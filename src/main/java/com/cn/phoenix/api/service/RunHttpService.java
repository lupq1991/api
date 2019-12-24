package com.cn.phoenix.api.service;

import com.cn.phoenix.api.enumeration.DictCode;
import com.cn.phoenix.api.pojo.*;
import com.cn.phoenix.api.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(RunHttpService.class);


    @Autowired
    TestResultService testResultService;

    @Autowired
    ApiService apiService;

    @Autowired
    HeaderService headerService;

    @Autowired
    HostService hostService;

    @Autowired
    TestDataService testDataService;

    @Autowired
    private VariableService variableService;

    private static VariableService variableServiceStatic;

    @PostConstruct
    private void init() {
        variableServiceStatic = this.variableService;
    }

    /**
     * 多线程调用
     */
    private static ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor) newFixedThreadPool(1);

    /**
     * 存储全局变量
     */
    private static Map<String, String> variableNameAndValueMap = new HashMap<>();

    /**
     * 全局header
     */
    private static Map<String, String> globalHeaderMap = new HashMap<>();

    /**
     * 存储用例执行后的变量
     */
    private static Map<String, String> caseResultVariableMap = new HashMap<>();


    /**
     * 存储未获取过测试数据
     */
    private static List<TestData> testDataNoUseList = new ArrayList<>();

    private static List<TestData> testDataList = new ArrayList<>();

    /**
     * 从数据库获取全局变量并添加到variableNameAndValueMap集合
     */
    private static void loadVariableToMap(Integer hostId) {
        List<Variable> variableList = variableServiceStatic.selectNoCaseId(hostId);
        for (Variable variable : variableList) {
            String variableName = variable.getKey();
            String variableValue = variable.getValue();
            variableNameAndValueMap.put(variableName, variableValue);
        }
        logger.info("全局变量库:{}", variableNameAndValueMap);
    }

    /**
     * 用例执行结果添加到变量库
     *
     * @param caseId
     * @param caseVariable
     */
    private static void loadCaseResultVariableToMap(int caseId, Integer hostId, String regex, String caseVariable) {
        List<Variable> variableList = variableServiceStatic.selectHaveCaseId(hostId);
        for (Variable variable : variableList) {
            if (variable.getCaseId() == caseId && variable.getValue().equals(regex)) {
                String variableName = variable.getKey();
                caseResultVariableMap.put(variableName, caseVariable);
            }
        }
        logger.info("用例执行结果添加到变量库:{}", caseResultVariableMap);
    }

    /**
     * 先执行测试结果需要添加到变量的用例
     *
     * @param url
     * @param requestType
     * @param contentType
     * @param parameter
     * @param headerMap
     * @param caseId
     * @param isResultVariable 提示数据的字段
     */
    private HttpPojo runFirstCase(String url, int requestType, int contentType,
                                  String parameter, Map<String, String> headerMap, int caseId, int hostId, String[] isResultVariable) {

        HttpPojo httpPojo = HttpUtil.doService(url, requestType, contentType, parameter, headerMap);
        String result = httpPojo.getResult();
        for (String regex : isResultVariable) {
            String regexJoin = "\"" + regex + "\":\"(.*?)\"";
            String resultVariable = RegexUtil.getMatchedString(regexJoin, result);
            loadCaseResultVariableToMap(caseId, hostId, regex, resultVariable);
        }
        return httpPojo;
    }

    /**
     * 重新更新下全局header
     */
    private boolean isAgainWriteHeader() {

        for (String h : globalHeaderMap.keySet()) {
            String headerValue = globalHeaderMap.get(h);
            if (headerValue.contains("${")) {
                writeHeader();
                return true;
            }
        }
        return false;
    }

    /**
     * 返回header
     */
    private Map<String, String> getHeader(List caseHeaders, int caseId) {
        if (caseHeaders.size() > 0) {
            List<Header> headerListCase = headerService.selectByCaseId(caseId);
            return handleHeader(headerListCase);
        }
        return globalHeaderMap;
    }

    /**
     * 向全局header写入
     */
    private void writeHeader() {
        // 处理全局header头
        List<Header> headerList = headerService.selectByCaseId(0);
        logger.info("向全局header写入:");
        globalHeaderMap = handleHeader(headerList);
    }

    /**
     * 处理header,替换变量
     *
     * @param headerList
     * @return
     */
    private Map<String, String> handleHeader(List<Header> headerList) {
        Map<String, String> headerMap = new HashMap<>();
        for (Header header : headerList) {
            // 所有的header去拿一下变量
            String headerKey = header.getKey();
            String headerValue = header.getValue();
            Integer isUseResult = header.getIsUseResult();
            logger.info("替换变量前的header:{} → {} → isUseResult = {}", headerKey, headerValue, isUseResult);
            headerValue = VariableUtil.replaceVariables(headerValue, variableNameAndValueMap);
            // 判断一下是否用到其他用例的返回值
            if (isUseResult != null) {
                headerValue = VariableUtil.replaceVariables(headerValue, caseResultVariableMap);
            }
            headerMap.put(headerKey, headerValue);
        }
        logger.info("替换变量后的header:{}", headerMap);
        return headerMap;
    }


    public boolean isPass(Check check, int httpCode, String result) {

        if (check == null) {
            return true;
        } else {
            Integer type = check.getType();
            if (type == DictCode.CheckType.NO_CHECK.code()) {
                return true;
            } else if (type == DictCode.CheckType.STATUS_CODE.code()) {
                return check.getStatusCode() == httpCode;
            } else if (type == DictCode.CheckType.CONTENT.code()) {
                int contentType = check.getContentType();
                if (contentType == DictCode.CheckType.CONTENT_TYPE.code()) {
                    //都转换成大写然后比较
                    return result.toUpperCase().contains(check.getContent().toUpperCase());
                } else {
                    return result.equalsIgnoreCase(check.getContent());
                }
            } else if (type == DictCode.CheckType.REGULAR.code()) {
                String regex = "\"" + check.getRegularKey() + "\":\"(.*?)\"";
                String value = RegexUtil.getMatchedString(regex, result);
                return check.getRegularValue().equals(value);
            }
        }
        return true;
    }

    private Map<String, Object> writeResultMap(int hostId, int apiId, int caseId, Check check,
                                               String parameterVariable, String parameterMD5, String parameterAES,
                                               Future<HttpPojo> resultFuture, String aesDecryption) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("hostId", hostId);
        resultMap.put("apiId", apiId);
        resultMap.put("caseId", caseId);
        resultMap.put("check", check);
        resultMap.put("parameterVariable", parameterVariable);
        resultMap.put("parameterMD5", parameterMD5);
        resultMap.put("parameterAES", parameterAES);
        resultMap.put("resultFuture", resultFuture);
        resultMap.put("aesDecryption", aesDecryption);
        return resultMap;
    }

    private Map<String, Object> writeResultMap(int hostId, int apiId, int caseId, Check check,
                                               String parameterVariable, String parameterMD5, String parameterAES,
                                               HttpPojo httpPojo, String aesDecryption, String[] isResultVariable) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("hostId", hostId);
        resultMap.put("apiId", apiId);
        resultMap.put("caseId", caseId);
        resultMap.put("check", check);
        resultMap.put("parameterVariable", parameterVariable);
        resultMap.put("parameterMD5", parameterMD5);
        resultMap.put("parameterAES", parameterAES);
        resultMap.put("httpPojo", httpPojo);
        resultMap.put("aesDecryption", aesDecryption);
        resultMap.put("isResultVariable", StringUtils.join(isResultVariable));
        return resultMap;
    }

    private String getCheckString(Check check) {
        if (check == null) {
            return null;
        }
        Integer type = check.getType();
        switch (type) {
            case 0:
                return null;
            case 1:
                return DictCode.CheckType.STATUS_CODE.text() + "," + check.getStatusCode();
            case 2:
                int contentType = check.getContentType();
                if (contentType == DictCode.CheckType.CONTENT_TYPE.code()) {
                    return DictCode.CheckType.CONTENT_TYPE.text() + "," + check.getContent();
                } else {
                    return "完全匹配" + "," + check.getContent();
                }
            case 3:
                return DictCode.CheckType.REGULAR.text() + "," + check.getRegularKey() + "→" + check.getRegularValue();
            default:
                return null;
        }
    }

    /**
     * 测试结果入库
     *
     * @param resultListMap
     * @param aesKey
     */
    private void writeResult(List<Map<String, Object>> resultListMap, String aesKey) {

        try {

            String batchId = "batch_test_" + System.currentTimeMillis();

            List<TestResult> resultList = new ArrayList<>();

            for (Map<String, Object> results : resultListMap) {
                TestResult testResult = new TestResult();

                int hostId = (Integer) results.get("hostId");
                int apiId = (Integer) results.get("apiId");
                int caseId = (Integer) results.get("caseId");


                String parameterVariable = (String) results.get("parameterVariable");
                String parameterMD5 = (String) results.get("parameterMD5");
                String parameterAES = (String) results.get("parameterAES");
                Check check = (Check) results.get("check");

                // isResultVariable 执行结果要加到变量里
                String isResultVariable = (String) results.get("isResultVariable");
                HttpPojo httpPojo;
                if (isResultVariable != null) {
                    httpPojo = (HttpPojo) results.get("httpPojo");
                } else {
                    Future<HttpPojo> resultFuture = (Future<HttpPojo>) results.get("resultFuture");
                    httpPojo = resultFuture.get();
                }

                String result = httpPojo.getResult();
                String responseTime = httpPojo.getResponseTime() + "";
                int httpCode = httpPojo.getHttpCode();

                String aesDecryption = (String) results.get("aesDecryption");
                String resultDecryption = null;
                if (!StringUtils.isBlank(aesDecryption)) {
                    resultDecryption = AESUtil.paramsDecryption(aesKey, result, aesDecryption);
                }

                testResult.setHostId(hostId);
                testResult.setApiId(apiId);
                testResult.setCaseId(caseId);
                testResult.setBatchId(batchId);
                testResult.setCheck(getCheckString(check));

                //写入测试结果
                if (isPass(check, httpCode, resultDecryption)) {
                    testResult.setPass(1);
                } else {
                    testResult.setPass(0);
                }

                testResult.setResponseTime(responseTime);
                testResult.setParameterVariable(parameterVariable);
                testResult.setParameterMD5(parameterMD5);
                testResult.setParameterAES(parameterAES);
                testResult.setResult(result);
                testResult.setResultDecryption(resultDecryption);


                resultList.add(testResult);
            }
            // 测试结果入库
            testResultService.insertResult(resultList);
        } catch (Exception e) {
            logger.error("测试结果入库", e);
        }

    }

    /**
     * 随机获取一组三要素
     *
     * @return
     */
    private TestData getRandomTestData() {
        int size = testDataNoUseList.size();
        // 预置数据里未使用数量(testDataNoUseList)为0时,就把库里数据复制过去
        if (size == 0) {
            testDataNoUseList.addAll(testDataList);
            size = testDataNoUseList.size();
        }
        int index = OtherUtil.getRandomNum(size);
        TestData testData = testDataNoUseList.get(index);
        testDataNoUseList.remove(index);
        return testData;
    }

    /**
     * 获取预置测试数据的变量key值
     *
     * @return
     */
    private Map<String, String> getTestDataKey() {
        Map<String, String> testDataKeyMap = new HashMap<>();
        testDataKeyMap.put("random", "${" + DictCode.TestData.RANDOM.enText() + "}");
        testDataKeyMap.put("name", "${" + DictCode.TestData.NAME.enText() + "}");
        testDataKeyMap.put("idCard", "${" + DictCode.TestData.ID_CARD.enText() + "}");
        testDataKeyMap.put("phone", "${" + DictCode.TestData.PHONE.enText() + "}");
        return testDataKeyMap;
    }


    /**
     * 异步调用
     *
     * @param apis
     */
    @Async
    public void batchRunCase(List<Api> apis, Integer runHostId) {
        // 调用一下,把全局变量拿到
        loadVariableToMap(runHostId);

        // 把临时变量库清空一下
        caseResultVariableMap.clear();

        // 获取预置的测试数据
        testDataList = testDataService.selectAll();

        // 系统预置的变量
        Map<String, String> testDataKeyMap = getTestDataKey();

        // 处理header头
        writeHeader();
        // header是否需要去变量库替换
        boolean headerToVariable = true;

        Host hostInfo = hostService.selectHostAndConfigByHostId(runHostId);
        String host = hostInfo.getRunHost();
        if (host.endsWith("/")) {
            host = host.substring(0, host.length() - 1);
        }
        ApiConfig apiConfig = hostInfo.getApiConfig();
        String aseKey = null;
        if (apiConfig != null) {
            aseKey = apiConfig.getAesKey();
        }

        List<Integer> apiIdList = new ArrayList<>();
        List<Integer> caseIdList = new ArrayList<>();
        for (Api a : apis) {
            apiIdList.add(a.getId());
            for (Cases c : a.getCases()) {
                caseIdList.add(c.getId());
            }
        }
        List<Api> apiAndCaseIdList = new ArrayList<>();
        Api apiAndCaseId = new Api();
        apiAndCaseId.setApiIdList(apiIdList);
        apiAndCaseId.setCaseIdList(caseIdList);
        apiAndCaseIdList.add(apiAndCaseId);

        List<Api> apiList = apiService.findAllInfo(apiAndCaseIdList);
        List<Map<String, Object>> results = new LinkedList<>();
        long startTime = System.currentTimeMillis();

        for (Api api : apiList) {
            int apiId = api.getId();
            String path = api.getPath();
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            String url = host + path;
            String aesEncryption = api.getAesEncryption();
            String aesDecryption = api.getAesDecryption();

            List<Cases> casesList = api.getCases();
            for (Cases cases : casesList) {
                int caseId = cases.getId();
                String caseName = cases.getName();
                int requestType = cases.getRequest();
                int contentType = cases.getContent();
                String[] isResultVariable = cases.getIsResultVariable();
                // 用例里加密参数为null就走api里的
                if (cases.getAesEncryption() != null) {
                    aesEncryption = cases.getAesEncryption();
                }
                // 用例里解密参数为null就走api里的
                if (cases.getAesDecryption() != null) {
                    aesDecryption = cases.getAesDecryption();
                }
                // 校验
                Check check = cases.getCheck();
                List<Parameter> parameterList = cases.getParameter();
                for (Parameter params : parameterList) {
                    String parameter = params.getJsonStr();
                    // 替换下系统预置的变量
                    TestData testData = getRandomTestData();
                    parameter = parameter.replace(testDataKeyMap.get("random"), OtherUtil.getTimeStamp() + "").
                            replace(testDataKeyMap.get("name"), testData.getName()).
                            replace(testDataKeyMap.get("idCard"), testData.getIdCard()).
                            replace(testDataKeyMap.get("phone"), testData.getPhone());

                    // 替换变量
                    logger.info("替换变量前的参数:{}", parameter);
                    parameter = VariableUtil.replaceVariables(parameter, variableNameAndValueMap);
                    if (cases.getIsUseResult() != null) {
                        parameter = VariableUtil.replaceVariables(parameter, caseResultVariableMap);
                    }
                    //判断参数是否需要随机值
                    if (parameter.contains("reqSn_替换")) {
                        String reqSn = System.currentTimeMillis() + "";
                        parameter = parameter.replace("reqSn_替换", reqSn);
                    }
                    String parameterVariable = parameter;
                    logger.info("替换变量后的参数:{}", parameter);
                    // 参数是否需要md5加密
                    String md5 = cases.getMd5Encryption();
                    String parameterMD5 = null;
                    if (!StringUtils.isBlank(md5)) {
                        parameter = MD5Util.stringToMD5(parameter, md5);
                        logger.info("MD5加密结果:{}", parameter);
                        parameterMD5 = parameter;
                    }
                    //参数是否需要AES加密
                    String parameterAES = null;
                    if (!StringUtils.isBlank(aesEncryption)) {
                        parameter = AESUtil.paramsEncryption(aseKey, parameter, aesEncryption);
                        logger.info("AES加密后的结果:{}", parameter);
                        parameterAES = parameter;
                    }
                    try {
                        Future<HttpPojo> resultFuture;
                        Map<String, String> headerMap = getHeader(cases.getHeaders(), caseId);
                        Map<String, Object> resultMap;
                        // 判断,用例结果是否需要添加到变量里
                        if (isResultVariable != null && isResultVariable.length > 0) {
                            logger.info("用例【{}】需要后置结果,url→{},参数→{},header→{},提取字段→{},是否需要重新处理header = {}"
                                    , caseName, url, parameter, headerMap, isResultVariable[0], headerToVariable);

                            HttpPojo httpPojo = runFirstCase(url, requestType, contentType, parameter, headerMap, caseId, runHostId, isResultVariable);
                            resultMap = writeResultMap(runHostId, apiId, caseId, check, parameterVariable, parameterMD5, parameterAES,
                                    httpPojo, aesDecryption, isResultVariable);
                            // 判断一下全局变量是否需要重新获取
                            if (headerToVariable) {
                                headerToVariable = isAgainWriteHeader();
                            }
                        } else {
                            logger.info("用例【{}】走多线程测试,url→{},参数→{},header→{}", caseName, url, parameter, headerMap);
                            resultFuture = fixedThreadPool.submit(new EtcdBatchCallable(url, requestType, contentType, parameter, headerMap));
                            resultMap = writeResultMap(runHostId, apiId, caseId, check, parameterVariable, parameterMD5, parameterAES, resultFuture, aesDecryption);
                        }
                        // 把结果写到resultMap
                        results.add(resultMap);
                    } catch (Exception e) {
                        logger.error("测试失败!", e);
                    }
                }
            }
        }
        //往数据库写入结果
        writeResult(results, aseKey);
        long endTime = System.currentTimeMillis() - startTime;
        logger.info("测试结束,耗时{}秒", (endTime / 1000));
    }
}
