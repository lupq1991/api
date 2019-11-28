package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.ParameterMapper;
import com.cn.phoenix.api.pojo.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService {

    @Autowired
    ParameterMapper parameterMapper;


    public Parameter findParameterById(int id) {
        return parameterMapper.selectByPrimaryKey(id);
    }

    public List<Parameter> findAll() {
        return parameterMapper.findAll();
    }

    public int deleteByPrimaryKey(Parameter parameter) {
        return parameterMapper.deleteByPrimaryKey(parameter.getId());
    }

    public int updateByPrimaryKeySelective(Parameter parameter) {
        return parameterMapper.updateByPrimaryKeySelective(parameter);
    }

    public int insertSelective(Parameter parameter) {
        return parameterMapper.insertSelective(parameter);
    }

    public int batchInsertSelective(List<Parameter> parameterList) {
        return parameterMapper.batchInsertSelective(parameterList);
    }

    public int batchUpdate(List<Parameter> parameterList) {
        return parameterMapper.batchUpdate(parameterList);
    }

    public int batchDelete(List<Parameter> parameterList) {
        return parameterMapper.batchDelete(parameterList);
    }

    public Parameter findParameterByCaseId(Integer caseId){
        return parameterMapper.findParameterByCaseId(caseId);
    }
}
