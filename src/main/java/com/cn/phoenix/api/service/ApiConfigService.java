package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.ApiConfigMapper;
import com.cn.phoenix.api.pojo.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lupq
 * @date 2019/12/5 10:38
 */
@Service
public class ApiConfigService {
    @Autowired
    ApiConfigMapper apiConfigMapper;

    public int oneInsert(ApiConfig apiConfig) {
        return apiConfigMapper.oneInsert(apiConfig);
    }

    public int oneUpdate(ApiConfig apiConfig) {
        return apiConfigMapper.oneUpdate(apiConfig);
    }

    public int oneDelete(ApiConfig apiConfig) {
        return apiConfigMapper.oneDelete(apiConfig);
    }

    public List<ApiConfig> selectByHostId(Integer hostId) {
        return apiConfigMapper.selectByHostId(hostId);
    }

    public List<ApiConfig> onlyIsUse(ApiConfig apiConfig) {
        return apiConfigMapper.onlyIsUse(apiConfig);
    }

    public ApiConfig selectById(ApiConfig apiConfig) {
        return apiConfigMapper.selectById(apiConfig);
    }
}
