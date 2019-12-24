package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.interceptor.HandleUser;
import com.cn.phoenix.api.pojo.*;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.cn.phoenix.api.service.ProjectService;
import com.cn.phoenix.api.service.ApiService;
import com.cn.phoenix.api.service.CaseService;
import com.cn.phoenix.api.service.DictionaryService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    ApiService apiService;

    @Autowired
    ProjectService apiGroupService;

    @Autowired
    CaseService caseService;

    @Autowired
    DictionaryService dictionaryService;

    @UserLoginToken
    @ApiOperation(value = "获取接口信息", notes = "获取接口信息")
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getApiGroup() {

        ItemsPojo<Project> itemsPojo = new ItemsPojo<>();
        Project project = new Project();
        project.setProjectIdList(HandleUser.getProjectIdByUser());

        List<Project> apiGroupList = apiGroupService.selectAllProjectAndApi(project);

        itemsPojo.setItems(apiGroupList);

        APIResponse<ItemsPojo> apiResponse = APIResponse.getSuccResponse();
        apiResponse.setData(itemsPojo);

        return apiResponse;
    }

    @UserLoginToken
    @ApiOperation(value = "获取接口信息(接口管理用到)", notes = "获取接口信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getApi(Integer page, Integer limit, Integer projectId) {
        BaseController<Api> baseController = new BaseController<>();

        if (baseController.isPageNull(page, limit) != null) {
            return baseController.isPageNull(page, limit);
        }
        Api api = new Api();

        List<Integer> projectIdList = HandleUser.getProjectIdByUser();

        if (projectIdList.size() == 0) {
            return APIResponse.getSuccResponse();
        }

        api.setProjectIdList(projectIdList);
        api.setProjectId(projectId);

        List<Api> apiList = apiService.findAll(page, limit, api);

        return baseController.getList(page, limit, apiList);
    }


    @UserLoginToken
    @ApiOperation(value = "获取接口所有信息", notes = "获取接口信息")
    @RequestMapping(value = "/all/list", method = RequestMethod.GET)
    public APIResponse<ItemsPojo<Api>> getApiAll(Integer apiId) {
        Api api = new Api();
        api.setId(apiId);
        List<Api> apiList1 = new ArrayList<>();
        if (apiId != null) {
            apiList1.add(api);
        }
        ItemsPojo<Api> itemsPojo = new ItemsPojo<>();
        // apiList1 获取的是个空的,暂时不需要改这个方法
        List<Api> apiList = apiService.findAllInfo(apiList1);
        itemsPojo.setItems(apiList);
        APIResponse<ItemsPojo<Api>> apiResponse = APIResponse.getSuccResponse();
        apiResponse.setData(itemsPojo);
        return apiResponse;
    }

    @UserLoginToken
    @ApiOperation(value = "新增接口", notes = "新增接口")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public APIResponse<Api> addApi(@RequestBody Api api) {
        if (StringUtils.isBlank(api.getName()) || StringUtils.isBlank(api.getPath())) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "name or path");
        }
        if (apiService.findApiByPath(api).size() > 0) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_REPEAT, api.getPath());
        }
        apiService.oneInsert(api);

        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "删除接口", notes = "删除接口")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public APIResponse<Api> deleteApi(@RequestBody Api api) {
        if (api.getId() == null) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "id");
        }
        if (apiService.selectById(api) == null) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, api.getId() + "");
        }

        // 查询这个接口下是否有用例
        List<Cases> caseList = caseService.findCasesByApiId(api.getId());
        if (caseList.size() > 0) {
            List<String> caseName = new ArrayList<>();
            for (Cases cases : caseList) {
                caseName.add(cases.getName());
            }
            return APIResponse.getErrorResponse(ResponseCode.DATA_LINKED, "接口id = " + api.getId() + ",用例 = " + caseName);
        }
        apiService.deleteById(api);
        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "更新接口", notes = "更新接口")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public APIResponse<Api> updateApi(@RequestBody Api api) {
        if (api.getId() == null) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "id");
        }
        if (apiService.selectById(api) == null) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, api.getId() + "");
        }
        if (api.getName() == null && api.getPath() == null && api.getStatus() == null && api.getInfo() == null) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_MUST_ONE, "name/path/status/info");
        }

        if (api.getPath() != null) {
            if (apiService.checkUnique(api).size() > 0) {
                return APIResponse.getErrorResponse(ResponseCode.DATA_REPEAT, "路径:" + api.getPath());
            }
        }

        apiService.updateById(api);

        return APIResponse.getSuccResponse();
    }


}
