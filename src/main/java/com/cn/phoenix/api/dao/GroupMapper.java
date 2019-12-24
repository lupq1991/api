package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper {

    List<Group> selectGroupByUser(@Param("userId") Integer userId);

    int oneInsert(Group group);

    int oneUpdate(Group group);

    int oneDelete(Group group);

    Group selectById(Group group);

    List<Group> selectGroupHaveOtherUser(Group group);

}
