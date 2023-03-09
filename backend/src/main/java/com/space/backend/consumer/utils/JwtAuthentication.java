package com.space.backend.consumer.utils;

import com.space.backend.utils.JwtUtil;
import io.jsonwebtoken.Claims;

public class JwtAuthentication { // web socket 判断传来的token是否存在
    public static Integer getUserId(String token){
        int userId = -1;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userId;
    }
}
