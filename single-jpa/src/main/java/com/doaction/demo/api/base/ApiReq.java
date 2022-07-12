package com.doaction.demo.api.base;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ApiReq implements Serializable {

    private static final long serialVersionUID = 9216354136713685159L;

    /**
     * 请求流水号
     */
    private String reqNo = UUID.randomUUID().toString().replace("-", "");

}