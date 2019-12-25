package com.cn.phoenix.api.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.pojo.User;
import com.cn.phoenix.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 自定义的token拦截器
 *
 * @author mac
 * @date
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object object) {

        //从header头获取token
        String token = httpServletRequest.getHeader("API-Token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 401);
            HandlerMethod handlerMethod = (HandlerMethod) object;
            //获取方法
            Method method = handlerMethod.getMethod();

            //检查有没有需要用户权限的注解 @UserLoginToken

            //UserLoginToken注解是否在方法上
            if (method.isAnnotationPresent(UserLoginToken.class)) {
                //获得UserLoginToken注解
                UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
                if (userLoginToken.required()) {
                    // 开始认证
                    if (token == null) {
                        jsonObject.put("message", "没有token，请重新登录");
                        returnJson(httpServletResponse, jsonObject);
                        return false;
                    }
                    // 拿到token中的userId
                    String username = null;
                    try {
                        //解码token
                        DecodedJWT jwt = JWT.decode(token);
                        username = jwt.getClaim("username").asString();
                    } catch (Exception e) {
                        jsonObject.put("message", "token不合法");
                        returnJson(httpServletResponse, jsonObject);
                        return false;
                    }

                    User user = userService.findUserByUsername(username);

                    if (user == null) {
                        jsonObject.put("message", "用户不存在，请检查");
                        returnJson(httpServletResponse, jsonObject);
                        return false;
                    }
                    //验证token
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getSecretKey())).build();
                    try {
                        jwtVerifier.verify(token);
                    } catch (Exception e) {
                        jsonObject.put("message", "登录已过期");
                        returnJson(httpServletResponse, jsonObject);
                        return false;
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void returnJson(HttpServletResponse response, JSONObject json) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.append(json.toJSONString());
        } catch (Exception ignored) {

        }
    }

}
