package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.HeaderMapper;
import com.cn.phoenix.api.pojo.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lupq
 * @date 2019/11/14 10:33
 */
@Service
public class HeaderService {

    @Autowired
    HeaderMapper headerMapper;

    public int batchInsert(List<Header> headerList) {
        return headerMapper.batchInsert(headerList);
    }


    public List<Header> selectByCaseId(Integer caseId) {
        return headerMapper.selectByCaseId(caseId);
    }

    public int batchUpdate(List<Header> headerList) {
        return headerMapper.batchUpdate(headerList);
    }

    public int batchDelete(List<Header> headerList) {
        return headerMapper.batchDelete(headerList);
    }
}
