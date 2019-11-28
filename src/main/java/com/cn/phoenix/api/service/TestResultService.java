package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.TestResultMapper;
import com.cn.phoenix.api.pojo.TestResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lupq
 * @date 2019/11/10 22:04
 */
@Service
public class TestResultService {
    @Autowired
    TestResultMapper testResultMapper;

    public int insertResult(List<TestResult> testResults) {
        return testResultMapper.batchInsertSelective(testResults);
    }

    public List<TestResult> selectResult(TestResult testResults) {
        return testResultMapper.selectResult(testResults);
    }

    public List<TestResult> pagingSelectResult(Integer page, Integer limit, TestResult testResults) {

        BaseService<TestResult> baseService = new BaseService<>();
        Page<TestResult> pageNum = baseService.startPage(page, limit);

        List<TestResult> resultList = testResultMapper.selectResult(testResults);
        if (resultList.size() > 0) {
            return baseService.page(pageNum);
        }
        return null;
    }
}
