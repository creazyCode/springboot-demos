package com.doaction.demo.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.doaction.demo.common.constants.Consts;
import com.doaction.demo.common.constants.JwtKeys;
import com.twz.base.constants.TokenConsts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {

    private static final String jwt_secret = "This secret is Used for Signing userToken";
    private static final String JWT_ISSUER = "Demo Project Issuer";
    private static final String issuer = "etet";

    public static final long EXPIRE_TIME = 30 * Consts.MIN_M_S;

	private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public static Map<String, Object> getDataMapByToken(String token) {
        Map<String, Object> dataMap = new HashMap<String, Object>();

        if (null == token || "".equals(token.trim())) {
            return null;
        }

        try {
            final JWTVerifier verifier = new JWTVerifier(secret);
            final Map<String, Object> claims = verifier.verify(token);

            final long iat = System.currentTimeMillis() / 1000L;

            Long exp = Long.valueOf(claims.get("exp").toString());
            if (iat <= exp) {

                dataMap.put(TokenConsts.USER_NO, claims.get("userNo").toString());
                dataMap.put(TokenConsts.ACCOUNT_ADDR, claims.get("addr").toString());
                if(claims.get("channelType")!=null){
                    dataMap.put(TokenConsts.CHANNEL_TYPE, claims.get("channelType").toString());
                }
                if(claims.get(TokenConsts.EMAIL)!=null){
                    dataMap.put(TokenConsts.EMAIL, claims.get(TokenConsts.EMAIL).toString());
                }

                return dataMap;
            }
        } catch (Exception e) {
            // Invalid Token
            logger.error("{}", e);
        }
        return null;
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

    public static Boolean verify(String token) {
        try {
            final JWTVerifier verifier = new JWTVerifier(secret);
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("{}", e);
        }
        return false;
    }

    public static Long getUserNoByToken(String token) {
        try {
            final JWTVerifier verifier = new JWTVerifier(secret);
            final Map<String, Object> claims = verifier.verify(token);

            Long uid = Long.valueOf(claims.get("uid").toString());
            return uid;
        } catch (Exception e) {
            log.error("{}", e);
        }
        return -1L;
    }

}
