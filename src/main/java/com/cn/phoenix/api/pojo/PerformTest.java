package com.cn.phoenix.api.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PerformTest {

    //返回值忽略runHostId apiId caseId

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer runHostId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer apiId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer caseId;
    private String runHostName;
    private String apiName;
    private String caseName;
    private String result;
    private String time;
    private String url;
    private String parameter;
    private String expected;
    private boolean pass;
    private List<Api> apiList = new ArrayList<>();
    private List<TestResult> queryList = new ArrayList<>();


    public List<TestResult> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<TestResult> queryList) {
        this.queryList = queryList;
    }

    public boolean isPass() {
        return pass;
    }

    public List<Api> getApiList() {
        return apiList;
    }

    public void setApiList(List<Api> apiList) {
        this.apiList = apiList;
    }

    public Integer getRunHostId() {
        return runHostId;
    }

    public void setRunHostId(Integer runHostId) {
        this.runHostId = runHostId;
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

    public String getRunHostName() {
        return runHostName;
    }

    public void setRunHostName(String runHostName) {
        this.runHostName = runHostName;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public boolean getPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
