package com.cn.phoenix.api.pojo;

import com.cn.phoenix.api.result.DictCode;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Cases {
    private Integer id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer caseId;

    private String name;

    private Integer apiId;

    private Integer request;

    private Integer content;

    private List<Parameter> parameter = new ArrayList<>();
    private List<Header> headers = new ArrayList<>();
    private Check check;

    private String isResultVariable;
    private Integer isUseResult;


    private Integer status;

    private String info;
    private String requestName;
    private String contentName;
    private String statusName;

    private Date createTime;

    private Date updateTime;


    public String getIsResultVariable() {
        return isResultVariable;
    }

    public void setIsResultVariable(String isResultVariable) {
        this.isResultVariable = isResultVariable;
    }

    public Integer getIsUseResult() {
        return isUseResult;
    }

    public void setIsUseResult(Integer isUseResult) {
        this.isUseResult = isUseResult;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getRequest() {
        return request;
    }

    public void setRequest(Integer request) {
        this.request = request;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public List<Parameter> getParameter() {
        return parameter;
    }

    public void setParameter(List<Parameter> parameter) {
        this.parameter = parameter;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRequestName() {
        if (request == null) {
            requestName = DictCode.RequestCode.getText(0);
        } else {
            requestName = DictCode.RequestCode.getText(request);
        }
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getContentName() {
        if (content == null) {
            contentName = DictCode.ContentCode.getText(0);
        } else {
            contentName = DictCode.ContentCode.getText(content);
        }
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getStatusName() {
        if (status == null) {
            statusName = DictCode.SwitchCode.getText(0);
        } else {
            statusName = DictCode.SwitchCode.getText(status);
        }
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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