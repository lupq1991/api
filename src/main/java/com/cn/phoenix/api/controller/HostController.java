package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.interceptor.HandleUser;
import com.cn.phoenix.api.pojo.Dictionary;
import com.cn.phoenix.api.pojo.Host;
import com.cn.phoenix.api.pojo.ItemsPojo;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.cn.phoenix.api.service.DictionaryService;
import com.cn.phoenix.api.service.HostService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/host")
public class HostController {

    final
    private HostService hostService;

    final
    private HttpSession httpSession;

    @Autowired
    public HostController(HostService hostService, HttpSession httpSession) {
        this.hostService = hostService;
        this.httpSession = httpSession;
    }


    @UserLoginToken
    @ApiOperation(value = "获取环境信息", notes = "获取环境信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getHost(Integer page, Integer limit) {

        List<Integer> projectIdList = HandleUser.getProjectIdByUser(httpSession);
        Host host = new Host();
        host.setProjectIdList(projectIdList);

        BaseController<Host> baseController = new BaseController<>();
        List<Host> hostList = hostService.findRunHost(page, limit, host);

        return baseController.getList(page, limit, hostList);
    }

    @UserLoginToken
    @ApiOperation(value = "新增环境信息", notes = "新增环境信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public APIResponse<Host> addHost(@RequestBody Host host) {

        if (StringUtils.isBlank(host.getRunHost()) || StringUtils.isBlank(host.getRunName())) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "runHost or runName");
        }
        if ((hostService.findHostByRunHost(host).size()) > 0) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_REPEAT, host.getRunHost());
        }
        int hostId = hostService.insert(host);
        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "删除环境信息", notes = "删除环境信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public APIResponse<Host> deleteHost(@RequestBody Host host) {

        if (host.getId() == null) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "id");
        }

        if (hostService.selectById(host) == null) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, host.getId() + "");
        }

        hostService.deleteByPrimaryKey(host);
        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "更新环境信息", notes = "更新环境信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public APIResponse<Host> updateHost(@RequestBody Host host, HttpSession httpSession) {
        if (host.getId() == null) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "id");
        }
        if (hostService.selectById(host) == null) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, host.getId() + "");
        }
        if (host.getRunName() == null && host.getRunHost() == null
                && host.getProjectId() == null && host.getStatus() == null) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_MUST_ONE,
                    "名称,环境,项目,状态");
        }

        if (!StringUtils.isBlank(host.getRunHost())) {
            if (hostService.checkUnique(host).size() > 0) {
                return APIResponse.getErrorResponse(ResponseCode.DATA_REPEAT, host.getRunHost());
            }
        }
        hostService.updateByPrimaryKeySelective(host);
        return APIResponse.getSuccResponse();
    }
}
