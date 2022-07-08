package com.doaction.demo.common.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class JwtPayload {

    private String iss;

    private Date iat;

    private Date exp;

    /**
     * 用户唯一标识
     */
    private String cid;

    /**
     * 用户名
     */
    private String cname;

    /**
     * 用户编码(角色)
     */
    private String rid;
}
