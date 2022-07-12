package com.doaction.demo.common.enums;

import java.nio.charset.Charset;

public enum CharEnum {

	/**
	 * UTF-8
	 */
	UTF_8("UTF-8"),

	/**
	 * UTF-16
	 */
	UTF_16("UTF-16"),

	/**
	 * UTF-16BE
	 */
	UTF_16BE("UTF-16BE"),

	/**
	 * UTF-16LE
	 */
	UTF_16LE("UTF-16LE"),

	/**
	 * ISO-8859-1
	 */
	ISO_8859_1("ISO-8859-1"),

	/**
	 * US-ASCII
	 */
	US_ASCII("US-ASCII"),

	/**
	 * GB2312
	 */
	GB2312("GB2312"),

	/**
	 * GBK - 微软对GB2312的扩展
	 */
	GBK("GBK"),
	/**
	 * GB18030 - 取代了GBK 1.0的正式国家标准
	 */
	GB18030("GB18030");

	private Charset charset;
	private String encoding;

	CharEnum(String encoding) {
		this.encoding = encoding;
		this.charset = Charset.forName(this.encoding);
	}

	public Charset get() {
		return this.charset;
	}

	public String getCode() {
		return this.encoding;
	}

	public static CharEnum getEnumByCode(String code) {

		for (CharEnum e : CharEnum.values()) {
			if (e.getCode().equals(code)) {
				return e;
			}
		}

		throw new IllegalArgumentException("Can't find the CharsetEnum Ojbect by Code");
	}

	public static boolean isSupported(String code) {

		for (CharEnum e : CharEnum.values()) {
			if (e.getCode().equalsIgnoreCase(code)) {
				return true;
			}
		}
		return false;
	}
}