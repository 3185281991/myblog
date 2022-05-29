package com.example.myitem.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.myitem.entity.Root;
import org.springframework.util.StringUtils;

import java.util.Calendar;
public class JwtUtils {
    public static String getToken(Root root) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间3天
        instance.add(Calendar.DATE, 3);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("rid", root.getRid())
                .withClaim("username",root.getUsername())
                .withClaim("password",root.getPassword());;
        // 使用了HMAC256加密算法。
        // secret是用来加密数字签名的密钥。
        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("secret"));
    }
    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token) throws Exception {
        if(StringUtils.isEmpty(token)){
            throw new Exception("token不能为空");
        }
        JWTVerifier build = JWT.require(Algorithm.HMAC256("secret")).build();
        return build.verify(token);
    }

}
