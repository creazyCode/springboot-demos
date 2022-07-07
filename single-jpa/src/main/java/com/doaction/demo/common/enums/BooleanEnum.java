package com.doaction.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Boolean 型枚举
 */
@Getter
@AllArgsConstructor
public enum BooleanEnum {

	FALSE(0, "否"),
	TRUE(1, "是"),
	;

	private Integer code;
	private String description;

	public static BooleanEnum getEnumByCode(Integer code) {
		for (BooleanEnum statusEnum : BooleanEnum.values()) {
			if (code.equals(statusEnum.getCode())) {
				return statusEnum;
			}
		}
		return null;
	}

	public static boolean isValid(Integer code){
		boolean flag = false;
		for(BooleanEnum booleanEnum : BooleanEnum.values()){
			if(booleanEnum.getCode().equals(code)){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
