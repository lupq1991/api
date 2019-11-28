package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.pojo.ItemsPojo;
import com.cn.phoenix.api.pojo.Variable;
import com.cn.phoenix.api.pojo.VariableList;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.cn.phoenix.api.service.VariableService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author lupq
 * @date 2019/11/21 14:17
 */
@RestController
@RequestMapping(value = "/variable")
public class VariableController {

    @Autowired
    VariableService variableService;

    @UserLoginToken
    @ApiOperation(value = "获取变量", notes = "获取变量")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getList(Integer page, Integer limit) {
        BaseController<Variable> baseController = new BaseController<>();

        if (baseController.isPageNull(page, limit) != null) {
            return baseController.isPageNull(page, limit);
        }
        List<Variable> dataList = variableService.select(page, limit);

        return baseController.getList(page, limit, dataList);
    }


    @UserLoginToken
    @ApiOperation(value = "新增变量", notes = "新增变量")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public APIResponse<Variable> add(@RequestBody Variable variable) {
        if (StringUtils.isBlank(variable.getvKey()) || StringUtils.isBlank(variable.getvValue())) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "变量名或者变量值");
        }
        if (variableService.selectByKey(variable.getvKey()).size() > 0) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_REPEAT, "变量名");
        }
        variableService.oneInsert(variable);
        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "更新变量", notes = "更新变量")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public APIResponse<Variable> update(@RequestBody Variable variable) {
        if (StringUtils.isBlank(variable.getId() + "")) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "缺少主键Id");
        }
        if (StringUtils.isBlank(variable.getvKey()) || StringUtils.isBlank(variable.getvValue())) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "变量名或者变量值");
        }
        if (variableService.selectOnlyKey(variable) != null) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_REPEAT, "变量名");
        }
        variableService.oneUpdate(variable);
        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "删除变量", notes = "删除变量")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public APIResponse<Variable> delete(@RequestBody Variable variable) {
        if (StringUtils.isBlank(variable.getId() + "")) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "缺少主键Id");
        }
        variableService.oneDelete(variable);
        return APIResponse.getSuccResponse();
    }


    @UserLoginToken
    @ApiOperation(value = "变量增删改操作", notes = "变量增删改操作")
    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    public APIResponse<ItemsPojo> handleVariable(@RequestBody VariableList variables) {
        List<Variable> variableList = variables.getVariableList();
        List<Variable> variableListDB = variableService.selectAll();

        List<Variable> addVariableList = new ArrayList<>();
        List<Variable> updateVariableList = new ArrayList<>();

        Set<String> set = new HashSet<>();

        for (Variable variable : variableList) {

            if (variable.getId() == null) {
                //这里数据要新增
                addVariableList.add(variable);
            } else {
                updateVariableList.add(variable);
                set.add(variable.getId() + "");
            }
        }
        // 使用LinkedList方便插入和删除
        List<Variable> deleteVariableList = new LinkedList<>(variableListDB);
        // 如果set中包含id则remove
        deleteVariableList.removeIf(item -> set.contains(item.getId() + ""));

        if (deleteVariableList.size() > 0) {
            variableService.batchDelete(deleteVariableList);
        }
        try {
            //新增
            if (addVariableList.size() > 0) {
                variableService.batchInsert(addVariableList);
            }
            // 更新
            if (updateVariableList.size() > 0) {
                variableService.batchUpdate(updateVariableList);
            }
        } catch (Exception e) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_ERROR, "变量名/变量值为空或者变量名重复");
        }
        return APIResponse.getSuccResponse();
    }
}
