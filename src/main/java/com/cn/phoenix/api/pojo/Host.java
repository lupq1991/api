package com.cn.phoenix.api.pojo;

import com.cn.phoenix.api.result.DictCode;
import lombok.Data;

import java.util.Date;

@Data
public class Host {
    private Integer id;

    private String runHost;

    private String runName;
    private String value;

    private Integer groupId;

    private Integer status;
    private String statusName;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRunHost() {
        return runHost;
    }

    public void setRunHost(String runHost) {
        this.runHost = runHost == null ? null : runHost.trim();
    }

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName == null ? null : runName.trim();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getValue() {
        return runName;
    }

    public void setValue(String value) {
        this.value = value;
    }
}