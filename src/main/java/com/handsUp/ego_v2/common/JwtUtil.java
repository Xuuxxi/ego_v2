package com.handsUp.ego_v2.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 * @author superdog07
 * @date 2022/5/5
 */
public class JwtUtil {

    private static String SIGNATURE = "ego!@^%#2580";

    /**
     * 生成token
     * @param payload
     * @return token
     */
    public static String getToken(Map<String,String> payload){
        JWTCreator.Builder builder = JWT.create();

        //添加头部
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        builder.withHeader(header);

        //加入载荷（声明）
        payload.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        //设置过期时长:48小时
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR,48);
        builder.withExpiresAt(instance.getTime());

        return builder.sign(Algorithm.HMAC256(SIGNATURE)).toString();
    }

    /**
     * 获取token载荷以验证
     * @param token
     * @param claim
     * @return
     */
    public static String verify(String token,String claim){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        return decodedJWT.getClaim(claim).asString();
    }


}
