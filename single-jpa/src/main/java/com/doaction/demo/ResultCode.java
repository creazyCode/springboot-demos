package com.doaction.demo;


import com.doaction.demo.common.error.IResultCode;

public enum ResultCode implements IResultCode {

                    USER_NOT_EXIST("U0000", "用户不存在"),
                    USER_FORBIDDEN("U0006", "账户被限制，请联系管理员"),
                  PHONE_NO_IS_NULL("U0001", "手机号为空"),
                HT_ADDRESS_INVALID("U0002", "无效地址格式"),
                 NICK_NAME_IS_NULL("U0003", "昵称为空"),
              TOKEN_STATUS_INVALID("U0004", "请先登录"),
              TOKEN_STATUS_EXPIRED("U0005", "会话已过期,请重新登录"),
    // ============================ web ================================ \\
         WEB_ACCOUNT_HAS_ACTIVATED("U1000", "账户地址已被注册"),
             WEB_ACCOUNT_NOT_FOUND("U1001", "账户不存在"),
     WEB_ACCOUNT_REVENUE_NOT_FOUND("U1002", "账户异常: 资金账户不存在"),
            WEB_CODE_INVITER_ADDED("U1011", "账户已添加过邀请码"),
          WEB_CODE_INVITER_INVALID("U1012", "邀请码无效"),
    WEB_CODE_SUBORDINATE_EACHOTHER("U1013", "邀请码无效: 互为上下级"),
          WEB_QUOTA_TYPE_NOT_FOUND("U1021", "入金额度类型无效"),
        WEB_QUOTA_AMOUNT_NOT_MATCH("U1022", "金额与算力额度不匹配"),
       WEB_TRANSACTION_HASH_REPEAT("U1031", "交易Hash重复"),
       WEB_DEPOSIT_HASH_FROM_WRONG("U1032", "交易发送方非本账户"),
         WEB_DEPOSIT_HASH_TO_WRONG("U1033", "入金账户错误"),
             WEB_ES_DEPOSIT_REPEAT("U1034", "入金失败： 算力包仅限入金一次"),
       WEB_ES_DEPOSIT_TOTAL_BEYOND("U1035", "入金失败： 算力包总额已达上限"),
            WEB_CP_CLAIM_LESS_ZERO("U1036", "领取算力数量必须大于0"),
            WEB_CP_CLAIM_NOT_FOUND("U1037", "无可领取奖励"),
          WEB_EXCHANGE_HASH_REPEAT("U1100", "兑换交易Hash重复"),

    // ============================ portal ================================ \\
             PHONE_NO_NOT_REGISTER("U2001", "该手机号未注册"),
                 USER_NOT_REGISTER("U2002", "该邮箱地址未注册，请重新注册"),
              SEND_EMAIL_CODE_FAIL("U2003", "发送邮件失败，系统正忙"),
                    WRONG_BIZ_TYPE("U2004", "发送验证码的业务类型未开通"),
             INVALID_MAIL_BIZ_CODE("U2005", "邮箱验证码无效"),
              INVALID_MAIL_ADDRESS("U2006", "邮箱地址校验失败"),
              INVALID_PHONE_FORMAT("U2007", "手机号校验失败"),
              SEND_PHONE_CODE_FAIL("U2008", "发送短信失败，系统正忙"),
             SEND_CODE_RESEND_FAIL("U2009", "验证码一分钟内不允许重发"),

        USER_GEETEST_VALIDATE_FAIL("U2029", "行为验证失败"),


    ;


	private String code;
    private String msg;

    // 构造方法
    ResultCode(String code, String msg) {
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
