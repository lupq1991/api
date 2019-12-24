package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.VariableMapper;
import com.cn.phoenix.api.pojo.Variable;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lupq
 * @date 2019/11/21 14:18
 */
@Service
public class VariableService {

    @Autowired
    VariableMapper variableMapper;

    public int batchInsert(List<Variable> variableList) {
        return variableMapper.batchInsert(variableList);
    }

    public int batchUpdate(List<Variable> variableList) {
        return variableMapper.batchUpdate(variableList);
    }

    public int batchDelete(List<Variable> variableList) {
        return variableMapper.batchDelete(variableList);
    }

    public int oneInsert(Variable variable) {
        return variableMapper.oneInsert(variable);
    }

    public int oneUpdate(Variable variable) {
        return variableMapper.oneUpdate(variable);
    }

    public int oneDelete(Variable variable) {
        return variableMapper.oneDelete(variable);
    }

    public Variable selectOnlyKey(Variable variable) {
        return variableMapper.selectOnlyKey(variable);
    }
    public Variable selectOnlyKeyByHostId(Variable variable) {
        return variableMapper.selectOnlyKeyByHostId(variable);
    }

    public List<Variable> select(Integer page, Integer limit) {
        BaseService<Variable> baseService = new BaseService<>();

        Page<Variable> pageNum = baseService.startPage(page, limit);

        variableMapper.select();
        return baseService.page(pageNum);
    }

    public List<Variable> selectAll() {
        return variableMapper.select();
    }

    public List<Variable> selectNoCaseId(Integer hostId) {
        return variableMapper.selectNoCaseId(hostId);
    }

    public List<Variable> selectHaveCaseId(Integer hostId) {
        return variableMapper.selectHaveCaseId(hostId);
    }
}
