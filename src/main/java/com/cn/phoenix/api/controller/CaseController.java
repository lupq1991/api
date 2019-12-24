package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.interceptor.HandleUser;
import com.cn.phoenix.api.pojo.*;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.cn.phoenix.api.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/case")
public class CaseController {

    @Autowired
    CaseService caseService;

    @Autowired
    ApiService apiService;

    @Autowired
    CaseParameterService caseParameterService;

    @Autowired
    ParameterService parameterService;

    @Autowired
    CheckService checkService;

    @UserLoginToken
    @ApiOperation(value = "获取用例和参数,可通过apiId获取", notes = "获取用例")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getCase(Integer page, Integer limit, Integer apiId) {
        BaseController<Cases> baseController = new BaseController<>();
        if (baseController.isPageNull(page, limit) != null) {
            return baseController.isPageNull(page, limit);
        }
        List<Integer> projectIdList = HandleUser.getProjectIdByUser();
        Cases cases = new Cases();
        cases.setProjectIdList(projectIdList);
        cases.setApiId(apiId);

        List<Cases> casesList = caseService.findCaseAndParameter(page, limit, cases);
        return baseController.getList(page, limit, casesList);
    }

    @UserLoginToken
    @ApiOperation(value = "获取关联用例", notes = "获取关联用例")
    @RequestMapping(value = "/related/list", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getRelatedCase(Integer id) {

        List<Cases> casesList = caseService.selectRelatedCaseByid(id);
        ItemsPojo<Cases> itemsPojo = new ItemsPojo<>();
        itemsPojo.setItems(casesList);
        APIResponse<ItemsPojo> apiResponse = APIResponse.getSuccResponse();
        apiResponse.setData(itemsPojo);
        return apiResponse;
    }

    @UserLoginToken
    @ApiOperation(value = "新增用例和参数", notes = "新增用例")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public APIResponse<Cases> addCase(@RequestBody Cases cases) {
        if (null == cases.getName()) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "name");
        }
        if (null == cases.getApiId()) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "apiId");
        }
        if (null == apiService.selectById(cases.getApiId())) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, "用例id = " + cases.getApiId());
        }
        caseService.insertSelective(cases);
        if (cases.getParameter().size() > 0) {
            int caseId = cases.getId();
            List<Parameter> parameterList = cases.getParameter();
            for (Parameter parameter : parameterList) {
                parameter.setCaseId(caseId);
            }
            parameterService.batchInsertSelective(parameterList);
        }
        Check check = cases.getCheck();
        if (check != null) {
            APIResponse<Cases> handleCheckResult = handleCheck(check, cases);
            if (handleCheckResult != null) {
                return handleCheckResult;
            }
        }
        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "删除用例和参数", notes = "删除用例")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public APIResponse<Cases> deleteCase(@RequestBody Cases cases) {
        if (null == cases.getId()) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "id");
        }
        if (null == caseService.selectById(cases)) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, cases.getId() + "");
        }

        List<Parameter> parameterList = cases.getParameter();
        if (parameterList.size() > 0) {
            parameterService.batchDelete(parameterList);
        }
        caseService.deleteById(cases);
        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "修改用例", notes = "修改用例")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public APIResponse<Cases> updateCase(@RequestBody Cases cases) {
        if (null == cases.getId()) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "id");
        }
        if (null == caseService.selectById(cases)) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, cases.getId() + "");
        }
        if (null == cases.getName() && null == cases.getApiId() && null == cases.getRequest()
                && null == cases.getContent() && null == cases.getInfo()) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_MUST_ONE, "name/apiId/request/format/info");
        }
        if (null != cases.getApiId()) {
            if (null == apiService.selectById(cases.getApiId())) {
                return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, "接口id = " + cases.getApiId());
            }
        }

        caseService.updateById(cases);
        List<Parameter> parameterList = cases.getParameter();

        if (parameterList.size() > 0) {
            parameterService.batchUpdate(parameterList);
        }
        Check check = cases.getCheck();
        if (check != null) {
            APIResponse<Cases> handleCheckResult = handleCheck(check, cases);
            if (handleCheckResult != null) {
                return handleCheckResult;
            }
        }
        APIResponse<Cases> apiResponse = APIResponse.getSuccResponse();
        apiResponse.setData(cases);
        return apiResponse;
    }

    private APIResponse<Cases> handleCheck(Check check, Cases cases) {
        int type = check.getType();
        switch (type) {
            case 1:
                if (check.getStatusCode() == null) {
                    return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "状态码");
                }
                break;
            case 2:
                if (check.getContentType() == null || check.getContent() == null) {
                    return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "匹配内容");
                }
                break;
            case 3:
                if (check.getRegularKey() == null || check.getRegularValue() == null) {
                    return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "正则内容");
                }
                break;
            default:
        }
        if (check.getType() == 1) {
            check.setContentType(null);
            check.setContent(null);
            check.setRegularKey(null);
            check.setRegularValue(null);
        }
        if (check.getType() == 2) {
            check.setStatusCode(null);
            check.setRegularKey(null);
            check.setRegularValue(null);
        }
        if (check.getType() == 3) {
            check.setStatusCode(null);
            check.setContentType(null);
            check.setContent(null);
        }
        if (check.getType() == 0) {
            check.setStatusCode(null);
            check.setContentType(null);
            check.setContent(null);
            check.setRegularKey(null);
            check.setRegularValue(null);
        }

        if (check.getId() != null) {
            checkService.update(check);
        } else {
            check.setCaseId(cases.getId());
            checkService.insert(check);
        }
        return null;
    }
}
