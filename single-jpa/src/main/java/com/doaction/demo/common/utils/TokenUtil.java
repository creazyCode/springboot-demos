package com.doaction.demo.common.utils;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.twz.base.constants.TokenConsts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TokenUtil {

    private TokenUtil() {}

    private static final String secret = "This secret is Used for Signing userToken";
    private static final String issuer = "etet";

	private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);

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

    public static String signJWT(String uid, String addr, String code){
        // issued at claim
        final long iat = System.currentTimeMillis() / 1000L;
        // expires claim. In this case the token expires in 1800 seconds
        //final long exp = iat + 1800000000L;//30分钟
        final long exp = iat + 7*24*60*60L;//10小时

        final JWTSigner signer = new JWTSigner(secret);
        final HashMap<String, Object> claims = new HashMap<String, Object>();
        claims.put("iss", issuer);
        claims.put("exp", exp);
        claims.put("iat", iat);
        claims.put("userNo", uid);
        claims.put(TokenConsts.ACCOUNT_ADDR, addr);
        claims.put(TokenConsts.ACCOUNT_NAME, code);
        final String jwt = signer.sign(claims);
        return jwt;
    }

    public static Boolean verifyJWT(String token) {
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
