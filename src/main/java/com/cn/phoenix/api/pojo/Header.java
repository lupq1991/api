package com.cn.phoenix.api.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author lupq
 * @date 2019/11/14 10:27
 */
@Data
public class Header {

    private Integer id;
    private Integer isUseResult;
    private String key;
    private String value;
    private Integer caseId;
    private Integer status;
    private String info;
    private Date createTime;
    private Date updateTime;

}
