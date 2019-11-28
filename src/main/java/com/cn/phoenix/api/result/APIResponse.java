package com.cn.phoenix.api.result;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author ethan
 * @version V1.0
 * @Title APIResponse
 * @Description API应答对象
 * @date 2016年8月10日
 */
public class APIResponse<T> implements Serializable {

    private static final long serialVersionUID = -1116234667369598261L;

    private String requestId;
    private int code;
    private String message;
    private T data;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }


    /**
     * 根据 错误代码设置异常,可追加自定义信息
     *
     * @param responseCode
     * @param appendMessage
     * @return
     */
    public static <T> APIResponse<T> getErrorResponse(ResponseCode responseCode, String appendMessage) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(responseCode.code);
        response.setMessage(responseCode.message + appendMessage);
        return response;
    }


    public static <T> APIResponse<T> getSuccResponse() {
        APIResponse<T> response = new APIResponse<>();
        ResponseCode resCode = ResponseCode.SUCCESS;
        response.setCode(resCode.code);
        response.setMessage(resCode.message);
        return response;
    }

    /**
     * 自定义异常应签
     *
     * @param code
     * @param message
     * @return
     */
    public static <T> APIResponse<T> getDefErrorResponse(int code, String message) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

}
