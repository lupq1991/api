package com.cn.phoenix.api.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author lupq
 * @date 2019/12/19 21:53
 */
@Data
public class UserGroup {

    private Integer id;
    private Integer userId;
    private String userName;
    private Integer groupId;
    private String groupName;
    private Integer projectId;
    private String projectName;
    private Date createTime;
    private Date updateTime;

}
