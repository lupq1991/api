package com.cn.phoenix.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Parameter {
    private Integer id;

    private Integer caseId;

    private String key;

    private String value;

    private String jsonStr;

    private String info;

    private Date createTime;

    private Date updateTime;

}