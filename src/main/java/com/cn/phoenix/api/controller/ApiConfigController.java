package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.pojo.ApiConfig;
import com.cn.phoenix.api.pojo.ItemsPojo;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.cn.phoenix.api.service.ApiConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lupq
 * @date 2019/12/5 10:46
 */
@RestController
@RequestMapping("/config")
public class ApiConfigController {
    @Autowired
    ApiConfigService apiConfigService;

    @UserLoginToken
    @ApiOperation(value = "获取环境配置信息", notes = "获取环境配置信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getHost(Integer hostId) {
        List<ApiConfig> apiConfigList = apiConfigService.selectByHostId(hostId);

        ItemsPojo<ApiConfig> itemsPojo = new ItemsPojo<>();
        itemsPojo.setItems(apiConfigList);

        APIResponse<ItemsPojo> apiResponse = APIResponse.getSuccResponse();
        apiResponse.setData(itemsPojo);

        return apiResponse;
    }

    @UserLoginToken
    @ApiOperation(value = "处理配置信息", notes = "处理配置信息")
    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    public APIResponse<ApiConfig> handle(@RequestBody ApiConfig apiConfig) {
        if (apiConfig.getIsUse() == 1) {
            List<ApiConfig> configUseList = apiConfigService.onlyIsUse(apiConfig);
            if (configUseList.size() > 0) {
                return APIResponse.getErrorResponse(ResponseCode.DATA_REPEAT, "该环境正在使用其他配置");
            }
        }
        if (apiConfig.getId() == null) {
            apiConfigService.oneInsert(apiConfig);
        } else {
            apiConfigService.oneUpdate(apiConfig);
        }
        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public APIResponse<ApiConfig> delete(@RequestBody ApiConfig apiConfig) {

        if (apiConfigService.selectById(apiConfig) == null) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, "数据不存在");
        }
        apiConfigService.oneDelete(apiConfig);
        return APIResponse.getSuccResponse();
    }
}
