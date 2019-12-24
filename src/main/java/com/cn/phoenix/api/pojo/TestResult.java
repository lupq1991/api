package com.cn.phoenix.api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lupq
 * @date 2019/11/10 10:53
 */
@Data
public class TestResult {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer apiId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String runHost;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date startTime;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date endTime;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String path;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer caseId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer hostId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> projectIdList;

    private String apiName;
    private String caseName;
    /**
     * 最初的参数
     */
    private String parameter;

    /**
     * 替换变量后的参数
     */
    private String parameterVariable;
    /**
     * 参数md5加密
     */
    private String parameterMD5;
    /**
     * 参数aes加密
     */
    private String parameterAES;

    private String responseTime;
    private Integer pass;
    /**
     * 返回的结果
     */
    private String result;
    /**
     * 结果解密
     */
    private String resultDecryption;
    private String runHostName;
    private String url;
    private String expected;
    private String check;
    private String batchId;
    private Date createTime;
    private Date updateTime;

}
