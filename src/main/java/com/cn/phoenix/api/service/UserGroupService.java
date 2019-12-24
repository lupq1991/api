package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.UserGroupMapper;
import com.cn.phoenix.api.pojo.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lupq
 * @date 2019/12/19 22:39
 */
@Service
public class UserGroupService {
    final
    private UserGroupMapper userGroupMapper;

    @Autowired
    private UserGroupService(UserGroupMapper userGroupMapper) {
        this.userGroupMapper = userGroupMapper;
    }

    public List<UserGroup> selectUserAndProjectByUserId(Integer userId) {
        return userGroupMapper.selectUserAndProjectByUserId(userId);
    }

    public int batchInsert(List<UserGroup> userGroupList) {
        return userGroupMapper.batchInsert(userGroupList);
    }

    public UserGroup selectIdByuserAndGroup(UserGroup userGroup){
        return userGroupMapper.selectIdByuserAndGroup(userGroup);
    }

    public int oneDelete(UserGroup userGroup){
        return userGroupMapper.oneDelete(userGroup);
    }
}
