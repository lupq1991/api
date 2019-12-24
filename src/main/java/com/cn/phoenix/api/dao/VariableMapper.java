package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Variable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VariableMapper {

    int batchInsert(List<Variable> variableList);

    int batchUpdate(List<Variable> variableList);

    int batchDelete(List<Variable> variableList);

    int oneInsert(Variable variable);

    int oneUpdate(Variable variable);

    int oneDelete(Variable variable);

    Variable selectOnlyKey(Variable variable);

    Variable selectOnlyKeyByHostId(Variable variable);

    List<Variable> select();

    List<Variable> selectNoCaseId(@Param("hostId") Integer hostId);

    List<Variable> selectHaveCaseId(@Param("hostId") Integer hostId);

}
