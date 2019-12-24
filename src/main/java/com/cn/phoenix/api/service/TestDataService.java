package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.TestDataMapper;
import com.cn.phoenix.api.pojo.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lupq
 * @date 2019/12/13 10:31
 */
@Service
public class TestDataService {
    @Autowired
    TestDataMapper testDataMapper;

    public List<TestData> selectAll() {
        return testDataMapper.selectAll();

    }
}
