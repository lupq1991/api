package com.cn.phoenix.api.service;

import com.cn.phoenix.api.dao.DictionaryMapper;
import com.cn.phoenix.api.pojo.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lupq
 * @date 2019/11/1 23:05
 */
@Service
public class DictionaryService {


    @Autowired
    DictionaryMapper dictionaryMapper;

    public List<Dictionary> findDictByCategory(String category) {
        return dictionaryMapper.findDictByCategory(category);
    }

    public Dictionary findNameByCategoryAndValue(Dictionary dictionary) {
        return dictionaryMapper.findNameByCategoryAndValue(dictionary);
    }
}
