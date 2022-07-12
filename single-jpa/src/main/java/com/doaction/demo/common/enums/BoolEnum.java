package com.doaction.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Boolean 型枚举
 */
@Getter
@AllArgsConstructor
public enum BoolEnum {

	FALSE(0, "否"),
	TRUE(1, "是"),
	;

	private Integer code;
	private String description;

	public static BoolEnum getEnumByCode(Integer code) {
		for (BoolEnum statusEnum : BoolEnum.values()) {
			if (code.equals(statusEnum.getCode())) {
				return statusEnum;
			}
		}
		return null;
	}

	public static boolean isValid(Integer code){
		boolean flag = false;
		for(BoolEnum booleanEnum : BoolEnum.values()){
			if(booleanEnum.getCode().equals(code)){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
