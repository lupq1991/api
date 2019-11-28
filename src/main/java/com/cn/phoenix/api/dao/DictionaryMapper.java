package com.cn.phoenix.api.dao;

import com.cn.phoenix.api.pojo.Dictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictionaryMapper {

    List<Dictionary> findDictByCategory(String category);

    Dictionary findNameByCategoryAndValue(Dictionary dictionary);

}
