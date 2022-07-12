package com.doaction.demo.api.base.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageReq extends BaseReq {

    @ApiModelProperty(value = "pageNum, default=1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "pageSize, default=10")
    private Integer pageSize = 10;

}