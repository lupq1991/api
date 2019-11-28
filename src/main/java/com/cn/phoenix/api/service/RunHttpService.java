package com.cn.phoenix.api.service;

import com.alibaba.fastjson.JSONObject;
import com.cn.phoenix.api.pojo.*;
import com.cn.phoenix.api.util.HttpUtil;
import com.cn.phoenix.api.util.RegexUtil;
import com.cn.phoenix.api.util.VariableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author lupq
 * @date 2019/11/12 16:12
 */
@Component
public class RunHttpService {

    @Autowired
    TestResultService testResultService;

    @Autowired
    ApiService apiService;

    @Autowired
    HeaderService headerService;

    @Autowired
    HostService hostService;

    @Autowired
    private VariableService variableService;

    private static VariableService variableServiceStatic;

    @PostConstruct
    private void init() {
        variableServiceStatic = this.variableService;
        // 从库里获取变量
    }

    /**
     * 多线程调用
     */
    private static ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor) newFixedThreadPool(4);

    private static Map<String, String> variableNameAndValueMap = new HashMap<String, String>();

    private static Map<String, String> caseResultVariableMap = new HashMap<>();

    /**
     * 从数据库获取全局变量并添加到variableNameAndValueMap集合
     */
    private static void loadVariableToMap() {
        List<Variable> variableList = variableServiceStatic.selectNoCaseId();
        for (Variable variable : variableList) {
            String variableName = variable.getvKey();
            String variableValue = variable.getvValue();
            variableNameAndValueMap.put(variableName, variableValue);
        }
    }

    private static void loadCaseResultVariableToMap(int caseId, String caseVariable) {
        List<Variable> variableList = variableServiceStatic.selectHaveCaseId();
        for (Variable variable : variableList) {
            if (variable.getCaseId() == caseId) {
                String variableName = variable.getvKey();
                caseResultVariableMap.put(variableName, caseVariable);
            }
        }
    }

    private void runFirstCase(String url, int requestType, int contentType,
                              String parameter, Map<String, String> headerMap, int caseId, String regex) {
        HttpPojo httpPojo = HttpUtil.doService(url, requestType, contentType, parameter, headerMap);
        String result = httpPojo.getResult();
        String resultVariable = RegexUtil.getMatchedString(regex, result);
        loadCaseResultVariableToMap(caseId, resultVariable);
    }

    /**
     * 异步调用
     *
     * @param apis
     */
    @Async
    public void runAllTest(List<Api> apis, Integer runHostId) {
        // 处理header头
        Map<String, String> headerMap = new HashMap<>();
        List<Header> headerList = headerService.selectByCaseId(0);
        for (Header header : headerList) {
            headerMap.put(header.gethKey(), header.gethValue());
        }
        //调用一下,把全局变量拿到
        loadVariableToMap();

        Host hostInfo = hostService.selectById(runHostId);
        String host = hostInfo.getRunHost();

        List<Api> apiList = apiService.findAllInfo(apis);
        List<Map<String, Object>> results = new LinkedList<>();
        VariableUtil variableUtil = new VariableUtil();
        long startTime = System.currentTimeMillis();
        for (Api api : apiList) {
            int apiId = api.getId();
            String url = host + api.getPath();
            List<Cases> casesList = api.getCases();
            for (Cases cases : casesList) {
                int caseId = cases.getId();
                int requestType = cases.getRequest();
                int contentType = cases.getContent();
                List<Parameter> parameterList = cases.getParameter();
                Check check = cases.getCheck();
                for (Parameter parameter : parameterList) {
                    String params = parameter.getJsonStr();
                    //替换变量
                    params = variableUtil.replaceVariables(params, variableNameAndValueMap);
                    if (cases.getIsUseResult() == 1) {
                        params = variableUtil.replaceVariables(params, caseResultVariableMap);
                    }

                    Map<String, Object> map = new HashMap<>();
                    try {
                        Future<HttpPojo> resultFuture = null;
                        // 判断用例是否自定义了header,有就使用自己的header
                        if (cases.getHeaders().size() > 0) {
                            Map<String, String> headerMapCase = new HashMap<>();
                            List<Header> headerListCase = headerService.selectByCaseId(caseId);
                            for (Header header : headerListCase) {
                                headerMapCase.put(header.gethKey(), header.gethValue());
                            }
                            if (cases.getIsResultVariable() != null) {
                                runFirstCase(url, requestType, contentType, params, headerMapCase, caseId, cases.getIsResultVariable());
                            } else {
                                resultFuture = fixedThreadPool.submit(new EtcdBatchCallable(url, requestType, contentType, params, headerMapCase));
                            }
                        } else {
                            if (cases.getIsResultVariable() != null) {
                                runFirstCase(url, requestType, contentType, params, headerMap, caseId, cases.getIsResultVariable());
                            } else {
                                resultFuture = fixedThreadPool.submit(new EtcdBatchCallable(url, requestType, contentType, params, headerMap));
                            }
                        }
                        System.out.println("caseId=" + caseId + ",url→" + url + "requestType→" + requestType + "contentType→" + contentType + "params→" + params);
                        map.put("apiId", apiId);
                        map.put("caseId", caseId);
                        map.put("check", check);
                        map.put("result", resultFuture);
                        results.add(map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {

            List<TestResult> resultList = new ArrayList<>();

            for (Map<String, Object> future : results) {
                TestResult testResult = new TestResult();
                int apiId = (Integer) future.get("apiId");
                int caseId = (Integer) future.get("caseId");
                Check check = (Check) future.get("check");
                Future<HttpPojo> resultFuture = (Future<HttpPojo>) future.get("result");
                HttpPojo httpPojo = resultFuture.get();
                String result = httpPojo.getResult();
                String responseTime = httpPojo.getResponseTime() + "";
                int httpCode = httpPojo.getHttpCode();

                testResult.setApiId(apiId);
                testResult.setCaseId(caseId);
                testResult.setResponseTime(responseTime);
                testResult.setResult(result);

                //写入测试结果
                if (isPass(check, httpCode, result)) {
                    testResult.setPass(1);
                } else {
                    testResult.setPass(0);
                }

                resultList.add(testResult);
            }
            testResultService.insertResult(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("接口测试完成!!!" + endTime);
    }

    public boolean isPass(Check check, int httpCode, String result) {

        if (check == null) {
            return true;
        } else {
            Integer type = check.getType();
            if (type == 0) {
                return true;
            } else if (type == 1) {
                return check.getStatusCode() == httpCode;
            } else if (type == 2) {
                int contentType = check.getContentType();
                if (contentType == 1) {
                    return result.contains(check.getContent());
                } else {
                    return result.equals(check.getContent());
                }
            } else if (type == 3) {
                String regex = "\"" + check.getRegularKey() + "\":\"(.*?)\"";
                String value = RegexUtil.getMatchedString(regex, result);
                return check.getRegularValue().equals(value);
            }
        }
        return false;
    }
}
