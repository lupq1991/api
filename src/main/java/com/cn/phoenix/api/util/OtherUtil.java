package com.cn.phoenix.api.util;

import java.util.Random;

/**
 * @author lupq
 * @date 2019/12/13 11:12
 */
public class OtherUtil {

    /**
     * 返回一个随机数
     *
     * @param n 0--n范围
     * @return
     */
    public static int getRandomNum(int n) {
        Random random = new Random();
        return random.nextInt(n);
    }

    /**
     * 返回当前时间戳
     * @return
     */
    public static long getTimeStamp() {
        return System.currentTimeMillis();
    }
}
