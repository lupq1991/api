package com.cn.phoenix.api.pojo;

import lombok.Data;

/**
 * @author lupq
 * @date 2019/11/1 22:00
 */
@Data
public class Dictionary {

    private Integer id;
    private Integer pId;
    private String category;
    private String name;
    private String key;
    private String value;
    private Integer orderNo;
    private String description;

}
