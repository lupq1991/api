package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.pojo.CaseParameter;
import com.cn.phoenix.api.pojo.Cases;
import com.cn.phoenix.api.pojo.Parameter;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.cn.phoenix.api.service.CaseParameterService;
import com.cn.phoenix.api.service.CaseService;
import com.cn.phoenix.api.service.ParameterService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "parameter")
public class ParameterController {

    @Autowired
    ParameterService parameterService;

    @Autowired
    CaseService caseService;

    @Autowired
    CaseParameterService caseParameterService;

/**
 *
 * 这个controller应该用不到了
 *
 *
 *
 *
 *
 *
 *
    private Result result = new Result();


    @UserLoginToken
    @ApiOperation(value = "查询参数", notes = "查询参数")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getParameter() {
        return result.successResult(parameterService.findAll());
    }

    @UserLoginToken
    @ApiOperation(value = "查询参数", notes = "查询参数")
    @RequestMapping(value = "/getParameter1", method = RequestMethod.GET)
    public APIResponse<Parameter> getParameter1() {
        List<Parameter> parameterList = parameterService.findAll();

        APIResponse<Parameter> apiResponse = APIResponse.getSuccResponse();

        APIResponse<Parameter> apiResponse1 = APIResponse.getErrorResponse(ResponseCode.PARAMETER_MISS,"");

//        apiResponse.setData(parameterList);
        return apiResponse;
    }


    @UserLoginToken
    @ApiOperation(value = "新增参数", notes = "新增参数")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addParameter(@RequestBody Cases cases) {

        int caseId = cases.getCaseId();

        if (StringUtils.isBlank(caseId + "")) {
            return result.isBlankResult("caseId");
        }
        if (caseService.selectByCaseId(cases) == null) {
            return result.noDataResult(caseId);
        }

        List<Parameter> parameters = cases.getParameter();
        if (parameters.size() <= 0) {
            return result.isBlankResult("parameter");
        }

        for (Parameter parameter : parameters) {
            boolean keyIsNull = null == parameter.getpKey();
            boolean keyIsBlank = StringUtils.isBlank(parameter.getpKey());
            boolean valueIsNull = null == parameter.getpValue();
            boolean jsonStrIsNull = null == parameter.getJsonStr();
            if (keyIsNull && jsonStrIsNull) {
                return result.leastOneParameterResult("key/jsonStr");
            }
            if (!keyIsNull && !jsonStrIsNull) {
                return result.onlyOneParameterResult("key and jsonStr");
            }
            if (!valueIsNull && keyIsBlank) {
                return result.customPromptResult("value值没有对应的key");
            }
            parameterService.insertSelective(parameter);
            int parameterId = parameter.getId();
            CaseParameter caseParameter = new CaseParameter();
            caseParameter.setCaseId(caseId);
            caseParameter.setParameterId(parameterId);
            caseParameterService.insertSelective(caseParameter);
        }


        return result.addSuccessResult("");
    }


    @UserLoginToken
    @ApiOperation(value = "更新参数", notes = "更新参数")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateParameter(@RequestBody Parameter parameter) {

        boolean idIsBlank = StringUtils.isBlank(parameter.getId() + "");

        boolean keyIsNull = null == parameter.getpKey();
        boolean valueIsNull = null == parameter.getpValue();
        boolean jsonStrIsNull = null == parameter.getJsonStr();
        boolean infoIsNull = null == parameter.getInfo();


        if (idIsBlank) {
            return result.isBlankResult("id");
        }
        if (null == parameterService.findParameterById(parameter.getId())) {
            return result.noDataResult(parameter.getId());
        }
        if (keyIsNull && valueIsNull && infoIsNull && jsonStrIsNull) {
            return result.leastOneParameterResult("key/value/jsonStr/info");
        }

        if (!keyIsNull && !jsonStrIsNull) {
            return result.onlyOneParameterResult("key and jsonStr");
        }
        if (!valueIsNull && !jsonStrIsNull) {
            return result.onlyOneParameterResult("value and jsonStr");
        }
        Parameter parameterData = parameterService.findParameterById(parameter.getId());
        boolean keyIsBlankData = StringUtils.isBlank(parameterData.getpKey());

        if (!valueIsNull && keyIsBlankData) {
            return result.customPromptResult("value值没有对应的key");
        }
        System.out.println(StringUtils.isBlank(parameterData.getJsonStr()));
        if (!keyIsNull && StringUtils.isBlank(parameterData.getJsonStr())) {
            return result.customPromptResult("已经存在json参数");
        }
        if (!jsonStrIsNull && StringUtils.isBlank(parameterData.getpKey())) {
            return result.customPromptResult("已经存在键值对参数");
        }
        parameterService.updateByPrimaryKeySelective(parameter);
        return result.updateSuccessResult(parameter.getId());
    }

    @UserLoginToken
    @ApiOperation(value = "删除参数", notes = "删除参数")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteParameter(@RequestBody Parameter parameter) {

        if (null == parameter.getId()) {
            return result.isBlankResult("id");
        }
        if (null == parameterService.findParameterById(parameter.getId())) {
            return result.noDataResult(parameter.getId());
        }
        parameterService.deleteByPrimaryKey(parameter);
        return result.deleteSuccessResult(parameter.getId());
    }
    **/
}
