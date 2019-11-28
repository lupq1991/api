package com.cn.phoenix.api.pojo;

import java.util.Date;

/**
 * @author lupq
 * @date 2019/11/18 13:53
 */
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegularKey() {
        return regularKey;
    }

    public void setRegularKey(String regularKey) {
        this.regularKey = regularKey;
    }

    public String getRegularValue() {
        return regularValue;
    }

    public void setRegularValue(String regularValue) {
        this.regularValue = regularValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
