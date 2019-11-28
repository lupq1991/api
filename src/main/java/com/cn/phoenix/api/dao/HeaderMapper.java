package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Header;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HeaderMapper {

    int batchInsert(List<Header> headerList);

    List<Header> selectByCaseId(@Param("caseId") Integer caseId);

    int batchUpdate(List<Header> headerList);

    int batchDelete(List<Header> headerList);
}
