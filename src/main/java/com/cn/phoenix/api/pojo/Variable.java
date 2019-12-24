package com.cn.phoenix.api.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author lupq
 * @date 2019/11/21 14:17
 */
@Data
public class Variable {
    private Integer id;
    private Integer isSystem;
    private Integer caseId;
    private Integer hostId;
    private String hostName;
    private String caseName;
    private boolean useResultShow;
    private String key;
    private String value;
    private Integer status;
    private String info;
    private Date createTime;
    private Date updateTime;


    public boolean isUseResultShow() {
        return getCaseId() != null;
    }


}
