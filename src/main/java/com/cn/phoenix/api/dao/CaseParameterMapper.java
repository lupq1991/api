package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.CaseParameter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CaseParameterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseParameter record);

    int insertSelective(CaseParameter record);

    CaseParameter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseParameter record);

    int updateByPrimaryKey(CaseParameter record);

    List<CaseParameter> findAllByCaseId(int caseId);

    CaseParameter findParameterIdByCaseId(Integer caseId);
}