package com.doaction.demo.api.base;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqBasePage extends ReqBase {

    @ApiModelProperty(value = "pageNum, default=1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "pageSize, default=12")
    private Integer pageSize = 12;

}