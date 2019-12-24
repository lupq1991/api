package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.UserGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserGroupMapper {


    List<UserGroup> selectUserAndProjectByUserId(@Param("userId") Integer userId);

    int batchInsert(List<UserGroup> useGroupList);

    UserGroup selectIdByuserAndGroup(UserGroup userGroup);

    int oneDelete(UserGroup userGroup);
}
