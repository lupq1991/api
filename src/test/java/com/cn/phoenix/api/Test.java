package com.cn.phoenix.api;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lupq
 * @date 2019/12/3 15:20
 */
public class Test {

    public static void main(String[] args) {
//        String[] s = {"1", "2", "3"};
//        System.out.println(StringUtils.join(s, ","));
        String result = "{\"content\":\"{\"S2010305\":\"510\",\"outTradeNo\":\"PT_PUNCH_OUT_1204347097353609222\",\"transactionNo\":\"1204347097353609222\"}\",\"status\":200}";
        boolean t = result.toUpperCase().contains("s2010305".toUpperCase());
        System.out.println(t);
    }
}
