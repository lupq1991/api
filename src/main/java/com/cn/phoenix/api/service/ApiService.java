package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.ApiMapper;
import com.cn.phoenix.api.pojo.Api;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {

    @Autowired
    ApiMapper apiMapper;

    public List<Api> findAll(Integer page, Integer limit) {
        BaseService<Api> baseService = new BaseService<>();

        Page<Api> pageNum = baseService.startPage(page, limit);

        apiMapper.findAll();

        return baseService.page(pageNum);
    }

    public int insertSelective(Api api) {
        return apiMapper.insertSelective(api);
    }

    public List<Api> findApiByPath(Api api) {
        return apiMapper.findApiByPath(api.getPath());
    }

    public int deleteByPrimaryKey(Api api) {
        return apiMapper.deleteByPrimaryKey(api.getId());
    }

    public Api selectByPrimaryKey(Api api) {
        return apiMapper.selectByPrimaryKey(api.getId());
    }

    public Api selectById(int id) {
        return apiMapper.selectByPrimaryKey(id);
    }

    public List<Api> checkUnique(Api api) {
        return apiMapper.checkUnique(api);
    }

    public int updateByPrimaryKeySelective(Api api) {
        return apiMapper.updateByPrimaryKeySelective(api);
    }

    public List<Api> findAllInfo(List<Api> api) {
        return apiMapper.findAllInfo(api);
    }
}
