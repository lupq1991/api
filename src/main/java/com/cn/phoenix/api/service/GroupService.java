package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.GroupMapper;
import com.cn.phoenix.api.pojo.Group;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lupq
 * @date 2019/12/21 21:56
 */
@Service
public class GroupService {

    final
    private GroupMapper groupMapper;

    @Autowired
    public GroupService(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public List<Group> selectGroupByUser(Integer page, Integer limit, Integer userId) {

        if (page != null && limit != null) {
            BaseService<Group> baseService = new BaseService<>();
            Page<Group> pageNum = baseService.startPage(page, limit);
            groupMapper.selectGroupByUser(userId);
            return baseService.page(pageNum);
        }
        return groupMapper.selectGroupByUser(userId);
    }

    public int oneInsert(Group group) {
        return groupMapper.oneInsert(group);
    }

    public int oneUpdate(Group group) {
        return groupMapper.oneUpdate(group);
    }

    public int oneDelete(Group group) {
        return groupMapper.oneDelete(group);
    }

    public List<Group> selectGroupHaveOtherUser(Group group) {
        return groupMapper.selectGroupHaveOtherUser(group);
    }

    public Group selectById(Group group) {
        return groupMapper.selectById(group);
    }
}
