package com.cn.phoenix.api.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author lupq
 * @date 2019/11/18 13:53
 */
@Data
public class Check {

    private Integer id;
    private Integer caseId;
    private Integer type;
    private Integer statusCode;
    private Integer contentType;
    private String content;
    private String regularKey;
    private String regularValue;
    private Date createTime;
    private Date updateTime;

}
