package com.cloud.jack.core.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Collection;
import java.util.Date;

/**
 * JWT工具类
 *
 * @Author Leo
 */
public class JwtTokenUtils {

    /**
     * 授权头部
     */
    public final static String AUTHORIZATION = "Authorization";

    /**
     * 票据
     */
    public final static String BEARER = "Bearer ";

    /**
     * 签名盐
     */
    public final static String secret = "LB7H";

    /**
     * 过期时间，0表示永远不会过期
     */
    public final static Long expiration = 0L;

    /**
     * 权限
     */
    public final static String AUTHORS = "authors";

    /**
     * 用户名
     */
    public final static String USERNAME = "userName";

    /**
     * 创建token
     *
     * @param id      用户id
     * @param subject 登录名
     * @param authors 权限
     * @return
     */
    public static String createToken(String id, String userName, String subject, Collection authors) {
        return Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secret)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000 * 5))
                .claim(AUTHORS, authors)
                .claim(USERNAME, userName)
                .compact();
    }

    /**
     * 解析Token
     *
     * @param token 令牌
     * @return
     */
    public static Claims parseToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            // 处理验证失效 例如过期时间
        }
        return claims;
    }

    /**
     * 刷新token
     *
     * @param token 令牌
     * @return
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = parseToken(token);
            refreshedToken = createToken(claims.getId(), (String) claims.get(USERNAME), claims.getSubject(), (Collection) claims.get(AUTHORS));
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

}
