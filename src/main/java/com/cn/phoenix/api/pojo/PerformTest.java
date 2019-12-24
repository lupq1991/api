package com.cn.phoenix.api.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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

}
