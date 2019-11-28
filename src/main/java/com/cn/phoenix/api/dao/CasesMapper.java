package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Cases;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CasesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cases record);

    int insertSelective(Cases record);

    Cases selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cases record);

    int updateByPrimaryKey(Cases record);

    List<Cases> findAll();


    List<Cases> findCasesByApiId(@Param("apiId") Integer apiId);

    List<Cases> findCaseAndParameter(@Param("apiId") Integer apiId);
}