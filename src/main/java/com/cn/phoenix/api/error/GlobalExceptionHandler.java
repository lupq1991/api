package com.cn.phoenix.api.error;


import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice(basePackages = "com.cn.phoenix.api.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Map<String, Object> resultError() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errorCode", 500);
        map.put("errorMsg", "系统内部错误123!");
        return map;
    }

}
