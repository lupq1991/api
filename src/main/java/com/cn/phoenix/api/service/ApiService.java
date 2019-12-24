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

    public List<Api> findAll(Integer page, Integer limit, Api api) {
        BaseService<Api> baseService = new BaseService<>();
        Page<Api> pageNum = baseService.startPage(page, limit);
        apiMapper.findAll(api);
        return baseService.page(pageNum);
    }

    public int oneInsert(Api api) {
        return apiMapper.oneInsert(api);
    }

    public List<Api> findApiByPath(Api api) {
        return apiMapper.findApiByPath(api.getPath());
    }

    public int deleteById(Api api) {
        return apiMapper.deleteById(api.getId());
    }

    public Api selectById(Api api) {
        return apiMapper.selectById(api.getId());
    }

    public Api selectById(int id) {
        return apiMapper.selectById(id);
    }

    public List<Api> checkUnique(Api api) {
        return apiMapper.checkUnique(api);
    }

    public int updateById(Api api) {
        return apiMapper.updateById(api);
    }

    public List<Api> findAllInfo(List<Api> api) {
        return apiMapper.findAllInfo(api);
    }
}
