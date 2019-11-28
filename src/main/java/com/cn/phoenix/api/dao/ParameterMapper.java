package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Parameter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.time.chrono.ChronoLocalDate;

@Mapper
public interface ParameterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Parameter record);

    int insertSelective(Parameter record);

    Parameter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Parameter record);

    int updateByPrimaryKey(Parameter record);

    List<Parameter> findAll();

    int batchInsertSelective(List<Parameter> parameterList);

    int batchUpdate(List<Parameter> parameterList);

    int batchDelete(List<Parameter> parameterList);

    Parameter findParameterByCaseId(@Param("caseId") Integer caseId);
}