package com.doaction.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BooleanEnum {
	PROVINCE(1, "省份"),
	CITY(2, "市区"),
	AREA(3, "区域"),
	;

	private Integer code;
	private String description;

	public static AreaTypeEnum getEnumByCode(Integer code) {
		for (AreaTypeEnum statusEnum : AreaTypeEnum.values()) {
			if (code.equals(statusEnum.getCode())) {
				return statusEnum;
			}
		}
		return null;
	}

	public static boolean isValid(Integer code){
		boolean flag = false;
		for(AreaTypeEnum reviewStatusEnum : AreaTypeEnum.values()){
			if(reviewStatusEnum.getCode().equals(code)){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
