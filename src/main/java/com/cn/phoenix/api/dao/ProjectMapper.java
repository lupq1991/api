package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    List<Project> selectAllProjectAndApi(Project project);

    List<Project> selectAllProject(Project project);

    int oneInsert(Project project);

    int oneDelete(Project project);

    int oneUpdate(Project project);

    Project checkUnique(Project project);

    Project selectById(Project project);


}
