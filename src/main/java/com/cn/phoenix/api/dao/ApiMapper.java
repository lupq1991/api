package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Api;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiMapper {
    int deleteById(Integer id);

    int oneInsert(Api record);

    Api selectById(Integer id);

    int updateById(Api api);

    List<Api> findAll(Api api);

    List<Api> findApiByPath(String path);

    List<Api> checkUnique(Api api);

    List<Api> findAllInfo(List<Api> api);
}