package com.cn.phoenix.api.pojo;

/**
 * @author lupq
 * @date 2019/11/18 15:53
 */
public class HttpPojo {
    private String result;
    private long responseTime;
    private int httpCode;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
