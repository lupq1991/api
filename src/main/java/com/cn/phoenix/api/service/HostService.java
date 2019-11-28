package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.HostMapper;
import com.cn.phoenix.api.pojo.Host;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HostService {

    @Autowired
    private HostMapper hostMapper;


    public List<Host> findRunHost(Integer page, Integer limit) {
        BaseService<Host> baseService = new BaseService<>();
        Page<Host> pageNum = baseService.startPage(page, limit);
        hostMapper.findRunHost();
        return baseService.page(pageNum);
    }

    public int insert(Host host) {
        return hostMapper.insertSelective(host);
    }

    public List<Host> findHostByRunHost(Host host) {
        return hostMapper.findHostByRunHost(host.getRunHost());
    }

    public Host selectByPrimaryKey(Host host) {
        return hostMapper.selectByPrimaryKey(host.getId());
    }

    public Host selectById(int hostId) {
        return hostMapper.selectByPrimaryKey(hostId);
    }

    public int deleteByPrimaryKey(Host host) {
        return hostMapper.deleteByPrimaryKey(host.getId());
    }

    public int updateByPrimaryKeySelective(Host host) {
        return hostMapper.updateByPrimaryKeySelective(host);
    }

    /**
     * 更新检查唯一性
     *
     * @param host
     * @return
     */
    public List<Host> checkUnique(Host host) {
        return hostMapper.checkUnique(host);
    }
}
