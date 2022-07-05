package com.doaction.demo.common.constants;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

/**
 * @author wuxy
 *
 */
public class Consts {
	public static final long SECOND_M_S = 1000L;
	public static final long MINIUTE_M_S = 60L * SECOND_M_S;
	public static final long HOUR_M_S = 60L * MINIUTE_M_S;
	public static final long DAY_M_S = 24L * HOUR_M_S;

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String MONTH_DATE_FORMAT = "yyyy-MM";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String TIME_STAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * OID根节点
	 */
	public static final String OID_ROOT_PREFIX = "2.16.156.3016";

	public static final String OID_ROOT_PREFIX_DOT = "2.16.156.3016.";

	public static final Integer COMPANY_CODE_LENGTH = 6;

	public static final Integer OID_LENGTH = 10;

//	public static final Long PUBLIC_TIME = 20 * DAY_M_S;
//	public static final String PUBLIC_TIME_VALUE = “20天”;
// TODO 上正式环境要修改这个时间
	public static final Long PUBLIC_TIME = 5 * MINIUTE_M_S;
	public static final String PUBLIC_TIME_VALUE = "5分钟";

	public static String getFileType(String fileType){
		String type = "file";
		switch (fileType) {
			case "1":
				type = "pem";
				break;
			case "2":
				type = "image";
				break;
			case "3":
				type = "file";
				break;
			case "4":
				type = "excel";
				break;
			default:
				break;
		}
		return type;
	}

	public static String toUtf8String(String fileName, HttpServletRequest request) throws Exception {
		final String userAgent = request.getHeader("USER-AGENT");
		String finalFileName = null;
		if (StringUtils.contains(userAgent, "MSIE")||StringUtils.contains(userAgent, "Trident")) {// IE浏览器（旧版/新版）
			finalFileName = URLEncoder.encode(fileName, "UTF8");
		} else if (StringUtils.contains(userAgent, "Mozilla")) { // google,火狐浏览器
			finalFileName = URLEncoder.encode(fileName, "UTF8");
		} else {
			finalFileName = URLEncoder.encode(fileName, "UTF8");// 其他浏览器
		}
		return finalFileName;
	}

	/**
	 * create by:  zhouxq
	 * description: 生成验证码
	 * create time: 2022/5/19/019 1:13
	 *
	 * @return java.lang.String
	 * @params [length]
	 */
	public static String generateCodeToString(int length) {
		length = Math.min(length, 9);
		length = Math.max(length, 1);
		int code = (int) ((Math.random() * 9 + 1) * (int) Math.pow(10, length - 1));
		return code + "";
	}
}
