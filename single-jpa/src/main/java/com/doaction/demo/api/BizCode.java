package com.doaction.demo.api;


import com.doaction.demo.common.error.IBaseCode;

public enum BizCode implements IBaseCode {

                      UNAUTHORIZED("U0000", "非法访问"),
                    NOT_PERMISSION("U0001", "没有权限"),
                         NOT_FOUND("U0002", "请求的资源不存在"),
                     PARAM_ILLEGAL("U0003", "请求的参数包含非法字符"),
                HT_ADDRESS_INVALID("U0010", "登录失败"),
              TOKEN_STATUS_INVALID("U0011", "请先登录"),
              TOKEN_STATUS_EXPIRED("U0012", "会话已过期,请重新登录"),
    // ============================ web ================================ \\
         WEB_ACCOUNT_HAS_ACTIVATED("U1000", "账户地址已被注册"),
             WEB_ACCOUNT_NOT_FOUND("U1001", "账户不存在"),

    // ============================ portal ================================ \\
             PHONE_NO_NOT_REGISTER("U2001", "该手机号未注册"),
                 USER_NOT_REGISTER("U2002", "该邮箱地址未注册，请重新注册"),
    ;


	private String code;
    private String msg;

    // 构造方法
    BizCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

	public String code() {
		return code;
	}

	public String message() {
		return msg;
	}

	public boolean isSuccess() {
		return this == SUCCESS;
	}
}
