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

    public Cases selectByCaseId(Cases cases) {
        return casesMapper.selectById(cases.getCaseId());
    }

    public Cases selectById(int id) {
        return casesMapper.selectById(id);
    }

    public Cases selectById(Cases cases) {
        return casesMapper.selectById(cases.getId());
    }

    public List<Cases> findCasesByApiId(Integer apiId) {
        return casesMapper.findCasesByApiId(apiId);
    }

    public List<Cases> findCaseAndParameter(Integer page, Integer limit, Cases cases) {

        // 分页插件: 查询第1页，每页10行
        Page<Cases> pageNum = PageHelper.startPage(page, limit);
        List<Cases> casesList = casesMapper.findCaseAndParameter(cases);
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

    public int insertSelective(Cases cases) {
        return casesMapper.insertSelective(cases);
    }

    public int deleteById(Cases cases) {
        return casesMapper.deleteById(cases.getId());
    }

    public int updateById(Cases cases) {
        return casesMapper.updateById(cases);
    }

    public List<Cases> selectRelatedCaseByid(Integer id) {
        return casesMapper.selectRelatedCaseByid(id);
    }
}
