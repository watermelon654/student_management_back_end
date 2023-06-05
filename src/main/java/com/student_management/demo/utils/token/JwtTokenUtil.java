package com.student_management.demo.utils.token;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.access_token.expiration}")
    private Long jwtExpiration;

    public String generateToken(Authentication authentication,String id) {
        // 生成此用户数据的JWT
        // Claims
        User user=(User) authentication.getPrincipal();
        System.out.println("从认证结果中获取Principal=" + user.getClass().getName());
        Map<String,Object> claims=new HashMap<>();
        claims.put("username",id);
        claims.put("permissions",user.getAuthorities());
        System.out.println("即将向JWT中写入数据=" + claims);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                //Header:指定算法与当前数据类型
                //格式:{"alg":算法,"typ":"jwt"}
                .setHeaderParam(Header.CONTENT_TYPE,"HS256")
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                //Payload:通常包含Claims(自定义数据)和过期时间
                .setClaims(claims)
                .setExpiration(expiryDate)
                //Signature:由算法和密钥(secret key)两部分组成
                .signWith(SignatureAlgorithm.HS256,jwtSecret)
                //打包生成
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            // 签名验证失败
        } catch (MalformedJwtException ex) {
            // JWT令牌格式错误
        } catch (ExpiredJwtException ex) {
            // JWT令牌已过期
        } catch (UnsupportedJwtException ex) {
            // 不支持的JWT令牌
        } catch (IllegalArgumentException ex) {
            // JWT令牌为空或无效
        }
        return false;
    }

    /**
     * 从token中提取username（学号/教职工号）
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token.replace(TOKEN_PREFIX,"")).get("username").toString();
    }
    public String getPermissionsFromToken(String token) {
        return getAllClaimsFromToken(token.replace(TOKEN_PREFIX,"")).get("permissions").toString();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }


}