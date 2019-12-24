package com.cn.phoenix.api.service;

import java.util.*;
import java.util.concurrent.Callable;

import com.cn.phoenix.api.pojo.HttpPojo;
import com.cn.phoenix.api.pojo.ItemsPojo;
import com.cn.phoenix.api.pojo.PerformTest;
import com.cn.phoenix.api.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 多线程调用测试方法
 */

@Service
@Scope("prototype")
public class EtcdBatchCallable implements Callable<HttpPojo> {

    private static Logger logger = LoggerFactory.getLogger(EtcdBatchCallable.class);

    private String url;
    private Integer requestType;
    private Integer contentType;
    private String params;
    private Map<String, String> headerMap;


    public EtcdBatchCallable(String url, Integer requestType, Integer contentType,
                             String params, Map<String, String> headerMap) {
        this.url = url;
        this.requestType = requestType;
        this.contentType = contentType;
        this.params = params;
        this.headerMap = headerMap;
    }


    @Override
    public HttpPojo call() {
        return HttpUtil.doService(url, requestType, contentType, params, headerMap);
    }


}
