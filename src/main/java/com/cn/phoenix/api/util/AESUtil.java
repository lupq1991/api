package com.cn.phoenix.api.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AESUtil {

    private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);


    /**
     * AES加密操作
     *
     * @param aesKey AES加密所需的key
     * @param jsonData 要加密的json串
     * @param encField 要加密的字段
     * @return
     */
    public static String paramsEncryption(String aesKey, String jsonData, String encField) {
        if (encField.contains(":")) {
            String[] parameter = encField.split(":");
            return paramsEncryption(aesKey, jsonData, parameter[0], parameter[1]);
        }
        String[] parameterList = encField.split(",");
        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            for (String parameter : parameterList) {
                String keyToValue = jsonObject.getString(parameter);
                if (keyToValue == null) {
                    return "要加密的参数有问题，请检查！" + jsonData;
                }
                jsonObject.put(parameter, encryptToString(keyToValue, aesKey));
            }
            return jsonObject.toString();
        } catch (Exception e) {
            logger.error("文本值不是json格式:【{}】", jsonData, e);
        }
        return null;
    }

    /**
     * 多层json加密操作
     *
     * @param aesKey 加密所需的key
     * @param jsonData 加密的json串
     * @param
     * @return
     */
    private static String paramsEncryption(String aesKey, String jsonData, String father, String sub) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            String params = jsonObject.getString(father);
            JSONObject paramsJson = JSONObject.parseObject(params);
            String[] parameterList = sub.split(",");
            for (String parameter : parameterList) {
                String keyToValue = paramsJson.getString(parameter);
                if (keyToValue == null) {
                    return "多层json操作,要加密的参数有问题，请检查！" + parameter;
                }
                paramsJson.put(parameter, encryptToString(keyToValue, aesKey));
            }
            jsonObject.put(father, paramsJson);
            return jsonObject.toString();

        } catch (Exception e) {
            logger.error("文本值不是json格式:【{}】", jsonData, e);
        }
        return null;
    }

    /**
     * 一级json解密
     * @param aesKey AES解密所需的key
     * @param jsonData 解密的json串
     * @param decField 要解密的字段
     * @return
     */
    public static String paramsDecryption(String aesKey, String jsonData, String decField) {
        if (decField.contains(":")) {
            String[] parameter = decField.split(":");
            return paramsDecryption(aesKey, jsonData, parameter[0], parameter[1]);
        }
        String[] parameterList = decField.split(",");
        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            for (String parameter : parameterList) {
                String keyToValue = jsonObject.getString(parameter);
                if (keyToValue == null) {
                    return "要解密的参数有问题，请检查！" + jsonData;
                }
                jsonObject.put(parameter, decryptToString(keyToValue, aesKey));
            }
            return jsonObject.toString();
        } catch (Exception e) {
            logger.error("文本值不是json格式:【{}】", jsonData, e);
        }
        return null;
    }

    /**
     * 多级json解密
     * @param aesKey
     * @param jsonData
     * @param father
     * @param sub
     * @return
     */
    private static String paramsDecryption(String aesKey, String jsonData, String father, String sub) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            String params = jsonObject.getString(father);
            JSONObject paramsJson = JSONObject.parseObject(params);
            String[] parameterList = sub.split(",");
            for (String parameter : parameterList) {
                String keyToValue = paramsJson.getString(parameter);
                if (keyToValue == null) {
                    return "多层json操作,要解密的参数有问题，请检查！" + jsonData;
                }
                paramsJson.put(parameter, decryptToString(keyToValue, aesKey));
            }
            jsonObject.put(father, paramsJson);
            return jsonObject.toString();
        } catch (Exception e) {
            logger.error("文本值不是json格式:【{}】", jsonData, e);
        }
        return null;
    }


    private static Lock lock = new ReentrantLock();

    /**
     * 加密
     *
     * @param aesKey
     * @return
     */
    private static String encryptToString(String content, String aesKey) {
        lock.lock();
        SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes()));
        } catch (Exception e) {
            logger.error("AES加密异常", e);
            throw new RuntimeException("AES加密异常");
        } finally {
            lock.unlock();
        }
    }

    /**
     * 解密
     *
     * @param aesKey
     * @return
     */
    private static String decryptToString(String content, String aesKey) {
        lock.lock();
        SecretKey key = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
        } catch (Exception e) {
            logger.error("AES解密异常", e);
            throw new RuntimeException("AES解密异常");
        } finally {
            lock.unlock();
        }
    }
}
