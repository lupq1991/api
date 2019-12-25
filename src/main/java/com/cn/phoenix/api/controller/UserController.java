package com.cn.phoenix.api.controller;

import com.cn.phoenix.api.annotation.UserLoginToken;
import com.cn.phoenix.api.pojo.User;
import com.cn.phoenix.api.result.APIResponse;
import com.cn.phoenix.api.result.ResponseCode;
import com.cn.phoenix.api.service.TokenService;
import com.cn.phoenix.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lupq
 * @date 2019/12/21 20:01
 */
@RestController
public class UserController {

    final
    private UserService userService;

    final
    private TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public APIResponse<User> login(@RequestBody User requestUser, HttpSession httpSession) {
        User userForBase = userService.findByUsername(requestUser);

        if (userForBase == null) {
            return APIResponse.getErrorResponse(ResponseCode.USER_IS_NO, "");
        }
        if (!userForBase.getPassword().equals(requestUser.getPassword())) {
            return APIResponse.getErrorResponse(ResponseCode.USER_PASSWORD_NO, "");
        }

        // 获取token
        String token = tokenService.getToken(userForBase.getSecretKey(), userForBase.getUsername());
        // 把user存到session中
        httpSession.setAttribute("user", userForBase.getUsername());
        httpSession.setAttribute("userId", userForBase.getId());

        userForBase.setToken(token);
        APIResponse<User> apiResponse = APIResponse.getSuccResponse();
        apiResponse.setData(userForBase);
        return apiResponse;
    }


    @RequestMapping("/user/info")
    public APIResponse<Map<String, Object>> roles(String token) {
        List<String> rolesList = new ArrayList<>();
        rolesList.add("admin");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("roles", rolesList);
        dataMap.put("name", "admin123");
        dataMap.put("avatar", "https://cn.bing.com/th?id=OIP.6LX2dYTITTLi6BVo0jWFbAHaLH&pid=Api&rs=1");
        dataMap.put("introduction", "https://cn.bing.com/th?id=OIP.6LX2dYTITTLi6BVo0jWFbAHaLH&pid=Api&rs=1");

        APIResponse<Map<String, Object>> apiResponse = APIResponse.getSuccResponse();
        apiResponse.setData(dataMap);
        return apiResponse;
    }

    @UserLoginToken
    @ApiOperation(value = "登录", notes = "登录")
    @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
    public APIResponse<Map> logout() {
        return APIResponse.getSuccResponse();
    }

    @ResponseBody
    @UserLoginToken
    @RequestMapping("/getMessage")
    public String getMessage() {
        return "通过验证！";
    }

}
