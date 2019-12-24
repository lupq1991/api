package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.TestData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDataMapper {

    List<TestData> selectAll();

}
