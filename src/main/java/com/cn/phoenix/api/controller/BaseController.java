package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.pojo.ItemsPojo;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.github.pagehelper.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lupq
 * @date 2019/11/20 10:31
 */
class BaseController<T> {

    APIResponse<ItemsPojo> isPageNull(Integer page, Integer limit) {
        if (page == null || limit == null) {
            return APIResponse.getErrorResponse(ResponseCode.PARAMETER_LACK, "page/limit");
        }
        return null;
    }

    APIResponse<ItemsPojo> getList(Integer page, Integer limit, List<T> list) {
        APIResponse<ItemsPojo> apiResponse = APIResponse.getSuccResponse();
        ItemsPojo<T> itemsPojo = new ItemsPojo<>();

        if (page != null && limit != null) {
            itemsPojo.setPage(page);
            itemsPojo.setLimit(limit);
        }
        if (list != null) {
            long total = 0;
            if (page != null && limit != null) {
                total = ((Page<T>) list).getTotal();
                itemsPojo.setTotal(total);
            } else {
                itemsPojo.setTotal(list.size());
            }

            itemsPojo.setItems(list);
            apiResponse.setData(itemsPojo);
            return apiResponse;
        }
        itemsPojo.setTotal(0);
        apiResponse.setData(itemsPojo);
        return apiResponse;
    }

}
