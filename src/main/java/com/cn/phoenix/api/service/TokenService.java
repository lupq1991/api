package com.cn.phoenix.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    /**
     * @param secret   私钥
     * @param username 用户名
     * @return 返回token
     */

    public String getToken(String secret, String username) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000 * 24;
        Date end = new Date(currentTime);
        String token;

        //设置签名的加密算法：HMAC256
        Algorithm algorithm = Algorithm.HMAC256(secret);

        token = JWT.create()
                .withClaim("username", username)
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(algorithm);
        return token;
    }



//    public String revokeToken(String token){
//
//    }


}
