package com.cn.phoenix.api.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author lupq
 * @date 2019/11/29 16:02
 */
@Data
public class ApiConfig {
    private Integer id;
    private Integer hostId;
    private Integer isUse;
    private boolean use;
    private Integer status;
    private String aesKey;
    private String appId;
    private String secretKey;
    private String info;
    private Date createTime;
    private Date updateTime;
}
