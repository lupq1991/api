package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Host;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Host record);

    int insertSelective(Host record);

    Host selectById(Integer id);

    Host selectHostAndConfigByHostId(Integer id);

    int updateByPrimaryKeySelective(Host record);

    int updateByPrimaryKey(Host record);

    List<Host> findHostByRunHost(String runHost);

    List<Host> findRunHost(Host host);

    List<Host> checkUnique(Host host);
}