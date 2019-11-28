package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.CheckMapper;
import com.cn.phoenix.api.pojo.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lupq
 * @date 2019/11/18 14:31
 */
@Service
public class CheckService {

    @Autowired
    CheckMapper checkMapper;

    public Check selectByCaseId(Integer caseId) {
        return checkMapper.selectByCaseId(caseId);
    }

    public int insert(Check check){
        return checkMapper.insert(check);
    }

    public int update(Check check){
        return checkMapper.update(check);
    }
}
