package com.cn.phoenix.api.interceptor;

import com.cn.phoenix.api.pojo.User;
import com.cn.phoenix.api.pojo.UserGroup;
import com.cn.phoenix.api.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lupq
 * @date 2019/12/19 21:36
 */
@Component
public class HandleUser {

    private UserGroupService userGroupService;

    private static UserGroupService userGroupServiceStatic;

    @Autowired
    public HandleUser(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @PostConstruct
    private void init() {
        userGroupServiceStatic = this.userGroupService;
    }

    /**
     * @return 根据userID返回对应的项目id
     */
    public static List<Integer> getProjectIdByUser(HttpSession httpSession) {
        List<Integer> projectList = new ArrayList<>();
        Integer userId = (Integer) httpSession.getAttribute("userId");
        List<UserGroup> userGroupList = userGroupServiceStatic.selectUserAndProjectByUserId(userId);
        for (UserGroup userGroup : userGroupList) {
            projectList.add(userGroup.getProjectId());
        }
        if (projectList.size() > 0) {
            return projectList;
        }
        projectList.add(null);
        return projectList;
    }

    /**
     * @return 根据userID返回对应的组id
     */
    public static List<Integer> getGroupIdByUser(HttpSession httpSession) {
        List<Integer> groupIdList = new ArrayList<>();
        Integer userId = (Integer) httpSession.getAttribute("userId");
        List<UserGroup> userGroupList = userGroupServiceStatic.selectUserAndProjectByUserId(userId);
        for (UserGroup userGroup : userGroupList) {
            groupIdList.add(userGroup.getGroupId());
        }
        if (groupIdList.size() > 0) {
            return groupIdList;
        }
        groupIdList.add(null);
        return groupIdList;
    }

    public static Integer getUserId(HttpSession httpSession) {
        return (Integer) httpSession.getAttribute("userId");
    }

}
