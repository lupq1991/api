package com.cn.phoenix.api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * @author lupq
 * @date 2019/11/10 10:53
 */
public class TestResult {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer apiId;
    private String apiName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String path;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer caseId;
    private String caseName;
    private String parameter;
    private String responseTime;
    private Integer pass;
    private String result;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer hostId;
    private String runHostName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String runHost;
    private String url;
    private String expected;
    private Date createTime;
    private Date updateTime;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date startTime;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public String getRunHost() {
        return runHost;
    }

    public void setRunHost(String runHost) {
        this.runHost = runHost;
    }

    public String getRunHostName() {
        return runHostName;
    }

    public void setRunHostName(String runHostName) {
        this.runHostName = runHostName;
    }

    public String getUrl() {
        return this.runHost + this.path;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
