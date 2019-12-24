package com.cn.phoenix.api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String realName;

    private Integer createUserId;

    private Integer status;
    private String token;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String secretKey;

    private Date createTime;

    private Date updateTime;
}