package com.cn.phoenix.api.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author lupq
 * @date 2019/12/12 18:22
 * 测试数据
 */
@Data
public class TestData {

    private Integer id;
    private String name;
    private String idCard;
    private String phone;
    private String bankCard;
    private Integer operator;
    private Date createTime;
    private Date updateTime;

}
