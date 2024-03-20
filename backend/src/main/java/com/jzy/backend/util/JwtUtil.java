package com.jzy.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/3/20 19:55
 * @Description: TODO
 */
public class JwtUtil {
    /**
     *
     * 有效期
     * @create 2024/3/20
     **/
    public static final long JWT_TTL = 60 * 60 * 1000L;
    /**
     *
     * 密钥明文
     * @create 2024/3/20
     **/
    public static final String JWT_KEY = "Sanitater";

    /**
     *
     * @return jwt
     * @author jzy
     * @create 2024/3/20
     **/
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     *
     * @param subject token中要存放的数据(json格式)
     * @return builder.compact()
     * @author jzy
     * @create 2024/3/20
     **/
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID()); // 设置过期时间
        return builder.compact();
    }

    /**
     *
     * @param subject token中要存放的数据(json格式)
     * @param ttlMillis token超时时间
     * @return builder.compact()
     * @author jzy
     * @create 2024/3/20
     **/
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID()); // 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid) // 唯一的id
                .setSubject(subject) // 主题 可以是json数据
                .setIssuer("sg") // 签发者
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 使用HS256对称加密算法签名，第二个参数为密钥
                .setExpiration(expDate);
    }

    /**
     *
     * 创建token
     * @param id
     * @param subject token中要存放的数据(json格式)
     * @param ttlMillis token超时时间
     * @return builder.compact()
     * @author jzy
     * @create 2024/3/20
     **/
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id); // 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密后的密钥 secretKey
     * @return SecretKeySpec
     * @author jzy
     * @create 2024/3/20
     **/
    public static SecretKey generalKey() {
        byte[] encodeKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "HmacSHA256");
    }

    /**
     *
     * @param jwt
     * @return
     * @author jzy
     * @create 2024/3/20
     **/
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
