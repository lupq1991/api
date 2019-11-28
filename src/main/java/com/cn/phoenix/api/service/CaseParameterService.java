package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.CaseParameterMapper;
import com.cn.phoenix.api.pojo.CaseParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseParameterService {

    @Autowired
    CaseParameterMapper caseParameterMapper;

    public List<CaseParameter> findAllByCaseId(int caseId) {
        return caseParameterMapper.findAllByCaseId(caseId);
    }

    public int insertSelective(CaseParameter caseParameter){
        return caseParameterMapper.insertSelective(caseParameter);
    }

    public CaseParameter findParameterIdByCaseId(int caseId){
        return caseParameterMapper.findParameterIdByCaseId(caseId);
    }

}
