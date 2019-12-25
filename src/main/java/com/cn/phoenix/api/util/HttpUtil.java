package com.cn.phoenix.api.util;

import com.alibaba.fastjson.JSONObject;
import com.cn.phoenix.api.enumeration.DictCode;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 接口执行
 */
public class HttpUtil {


    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 根据请求类型去调用接口方法
     *
     * @param url
     * @param requestType
     * @param parameter
     * @return 返回接口请求结果
     */
    public static HttpPojo doService(String url, int requestType, int contentType,
                                     String parameter, Map<String, String> headerMap) {

        HttpPojo httpPojo = new HttpPojo();
        Map<String, String> params = null;
        try {
            params = (Map<String, String>) JSONObject.parse(parameter);
        } catch (Exception e) {
            httpPojo.setResult("传递的参数有问题:" + parameter);
            logger.error("{}", e);
        }
        if (requestType == DictCode.RequestCode.POST.code()) {
            httpPojo = doPost(url, params, contentType, headerMap);
        } else if (requestType == DictCode.RequestCode.GET.code()) {
            httpPojo = doGet(url, params, headerMap);
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
        if (contentType == DictCode.ContentCode.JSON.code()) {
            return doPostByJson(url, params, headerMap);
        }
        // 表单的情况
        else if (contentType == DictCode.ContentCode.FORM.code()) {
            return doPostByForm(url, params, headerMap);
        }
        return null;
    }

    private static HttpPojo doPostByJson(String url, Map<String, String> params, Map<String, String> headerMap) {
        HttpPost httpPost = new HttpPost(url);

        addHeader(httpPost, headerMap);

        String result = null;
        long responseTime = 0;
        int code = 0;
        try {
            String jsonStr = JSONObject.toJSONString(params);
            httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
            // 发起请求,获得响应信息
            HttpClient httpClient = HttpClients.createDefault();
            long t1 = System.currentTimeMillis();
            HttpResponse httpResponse = httpClient.execute(httpPost);
            responseTime = System.currentTimeMillis() - t1;
            getAndStoreCookiesFromResponseHeader(httpResponse);
            // 接口状态码
            code = httpResponse.getStatusLine().getStatusCode();
            // 响应报文
            result = EntityUtils.toString(httpResponse.getEntity());
            logger.info("POST类型接口（json提交），返回状态码→{},返回报文→{},响应时间→{}",
                    code, result, responseTime);

        } catch (Exception e) {
            logger.error("POST类型接口（json提交）调用失败!", e);

        }
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpCode(code);
        httpPojo.setResult(result);
        httpPojo.setResponseTime(responseTime);
        return httpPojo;
    }

    private static HttpPojo doPostByForm(String url, Map<String, String> params, Map<String, String> headerMap) {
        HttpPost httpPost = new HttpPost(url);

        addHeader(httpPost, headerMap);
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
            long t1 = System.currentTimeMillis();
            HttpResponse httpResponse = httpClient.execute(httpPost);
            responseTime = System.currentTimeMillis() - t1;

//            getAndStoreCookiesFromResponseHeader(httpResponse);
            // 接口状态码
            code = httpResponse.getStatusLine().getStatusCode();
            // 响应报文
            result = EntityUtils.toString(httpResponse.getEntity());
            logger.info("POST类型接口（表单提交），返回状态码→{},返回报文→{},响应时间→{}",
                    code, result, responseTime);
        } catch (Exception e) {
            logger.info("{}", e);
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
    private static HttpPojo doGet(String url, Map<String, String> params, Map<String, String> headerMap) {
        Set<String> keys = params.keySet();
        int mark = 1;
        StringBuilder urlBuilder = new StringBuilder(url);
        for (String name : keys) {
            if (mark == 1) {
                urlBuilder.append("?").append(name).append("=").append(params.get(name));
            } else {
                urlBuilder.append("&").append(name).append("=").append(params.get(name));
            }
            mark++;
        }
        url = urlBuilder.toString();
        HttpGet httpGet = new HttpGet(url);
        addHeader(httpGet, headerMap);
        HttpClient httpClient = HttpClients.createDefault();
        String result = null;
        long responseTime = 0;
        int code = 0;
        try {
            long t1 = System.currentTimeMillis();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            responseTime = System.currentTimeMillis() - t1;
            code = httpResponse.getStatusLine().getStatusCode();
            result = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            logger.info("{}", e);
        }
        logger.info("get请求的url→{},返回状态码→{},返回报文→{},响应时间→{}",
                url, code, result, responseTime);
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpCode(code);
        httpPojo.setResult(result);
        httpPojo.setResponseTime(responseTime);
        return httpPojo;
    }

    private static Map<String, String> cooking = new HashMap<String, String>();


    /**
     * 添加header
     * @param httpRequest
     * @param headerMap
     */
    private static void addHeader(HttpRequest httpRequest, Map<String, String> headerMap) {
        if (headerMap.size() > 0) {
            for (String header : headerMap.keySet()) {
                String headerValue = headerMap.get(header);
                httpRequest.addHeader(header, headerValue);
            }
        }


    }

    /**
     * 响应头里添加cookie
     *
     * @param request
     */
    private static void addCookieInRequestHeaderBeforeRequest(HttpRequest request) {
        String jsessionIdCookie = cooking.get("usid");
        if (jsessionIdCookie != null) {
            request.addHeader("Cookie", jsessionIdCookie);
        }

    }

    /**
     * 获取cookie并保存
     * 这个方法应该用不到了
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
