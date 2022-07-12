package com.doaction.demo.api.req;

import com.doaction.demo.api.base.req.BaseReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class ReqDemo extends BaseReq {

    @ApiModelProperty(value="密钥描述(字符限制长度150)",required = true)
    @NotNull(message = "[密钥描述]字段不能为空")
    @Length(max = 150,message = "描述内容字符长度不能超过150")
    private String accessDesc;

}
