package com.doaction.demo.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.doaction.demo.common.constants.Consts;
import com.doaction.demo.common.constants.JwtKeys;
import com.doaction.demo.common.pojo.JwtPayload;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtTokenUtil {

    //todo set config and value
    public static final String JWT_SECRET = "secret_";

    private static final String JWT_ISSUER = "Demo Project Issuer";

    public static final long EXPIRE_TIME = 30 * Consts.MIN_M_S;

    public static String sign(String userId, String roleId, String userName){
        return sign(userId, roleId, userName, JWT_SECRET);
    }

    public static String sign(String userId, String roleId, String userName, String secret) {
        final long jwtIat = System.currentTimeMillis();
        final long jwtExp = jwtIat + EXPIRE_TIME;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(JWT_ISSUER)
                .withIssuedAt(new Date(jwtIat))
                .withExpiresAt(new Date(jwtExp))
                .withClaim(JwtKeys.CID, userId)
                .withClaim(JwtKeys.RID, roleId)
                .withClaim(JwtKeys.CNAME, userName)
                .sign(algorithm);
    }

    public static JwtPayload verify(String token){
        return verify(token, JWT_SECRET);
    }

    public static JwtPayload verify(String token, String secret){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setIss(jwt.getIssuer());
        jwtPayload.setIat(jwt.getIssuedAt());
        jwtPayload.setExp(jwt.getExpiresAt());
        jwtPayload.setCid(jwt.getClaim(JwtKeys.CID).asString());
        jwtPayload.setRid(jwt.getClaim(JwtKeys.RID).asString());
        jwtPayload.setCname(jwt.getClaim(JwtKeys.CNAME).asString());
        return jwtPayload;
    }


    public static boolean valid(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
