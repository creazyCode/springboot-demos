package com.doaction.demo.api.base;

import com.doaction.demo.common.error.IResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回对象
 * Created by macro on 2019/4/19.
 */
@Data
public class RespBase<T> implements Serializable {

    private static final long serialVersionUID = 2520762783876870320L;

    /**
     * 状态码
     */
    private String code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据封装
     */
    private T data;

    protected RespBase() {
    }

    public RespBase(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> RespBase<T> success() {
        return new RespBase<T>(IResultCode.SUCCESS.code(), IResultCode.SUCCESS.message(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> RespBase<T> success(T data) {
        return new RespBase<T>(IResultCode.SUCCESS.code(), IResultCode.SUCCESS.message(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> RespBase<T> success(T data, String message) {
        return new RespBase<T>(IResultCode.SUCCESS.code(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> RespBase<T> failed(IResultCode errorCode) {
        return new RespBase<T>(errorCode.code(), errorCode.message(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> RespBase<T> failed(IResultCode errorCode, String message) {
        return new RespBase<T>(errorCode.code(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> RespBase<T> failed(String message) {
        return new RespBase<T>(IResultCode.SYSTEM_ERROR.code(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> RespBase<T> failed() {
        return failed(IResultCode.SYSTEM_ERROR);
    }

}
