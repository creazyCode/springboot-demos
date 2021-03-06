package com.doaction.demo.api.base.resp;

import com.doaction.demo.common.error.IBaseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 通用返回对象
 * Created by macro on 2019/4/19.
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class RespData<T> implements Serializable {

    private static final long serialVersionUID = 2520762783876870320L;

    /**
     * 状态码
     */
    private String code;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据封装
     */
    private T data;

    public static <T> RespData<T> ok() {
        return ok(null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> RespData<T> ok(T data) {
        return ok(null, data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> RespData<T> ok(String message, T data) {
        return result(IBaseCode.SUCCESS, message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> RespData<T> fail(){
        return fail(IBaseCode.FAIL);
    }

    public static <T> RespData<T> fail(IBaseCode errorCode) {
        return result(errorCode, null, null);
    }

    public static <T> RespData<T> fail(IBaseCode errorCode, T data) {
        return result(errorCode, null, data);
    }

    public static <T> RespData<T> fail(String message){
        return result(IBaseCode.FAIL,message,null);
    }

    private static <T> RespData<T> result(IBaseCode errorCode, String message, T data){
        boolean success = false;
        if (IBaseCode.SUCCESS.code().equals(errorCode.code())){
            success = true;
        }
        if(StringUtils.isBlank(message)){
            message = errorCode.message();
        }
        return (RespData<T>) RespData.builder()
                .code(errorCode.code())
                .message(message)
                .data(data)
                .success(success)
                .build();
    }
}
