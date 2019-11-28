package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Variable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VariableMapper {

    int batchInsert(List<Variable> variableList);

    int batchUpdate(List<Variable> variableList);

    int batchDelete(List<Variable> variableList);

    int oneInsert(Variable variable);

    int oneUpdate(Variable variable);

    int oneDelete(Variable variable);

    List<Variable> selectByKey(String key);

    Variable selectOnlyKey(Variable variable);

    List<Variable> select();

    List<Variable> selectNoCaseId();

    List<Variable> selectHaveCaseId();

}
