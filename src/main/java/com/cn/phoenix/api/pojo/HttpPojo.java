package com.cn.phoenix.api.pojo;

import lombok.Data;

/**
 * @author lupq
 * @date 2019/11/18 15:53
 */
@Data
public class HttpPojo {
    private String result;
    private long responseTime;
    private int httpCode;

}
