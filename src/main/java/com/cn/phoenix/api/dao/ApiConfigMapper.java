package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.ApiConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiConfigMapper {

    int oneInsert(ApiConfig apiConfig);

    int oneUpdate(ApiConfig apiConfig);

    int oneDelete(ApiConfig apiConfig);

    List<ApiConfig> selectByHostId(@Param("hostId") Integer hostId);

    ApiConfig selectById(ApiConfig apiConfig);

    List<ApiConfig> onlyIsUse(ApiConfig apiConfig);
}
