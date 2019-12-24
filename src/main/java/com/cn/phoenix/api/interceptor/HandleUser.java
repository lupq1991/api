package com.cn.phoenix.api.interceptor;

import com.cn.phoenix.api.pojo.User;
import com.cn.phoenix.api.pojo.UserGroup;
import com.cn.phoenix.api.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lupq
 * @date 2019/12/19 21:36
 */
@Component
public class HandleUser {

    static User user;


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
    public static List<Integer> getProjectIdByUser() {
        List<Integer> projectList = new ArrayList<>();
        Integer userId = user.getId();
        if (user != null) {
            List<UserGroup> userGroupList = userGroupServiceStatic.selectUserAndProjectByUserId(userId);
            for (UserGroup userGroup : userGroupList) {
                projectList.add(userGroup.getProjectId());
            }
        }
        return projectList;
    }

    /**
     *
     * @return 根据userID返回对应的组id
     */
    public static List<Integer> getGroupIdByUser() {
        List<Integer> groupIdList = new ArrayList<>();
        Integer userId = user.getId();
        if (user != null) {
            List<UserGroup> userGroupList = userGroupServiceStatic.selectUserAndProjectByUserId(userId);
            for (UserGroup userGroup : userGroupList) {
                groupIdList.add(userGroup.getGroupId());
            }
        }
        return groupIdList;
    }

    /**
     * @return 返回用户id
     */
    public static int getUserId() {
        return user.getId();
    }
}
