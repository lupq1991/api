package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.ProjectMapper;
import com.cn.phoenix.api.pojo.Project;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lupq
 * @date 2019/12/9 17:10
 */
@Service
public class ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    public List<Project> selectAllProjectAndApi(Project project) {
        return projectMapper.selectAllProjectAndApi(project);
    }

    public List<Project> selectAllProject(Integer page, Integer limit, Project project) {
        BaseService<Project> baseService = new BaseService<>();
        Page<Project> pageNum = baseService.startPage(page, limit);
        projectMapper.selectAllProject(project);
        return baseService.page(pageNum);
    }

    public int oneDelete(Project project) {
        return projectMapper.oneDelete(project);
    }

    public int oneInsert(Project project) {
        return projectMapper.oneInsert(project);
    }

    public int oneUpdate(Project project) {
        return projectMapper.oneUpdate(project);
    }

    public Project checkUnique(Project project) {
        return projectMapper.checkUnique(project);
    }

    public Project selectById(Project project) {
        return projectMapper.selectById(project);
    }

}
