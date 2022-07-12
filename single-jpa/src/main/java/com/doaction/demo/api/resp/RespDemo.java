package com.doaction.demo.api.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespDemo {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="姓名")
	private String name;

    @ApiModelProperty(value="年龄")
	private Integer age;
}
