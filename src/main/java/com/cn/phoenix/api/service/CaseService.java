package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.CasesMapper;
import com.cn.phoenix.api.pojo.Cases;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {

    @Autowired
    CasesMapper casesMapper;

    public List<Cases> findAll() {
        return casesMapper.findAll();
    }

    public int insertSelective(Cases cases) {
        return casesMapper.insertSelective(cases);
    }

    public Cases selectByCaseId(Cases cases) {
        return casesMapper.selectByPrimaryKey(cases.getCaseId());
    }

    public Cases selectById(int id) {
        return casesMapper.selectByPrimaryKey(id);
    }

    public Cases selectByPrimaryKey(Cases cases) {
        return casesMapper.selectByPrimaryKey(cases.getId());
    }

    public int deleteByPrimaryKey(Cases cases) {
        return casesMapper.deleteByPrimaryKey(cases.getId());
    }

    public List<Cases> findCasesByApiId(Integer apiId) {
        return casesMapper.findCasesByApiId(apiId);
    }

    public int updateByPrimaryKeySelective(Cases cases) {
        return casesMapper.updateByPrimaryKeySelective(cases);
    }

    public List<Cases> findCaseAndParameter(Integer page, Integer limit, Integer apiId) {

        // 分页插件: 查询第1页，每页10行
        Page<Cases> pageNum = PageHelper.startPage(page, limit);
        List<Cases> casesList = casesMapper.findCaseAndParameter(apiId);
        if (casesList.size() > 0) {
            // 数据表的总行数
            pageNum.getTotal();
            // 分页查询结果的总行数
            pageNum.size();
            pageNum.get(0);
            return pageNum;
        }
        return null;
    }
}
