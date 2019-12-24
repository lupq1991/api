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


    public List<Host> findRunHost(Integer page, Integer limit,Host host) {
        if (page != null && limit != null) {
            BaseService<Host> baseService = new BaseService<>();
            Page<Host> pageNum = baseService.startPage(page, limit);
            hostMapper.findRunHost(host);
            return baseService.page(pageNum);
        }
        return hostMapper.findRunHost(host);
    }

    public int insert(Host host) {
        return hostMapper.insertSelective(host);
    }

    public List<Host> findHostByRunHost(Host host) {
        return hostMapper.findHostByRunHost(host.getRunHost());
    }

    public Host selectById(Host host) {
        return hostMapper.selectById(host.getId());
    }

    public Host selectHostAndConfigByHostId(int hostId) {
        return hostMapper.selectHostAndConfigByHostId(hostId);
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
