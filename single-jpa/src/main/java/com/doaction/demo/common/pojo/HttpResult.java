package com.doaction.demo.common.pojo;

import lombok.Data;

@Data
public class HttpResult {

    private int status;

    private String result;

    private String message;

    public HttpResult() {
    }

    public HttpResult(int statusCode, String result, String message) {
        this.status = statusCode;
        this.result = result;
        this.message = message;
    }

}
