package com.cn.phoenix.api.pojo;

import com.cn.phoenix.api.enumeration.DictCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Host {
    private Integer id;
    private String runHost;
    private String runName;
    private String value;
    private Integer projectId;
    private Integer status;
    private String statusName;
    private Date createTime;
    private Date updateTime;
    private ApiConfig apiConfig;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> projectIdList;

    public String getStatusName() {
        if (status == null) {
            statusName = DictCode.SwitchCode.getText(0);
        } else {
            statusName = DictCode.SwitchCode.getText(status);
        }
        return statusName;
    }
}