package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.TestResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestResultMapper {

    int batchInsertSelective(List<TestResult> testResults);

    List<TestResult> selectResult(TestResult testResults);

//    List<TestResult> selectAll();
}
