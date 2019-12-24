package com.cn.phoenix.api.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Util {

    public static void main(String[] args) {
        String jsonData = "{\"param\":{\"idCard\":198000090022,\"phone\":\"13899990000\",\"name\":\"punch\"},callbackUrl:\"https://customer.com/receiver\"}";
        String key = "param:phone,idCard";
        String a = stringToMD5(jsonData, key);
        System.out.println(a);
    }

    private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);

    public static String stringToMD5(String jsonData, String md5Keys) {
        if (md5Keys.contains(":")) {
            String[] allArray = md5Keys.split(":");
            return stringToMD5(jsonData, allArray[1], allArray[0]);
        }
        String[] keysArray = md5Keys.split(",");
        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            String key = getString(keysArray, jsonObject);
            if (key != null) {
                return key;
            }
            return jsonObject.toString();
        } catch (Exception e) {
            logger.error("文本值不是json格式:【{}】", jsonData, e);
        }
        return jsonData;
    }

    /**
     * 替换md5，可处理多级json
     *
     * @param jsonData
     * @param md5Keys  要替换的key值
     * @param param    下级key
     * @return
     */

    private static String stringToMD5(String jsonData, String md5Keys, String param) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            String params = jsonObject.getString(param);
            JSONObject paramsJson = JSONObject.parseObject(params);
            String[] md5KeyList = md5Keys.split(",");
            String key = getString(md5KeyList, paramsJson);
            if (key != null) {
                return key;
            }
            jsonObject.put(param, paramsJson);
            return jsonObject.toString();

        } catch (Exception e) {
            logger.error("文本值不是json格式:【{}】", jsonData, e);
        }

        return jsonData;
    }

    private static String getString(String[] keysArray, JSONObject jsonObject) {
        for (String key : keysArray) {
            String keyToValue = jsonObject.getString(key);
            if (keyToValue == null) {
                return "要替换的参数有问题，请检查！" + key;
            }
            jsonObject.put(key, encryptToMD5Lowercase32(keyToValue));
        }
        return null;
    }


    /**
     * @param st
     * @return 返回MD5 32位小写字符串
     */
    private static String encryptToMD5Lowercase32(String st) {
        return DigestUtils.md5Hex(st);
    }

    /**
     * @param st
     * @return 返回MD5 32位大写字符串
     */

    private static String encryptToMD5UpperCase32(String st) {
        return DigestUtils.md5Hex(st).toUpperCase();
    }

    /**
     * @param st
     * @return 返回MD5 16位小写字符串
     */
    private static String encryptToMD5Lowercase16(String st) {
        return DigestUtils.md5Hex(st).substring(8, 24);
    }

    /**
     * @param st
     * @return 返回MD5 16位小写字符串
     */
    private static String encryptToMD5UpperCase16(String st) {
        return DigestUtils.md5Hex(st).substring(8, 24).toUpperCase();
    }
}
