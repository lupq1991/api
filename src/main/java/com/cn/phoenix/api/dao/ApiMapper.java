package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Api;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Api record);

    int insertSelective(Api record);

    Api selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Api record);

    int updateByPrimaryKey(Api record);

    List<Api> findAll();

    List<Api> findApiByPath(String path);

    List<Api> checkUnique(Api api);

    List<Api> findAllInfo(List<Api> api);
}