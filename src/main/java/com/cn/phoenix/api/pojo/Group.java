package com.cn.phoenix.api.pojo;

import com.cn.phoenix.api.enumeration.DictCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author lupq
 * @date 2019/12/21 20:01
 */
@Data
public class Group {
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer userId;
    private String name;
    private Integer status;
    private String statusName;
    private String info;
    private Date createTime;
    private Date updateTime;

    public String getStatusName() {
        if (status == null) {
            statusName = DictCode.SwitchCode.getText(0);
        } else {
            statusName = DictCode.SwitchCode.getText(status);
        }
        return statusName;
    }
}
