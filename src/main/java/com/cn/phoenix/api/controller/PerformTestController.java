package com.cn.phoenix.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.pojo.*;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.cn.phoenix.api.service.*;
import com.cn.phoenix.api.util.HttpUtil;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.Executors.*;

@RestController
@RequestMapping(value = "/run")
public class PerformTestController {

    @Autowired
    HostService hostService;

    @Autowired
    ApiService apiService;

    @Autowired
    CaseService caseService;

    @Autowired
    CaseParameterService caseParameterService;

    @Autowired
    ParameterService parameterService;

    @Autowired
    TestResultService testResultService;

    @Autowired
    RunHttpService runHttpService;

    @Autowired
    HeaderService headerService;

    @Autowired
    CheckService checkService;

    @UserLoginToken
    @ApiOperation(value = "获取测试结果", notes = "获取测试结果")
    @RequestMapping(value = "/test/report", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getReports(Integer page, Integer limit, Integer apiId,
                                             Integer caseId, String startTime, String endTime) {

        BaseController<TestResult> baseController = new BaseController<>();

        if (baseController.isPageNull(page, limit) != null) {
            return baseController.isPageNull(page, limit);
        }

        TestResult testResult = new TestResult();
        testResult.setApiId(apiId);
        testResult.setCaseId(caseId);
        if (startTime != null && endTime != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date startTimeDate = dateFormat.parse(startTime);
                Date endTimeDate = dateFormat.parse(endTime);
                testResult.setStartTime(startTimeDate);
                testResult.setEndTime(endTimeDate);
            } catch (Exception e) {
                return APIResponse.getErrorResponse(ResponseCode.PARAMETER_INVALID, "开始或结束时间");
            }
        }
        List<TestResult> resultList = testResultService.pagingSelectResult(page, limit, testResult);

        return baseController.getList(page, limit, resultList);
    }


    @UserLoginToken
    @ApiOperation(value = "接口批量测试", notes = "接口批量测试")
    @RequestMapping(value = "/all/test")
    public APIResponse<TestResult> runAllTest(@RequestBody PerformTest performTest) {
        List<Api> apis = performTest.getApiList();
        Integer runHostId = performTest.getRunHostId();
        runHttpService.runAllTest(apis, runHostId);
        return APIResponse.getSuccResponse();

    }

    @UserLoginToken
    @ApiOperation(value = "单接口测试", notes = "单接口测试")
    @RequestMapping(value = "/case/test", method = RequestMethod.POST)
    public APIResponse<TestResult> unitOneTest(@RequestBody PerformTest performTest) {

        if (null == performTest.getRunHostId() || null == performTest.getApiId() || null == performTest.getCaseId()) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "hostId/apiId/caseId");
        }

        // 从参数中获取地址、接口、用例的id
        int runHostId = performTest.getRunHostId();
        int apiId = performTest.getApiId();
        int caseId = performTest.getCaseId();

        //实例化
        Cases cases = caseService.selectById(caseId);
        Api api = apiService.selectById(apiId);
        Host host = hostService.selectById(runHostId);

        //查看用例是否自定义header
        List<Header> headerListCase = headerService.selectByCaseId(caseId);

        if (cases == null || api == null || host == null) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_INVALID, "数据不存在");
        }

        //判断用例与接口是否匹配
        if (apiId != cases.getApiId()) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_INVALID, "用例id与接口id不匹配");
        }
        // 从用例里获取请求方式以及内容方式
        int requestType = cases.getRequest();
        int contentType = cases.getContent();

        String runHost = host.getRunHost();
        String path = api.getPath();
        String url = runHost + path;

        //根据用例获取对应的参数信息
        Parameter parameter = parameterService.findParameterByCaseId(caseId);
        String params = parameter.getJsonStr();

        Map<String, String> headerMap = new HashMap<>();
        if (headerListCase.size() > 0) {
            for (Header header : headerListCase) {
                headerMap.put(header.gethKey(), header.gethValue());
            }

        } else {
            // 处理header头
            List<Header> headerList = headerService.selectByCaseId(0);
            for (Header header : headerList) {
                headerMap.put(header.gethKey(), header.gethValue());
            }
        }
        //调用请求接口方法,获取调用结果
        HttpPojo httpPojo = HttpUtil.doService(url, requestType, contentType, params, headerMap);

        Check check = checkService.selectByCaseId(caseId);
        //测试结果入库
        List<TestResult> resultList = new ArrayList<>();
        TestResult testResult = new TestResult();
        testResult.setCaseId(caseId);
        testResult.setApiId(apiId);
        testResult.setResult(httpPojo.getResult());
        testResult.setResponseTime(httpPojo.getResponseTime() + "");
        if (runHttpService.isPass(check, httpPojo.getHttpCode(), httpPojo.getResult())) {
            testResult.setPass(1);
        } else {
            testResult.setPass(0);
        }

        //这些不入库,前端展示用
        testResult.setRunHost(url);
        testResult.setCaseName(cases.getName());
        testResult.setParameter(params);
        testResult.setExpected("");
        testResult.setApiName(host.getRunName());
        resultList.add(testResult);
        testResultService.insertResult(resultList);

        ItemsPojo<TestResult> itemsPojo = new ItemsPojo<>();
        itemsPojo.setItems(resultList);

        //返回值
        APIResponse<TestResult> response = APIResponse.getSuccResponse();
        response.setData(testResult);
        return response;
    }
}
