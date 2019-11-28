package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.pojo.Header;
import com.cn.phoenix.api.pojo.HeaderList;
import com.cn.phoenix.api.pojo.ItemsPojo;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.service.HeaderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author lupq
 * @date 2019/11/14 10:52
 */
@RestController
@RequestMapping(value = "/header")
public class HeaderController {

    @Autowired
    HeaderService headerService;

    @UserLoginToken
    @ApiOperation(value = "获取请求头", notes = "获取请求头")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public APIResponse<ItemsPojo> getHeader(Integer caseId) {
        List<Header> headerList = headerService.selectByCaseId(caseId);
        ItemsPojo<Header> itemsPojo = new ItemsPojo<>();
        itemsPojo.setItems(headerList);
        APIResponse<ItemsPojo> apiResponse = APIResponse.getSuccResponse();
        apiResponse.setData(itemsPojo);
        return apiResponse;
    }

    @UserLoginToken
    @ApiOperation(value = "添加请求头", notes = "添加请求头")
    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    public APIResponse<ItemsPojo> handleHeader(@RequestBody HeaderList headers) {
        List<Header> headerList = headers.getHeaderList();
        List<Header> headerListDB = headerService.selectByCaseId(0);

        List<Header> addHeaderList = new ArrayList<>();
        List<Header> updateHeaderList = new ArrayList<>();

        Set<String> set = new HashSet<>();

        for (Header header : headerList) {
            if (header.getCaseId() == null) {
                header.setCaseId(0);
            }
            if (header.getId() == null) {
                //这里数据要新增
                addHeaderList.add(header);
            } else {
                updateHeaderList.add(header);
                set.add(header.getId() + "");
            }
        }

        // 使用LinkedList方便插入和删除
        List<Header> deleteHeaderList = new LinkedList<>(headerListDB);
        // 如果set中包含id则remove
        deleteHeaderList.removeIf(item -> set.contains(item.getId() + ""));

//上面代码与下面功能一样
        //迭代器遍历listA
//        Iterator<Header> iter = res.iterator();
//        while (iter.hasNext()) {
//            Header item = iter.next();
//            //如果set中包含id则remove
//            if (set.contains(item.getId() + "")) {
//                iter.remove();
//            }
//        }


        if (deleteHeaderList.size() > 0) {
            headerService.batchDelete(deleteHeaderList);
        }
        //新增
        if (addHeaderList.size() > 0) {
            headerService.batchInsert(addHeaderList);
        }
        // 更新
        if (updateHeaderList.size() > 0) {
            headerService.batchUpdate(updateHeaderList);
        }


        return APIResponse.getSuccResponse();
    }

    @UserLoginToken
    @ApiOperation(value = "添加请求头", notes = "添加请求头")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public APIResponse<Header> add(@RequestBody HeaderList headers) {
        List<Header> headerList = headers.getHeaderList();
        headerService.batchInsert(headerList);
        return APIResponse.getSuccResponse();
    }

}
