package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Cases;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CasesMapper {
    int deleteById(Integer id);

    int insertSelective(Cases record);

    Cases selectById(Integer id);

    int updateById(Cases cases);

    List<Cases> findCasesByApiId(@Param("apiId") Integer apiId);

    List<Cases> findCaseAndParameter(Cases cases);

    List<Cases> selectRelatedCaseByid(@Param("id") Integer id);
}