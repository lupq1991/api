package com.cn.phoenix.api.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lupq
 * @date 2019/10/30 17:20
 */
@Data
public class ItemsPojo<T> {

    private long total;
    private long page;
    private long limit;
    private List<T> items = new ArrayList<>();

}
