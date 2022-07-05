package com.doaction.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.Date;

@Component
public class ToolsUtils {

	private static Logger logger = LoggerFactory.getLogger(ToolsUtils.class);

	private static MessageDigest md5 = null;
	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			logger.error("Error happens when we get MD5 instance");
		}
	}
	
	/**
	 * Generate md5 str as unique id based on baseStr + timestamp
	 * 
	 * @param baseStr
	 * @return (airdia:baseStr:hashcode)
	 */
	public static String generateName(String baseString) {
		String timeString = String.valueOf(new Date().getTime());
		String base = baseString + timeString;
		String md5String = generateMd5Str(base);

		StringBuilder out = new StringBuilder();
		out.append("airdia:");
		out.append(baseString);
		out.append(":");
		out.append(md5String);
		String id = out.toString(); 
		return id;
	}
	
	public static String generateMd5Str(String base) {
		byte[] bs = md5.digest(base.getBytes());
		StringBuilder sb = new StringBuilder();
		for (byte x : bs) {
			if ((x & 0xff) >> 4 == 0) {
				sb.append("0").append(Integer.toHexString(x & 0xff));
			} else {
				sb.append(Integer.toHexString(x & 0xff));
			}
		}
		return sb.toString();
	}
	
    public static String getIpAddress(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");  
        if (ip != null && !"".equals(ip) && !"unKnown".equalsIgnoreCase(ip)) {
             // 多次反向代理后会有多个ip值，第一个ip才是真实ip  
            int index = ip.indexOf(",");  
            if (index != -1) {  
                return ip.substring(0, index);  
            } else {  
                return ip;  
            }  
        }  
        ip = request.getHeader("X-Real-IP");  
        if (ip != null && !"".equals(ip) && !"unKnown".equalsIgnoreCase(ip)) {  
           return ip;  
        }  
        return request.getRemoteAddr();  
    } 
}
