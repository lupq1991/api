package com.cn.phoenix.api.pojo;

import com.cn.phoenix.api.enumeration.DictCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Data
public class Cases {
    private Integer id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer caseId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> projectIdList;

    private String name;

    private Integer apiId;

    private Integer request;

    private Integer content;
    private List<Parameter> parameter = new ArrayList<>();
    private List<Header> headers = new ArrayList<>();
    private Check check;
    private String[] isResultVariable;
    private String isResultVariableString;

    private Integer isUseResult;
    private Integer status;
    private String info;
    private String requestName;
    private String contentName;
    private String statusName;
    private Date createTime;
    private Date updateTime;

    private String md5Encryption;
    private String aesEncryption;
    private String aesDecryption;
    private boolean aesEnc;
    private boolean aesDec;
    private boolean md5Enc;
    private boolean resultVariableShow;
    private boolean useResultShow;
    private String useResultCaseName;


    public boolean isResultVariableShow() {
        return getIsResultVariable() != null;
    }


    public boolean isUseResultShow() {
        return getIsUseResult() != null;
    }


    public boolean isMd5Enc() {
        return getMd5Encryption() != null;
    }


    public boolean isAesDec() {
        return getAesDecryption() != null;
    }


    public boolean isAesEnc() {
        return getAesEncryption() != null;
    }


    public String[] getIsResultVariable() {
        if (getIsResultVariableString() != null) {

            return getIsResultVariableString().split(",");
        }
        return isResultVariable;
    }


    public String getRequestName() {
        if (request == null) {
            requestName = DictCode.RequestCode.getText(0);
        } else {
            requestName = DictCode.RequestCode.getText(request);
        }
        return requestName;
    }


    public String getContentName() {
        if (content == null) {
            contentName = DictCode.ContentCode.getText(0);
        } else {
            contentName = DictCode.ContentCode.getText(content);
        }
        return contentName;
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