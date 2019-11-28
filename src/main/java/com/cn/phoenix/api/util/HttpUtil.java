package com.cn.phoenix.api.util;

import com.alibaba.fastjson.JSONObject;
import com.cn.phoenix.api.pojo.HttpPojo;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * 接口执行
 */
public class HttpUtil {


    private static Logger logger = Logger.getLogger(HttpUtil.class);

    /**
     * 根据请求类型去调用接口方法
     *
     * @param url
     * @param requestType
     * @param parameter
     * @return
     */
    public static HttpPojo doService(String url, int requestType, int contentType,
                                     String parameter, Map<String, String> headerMap) {

        HttpPojo httpPojo = new HttpPojo();
        Map<String, String> params = null;
        try {
            params = (Map<String, String>) JSONObject.parse(parameter);
        } catch (Exception e) {
            httpPojo.setResult("传递的参数有问题:" + parameter);
        }
        if (requestType == 1) {
            httpPojo = doPost(url, params, contentType, headerMap);
        } else if (requestType == 2) {
            httpPojo = doGet(url, params);
        } else {
            httpPojo.setResult("接口提交方式有误，请检查！！！");
        }
        return httpPojo;
    }


    /**
     * 处理post请求的接口
     *
     * @param url
     * @param params
     * @return
     */

    private static HttpPojo doPost(String url, Map<String, String> params, int contentType, Map<String, String> headerMap) {
        // 从配置文件取出配置，这个决定了我们要以何种方式去提交参数
        if (contentType == 1) {
            return doPostByJson(url, params, headerMap);
        }
        // 表单的情况
        else if (contentType == 2) {
            return doPostByForm(url, params);
        }
        return null;
    }

    private static HttpPojo doPostByJson(String url, Map<String, String> params, Map<String, String> headerMap) {
//        logger.info("请求的接口地址：" + url);
        // 指定请求方式post
        HttpPost httpPost = new HttpPost(url);
        if (headerMap.size() > 0) {
            for (String header : headerMap.keySet()) {
                httpPost.addHeader(header, headerMap.get(header));
            }
        }

        String result = null;
        long responseTime = 0;
        int code = 0;
        try {
            String jsonStr = JSONObject.toJSONString(params);
            httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
            // 发起请求,获得响应信息
            HttpClient httpClient = HttpClients.createDefault();
            addCookieInRequestHeaderBeforeRequest(httpPost);
            long t1 = System.currentTimeMillis();
            HttpResponse httpResponse = httpClient.execute(httpPost);
            responseTime = System.currentTimeMillis() - t1;
            getAndStoreCookiesFromResponseHeader(httpResponse);
            // 接口状态码
            code = httpResponse.getStatusLine().getStatusCode();
            // 响应报文
            result = EntityUtils.toString(httpResponse.getEntity());
            logger.info("POST类型接口（json提交），返回状态码：" + code + "返回报文：" + result + "响应时间：" + responseTime);

        } catch (Exception e) {

        }
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpCode(code);
        httpPojo.setResult(result);
        httpPojo.setResponseTime(responseTime);
        return httpPojo;
    }

    private static HttpPojo doPostByForm(String url, Map<String, String> params) {
        // 指定请求方式post
        HttpPost httpPost = new HttpPost(url);
        List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        Set<String> keys = params.keySet();
        for (String name : keys) {
            String value = params.get(name);
            parameters.add(new BasicNameValuePair(name, value));
        }
        String result = null;
        long responseTime = 0;
        int code = 0;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
            // 发起请求,获得响应信息
            HttpClient httpClient = HttpClients.createDefault();
            addCookieInRequestHeaderBeforeRequest(httpPost);
            long t1 = System.currentTimeMillis();
            HttpResponse httpResponse = httpClient.execute(httpPost);
            responseTime = System.currentTimeMillis() - t1;
            getAndStoreCookiesFromResponseHeader(httpResponse);
            // 接口状态码
            code = httpResponse.getStatusLine().getStatusCode();
            // 响应报文
            result = EntityUtils.toString(httpResponse.getEntity());
            logger.info("POST类型接口（表单提交），返回状态码：" + code + "返回报文：" + result + "响应时间：" + responseTime);

        } catch (Exception e) {

        }
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpCode(code);
        httpPojo.setResult(result);
        httpPojo.setResponseTime(responseTime);
        return httpPojo;
    }

    /**
     * 处理get请求的接口
     *
     * @param url
     * @param params
     * @return
     */
    private static HttpPojo doGet(String url, Map<String, String> params) {
        Set<String> keys = params.keySet();
        int mark = 1;
        for (String name : keys) {
            if (mark == 1) {
                url += ("?" + name + "=" + params.get(name));
            } else {
                url += ("&" + name + "=" + params.get(name));
            }
            mark++;
        }
        HttpGet httpGet = new HttpGet(url);
        logger.info("get请求的url是：" + url);
        HttpClient httpClient = HttpClients.createDefault();
        String result = null;
        long responseTime = 0;
        int code = 0;
        try {
            addCookieInRequestHeaderBeforeRequest(httpGet);
            long t1 = System.currentTimeMillis();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            responseTime = System.currentTimeMillis() - t1;
            getAndStoreCookiesFromResponseHeader(httpResponse);
            code = httpResponse.getStatusLine().getStatusCode();
            result = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {

        }
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpCode(code);
        httpPojo.setResult(result);
        httpPojo.setResponseTime(responseTime);
        return httpPojo;
    }

    private static Map<String, String> cooking = new HashMap<String, String>();

    /**
     * 响应头里添加cookie
     *
     * @param Request
     */
    private static void addCookieInRequestHeaderBeforeRequest(HttpRequest Request) {
        String jsessionIdCookie = cooking.get("usid");
        if (jsessionIdCookie != null) {
            Request.addHeader("Cookie", jsessionIdCookie);
        }

    }

    /**
     * 获取cookie并保存
     *
     * @param httpResponse
     */
    private static void getAndStoreCookiesFromResponseHeader(HttpResponse httpResponse) {
        // 从响应头取出名字“Set-Cookie”的响应头
        Header setCookieHeader = httpResponse.getFirstHeader("Set-Cookie");
        // 如果不为空
        if (setCookieHeader != null) {
            // 取出此响应头的值
            String cookiePairsString = setCookieHeader.getValue();
            if (cookiePairsString != null && cookiePairsString.trim().length() > 0) {
                // 以“;”来切割
                String[] cookiePairs = cookiePairsString.split(";");
                for (String cookiePair : cookiePairs) {
                    // 如果包含了JSESSIONID
                    if (cookiePair.contains("usid")) {
                        // 保存到map
                        cooking.put("usid", cookiePair);
                    }
                }
            }
        }
    }
}
