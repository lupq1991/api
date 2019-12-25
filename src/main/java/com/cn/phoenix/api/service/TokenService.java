package com.cn.phoenix.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.interfaces.DecodedJWT;
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

    public static String getUserByToken(String token) {
        String username = null;
        try {
            //解码token
            DecodedJWT jwt = JWT.decode(token);
            username = jwt.getClaim("username").asString();
            System.out.println("username:" + username);
        } catch (Exception e) {
            return username;
        }
        return username;
    }

    public static String token(String token, String secret) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT k = jwtVerifier.verify(token);
        String username = k.getClaim("username").asString();
        String t = k.getToken();
        String header = k.getHeader();
        String payload = k.getPayload();
        String signature = k.getSignature();
        String al = k.getAlgorithm();
        return null;
    }

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NzczMjg2NTMsImlhdCI6MTU3NzI0MjI1MywidXNlcm5hbWUiOiJ1c2VyMyJ9.YqKSbks6h5TtPzFmHpgXnCC9KTNLTvFcTIsmR_ow6XQ";
        getUserByToken(token);
        token(token,"key3");
    }

}
