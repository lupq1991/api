package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.pojo.Dictionary;
import com.cn.phoenix.api.pojo.ItemsPojo;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lupq
 * @date 2019/11/1 22:03
 */
@RestController
public class DictionaryController {
    @Autowired
    DictionaryService dictionaryService;

    @UserLoginToken
    @RequestMapping(value = "/dict")
    public APIResponse<ItemsPojo> getDict(String category) {
        List<Dictionary> dictionary = dictionaryService.findDictByCategory(category);
        APIResponse<ItemsPojo> apiResponse = APIResponse.getSuccResponse();

        ItemsPojo<Dictionary> itemsPojo = new ItemsPojo<>();
        itemsPojo.setItems(dictionary);

        apiResponse.setData(itemsPojo);
        return apiResponse;
    }

}
