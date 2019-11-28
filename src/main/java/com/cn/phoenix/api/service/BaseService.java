package com.cn.phoenix.api.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

/**
 * @author lupq
 * @date 2019/11/20 13:57
 */
class BaseService<T> {

    Page<T> startPage(Integer page, Integer limit) {

        return PageHelper.startPage(page, limit);
    }

    List<T> page(Page<T> pageNum) {
        // 数据表的总行数
        pageNum.getTotal();
        // 分页查询结果的总行数
        pageNum.size();

        try {
            pageNum.get(0);

        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return pageNum;
    }
}
