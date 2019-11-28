package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Check;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CheckMapper {

    Check selectByCaseId(@Param("caseId") Integer caseId);

    int insert(Check check);

    int update(Check check);

}
