package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.interceptor.HandleUser;
import com.cn.phoenix.api.pojo.Group;
import com.cn.phoenix.api.pojo.ItemsPojo;
import com.cn.phoenix.api.pojo.UserGroup;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.cn.phoenix.api.service.GroupService;
import com.cn.phoenix.api.service.RunHttpService;
import com.cn.phoenix.api.service.UserGroupService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lupq
 * @date 2019/12/21 22:48
 */
@RestController
@RequestMapping("/group")
public class GroupController {
    private static final Logger logger = LoggerFactory.getLogger(RunHttpService.class);
    final
    private GroupService groupService;

    final
    private UserGroupService userGroupService;

    final
    private HttpSession httpSession;

    @Autowired
    public GroupController(GroupService groupService, UserGroupService userGroupService,HttpSession httpSession) {
        this.groupService = groupService;
        this.userGroupService = userGroupService;
        this.httpSession = httpSession;
    }


    @UserLoginToken
    @ApiOperation(value = "获取组", notes = "获取组")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getApi(Integer page, Integer limit) {
        BaseController<Group> baseController = new BaseController<>();
        //获取userId
        Integer userId = HandleUser.getUserId(httpSession);

        List<Group> groupList = groupService.selectGroupByUser(page, limit, userId);

        return baseController.getList(page, limit, groupList);
    }

    @UserLoginToken
    @ApiOperation(value = "处理组数据", notes = "处理组数据")
    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    public APIResponse<Group> handleData(@RequestBody Group group) {
        if (StringUtils.isBlank(group.getName())) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "名称");
        }
        try {
            if (group.getId() == null) {
                groupService.oneInsert(group);

                // 插入用户与组的关联
                Integer groupId = group.getId();
                Integer userId = HandleUser.getUserId(httpSession);
                UserGroup userGroup = new UserGroup();
                userGroup.setUserId(userId);
                userGroup.setGroupId(groupId);
                List<UserGroup> userGroupList = new ArrayList<>();
                userGroupList.add(userGroup);
                userGroupService.batchInsert(userGroupList);
            } else {
                groupService.oneUpdate(group);
            }
        } catch (Exception e) {
            logger.error(e + "");
            return APIResponse.getErrorResponse(ResponseCode.DATA_REPEAT, "名称不能重复");
        }
        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "删除组", notes = "删除组")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public APIResponse<Group> delete(@RequestBody Group group) {

        if (groupService.selectById(group) == null) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_NULL, "");
        }
        // 获取当前用户id
        Integer userId = HandleUser.getUserId(httpSession);
        group.setUserId(userId);
        if (groupService.selectGroupHaveOtherUser(group).size() > 0) {
            return APIResponse.getErrorResponse(ResponseCode.DATA_CUSTOM, "组里还有其他成员,不能删除!");
        }
        // 删除用户与组的关联
        Integer groupId = group.getId();
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupId(groupId);
        userGroup.setUserId(userId);
        UserGroup userGroupDelete = userGroupService.selectIdByuserAndGroup(userGroup);
        userGroupService.oneDelete(userGroupDelete);

        groupService.oneDelete(group);
        return APIResponse.getSuccResponse();
    }
}
