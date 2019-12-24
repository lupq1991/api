package com.cn.phoenix.api.pojo;

import com.cn.phoenix.api.enumeration.DictCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author lupq
 * @date 2019/12/9 16:40
 */
@Data
public class Project {

    private Integer id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> projectIdList;

    private String name;

    private String version;

    private Integer type;

    private Integer groupId;

    private String groupName;

    private String typeName;

    private Integer status;

    private String statusName;

    private List<Api> apiList;

    private String info;

    private Date createTime;

    private Date updateTime;

    public String getTypeName() {
        if (type == null) {
            typeName = DictCode.ProjectType.getText(0);
        } else {
            typeName = DictCode.ProjectType.getText(type);
        }
        return typeName;
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
