package io.github.issowl.gateway.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JWTUtils {
    public static final String SECRET_KEY = "blkmkt_secret_key"; // 秘钥
    public static final long TOKEN_EXPIRE_TIME = 15 * 60 * 1000; // token过期时间
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 30 * 60 * 1000; // refresh token过期时间
    private static final String ISSUER = "issuer"; // 签发人

    public static String generateToken(String studentId){
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); // 算法

        // Token用TOKEN_EXPIRE_TIME
        // redis用REFRESH_TOKEN_EXPIRE_TIME
        return JWT.create()
            .withIssuer(ISSUER) // 签发人
            .withIssuedAt(now) // 签发时间
            .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME)) // 过期时间
            .withClaim("studentId", studentId) // 保存身份标识
            .sign(algorithm);
    }

    public static void verify(String token){
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); // 算法
        JWTVerifier verifier = JWT.require(algorithm)
            .withIssuer(ISSUER)
            .build();
        verifier.verify(token);
    }

    public static String getStudentId(String token){
        return JWT.decode(token).getClaim("studentId").asString();
    }
}
