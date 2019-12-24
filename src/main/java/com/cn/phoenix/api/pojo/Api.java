package com.cn.phoenix.api.pojo;

import com.cn.phoenix.api.enumeration.DictCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Api {
    private Integer id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> apiIdList;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> caseIdList;
    private String name;

    private String path;

    private Integer status;

    private Integer projectId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> projectIdList;

    private List<Cases> cases = new ArrayList<>();

    private String info;

    private String statusName;

    private Date createTime;

    private Date updateTime;

    private String aesDecryption;

    private String aesEncryption;

    private boolean aesEnc;

    private boolean aesDec;


    public boolean isAesDec() {
        return getAesDecryption() != null;
    }

    public boolean isAesEnc() {
        return getAesEncryption() != null;
    }

    public String getStatusName() {
        if (status == null) {
            statusName = DictCode.SwitchCode.getText(0);
        } else {
            statusName = DictCode.SwitchCode.getText(status);
        }
        return statusName;
    }
}