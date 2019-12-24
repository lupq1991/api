package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.interceptor.HandleUser;
import com.cn.phoenix.api.pojo.Project;
import com.cn.phoenix.api.pojo.ItemsPojo;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.cn.phoenix.api.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lupq
 * @date 2019/12/16 11:26
 */
@RestController
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @UserLoginToken
    @ApiOperation(value = "获取接口项目信息", notes = "获取接口项目信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getList(Integer page, Integer limit) {
        BaseController<Project> baseController = new BaseController<>();

        if (baseController.isPageNull(page, limit) != null) {
            return baseController.isPageNull(page, limit);
        }
        List<Integer> projectIdList = HandleUser.getProjectIdByUser();
        Project project = new Project();
        project.setProjectIdList(projectIdList);

        List<Project> projectList = projectService.selectAllProject(page, limit, project);

        return baseController.getList(page, limit, projectList);
    }

    @UserLoginToken
    @ApiOperation(value = "新增或编辑", notes = "新增或编辑")
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public APIResponse<Project> change(@RequestBody Project project) {
        if (StringUtils.isBlank(project.getName())) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "名称");
        }

        if (project.getGroupId() == null){
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "请选择组");
        }

        if (projectService.checkUnique(project) != null) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_REPEAT, "名称不能重复");
        }

        if (project.getId() == null) {
            projectService.oneInsert(project);
        } else {
            projectService.oneUpdate(project);
        }
        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public APIResponse<Project> delete(@RequestBody Project project) {

        if (projectService.selectById(project) == null) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, "");
        }

        projectService.oneDelete(project);
        return APIResponse.getSuccResponse();
    }

}
