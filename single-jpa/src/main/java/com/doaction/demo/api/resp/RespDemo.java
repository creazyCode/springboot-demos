package com.doaction.demo.api.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RespDemo {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="类型名称")
	private String name;

    @ApiModelProperty(value="类型编码")
	private Long code;
}
