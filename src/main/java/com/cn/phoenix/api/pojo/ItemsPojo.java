package com.cn.phoenix.api.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lupq
 * @date 2019/10/30 17:20
 */
public class ItemsPojo<T> {

    private long total;
    private long page;
    private long limit;
    private List<T> items = new ArrayList<>();

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }
}
