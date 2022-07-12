package com.doaction.demo.aop;

import com.doaction.demo.api.base.resp.RespData;
import com.doaction.demo.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * Created by macro on 2020/2/27.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /*@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult handle(Exception e) {
        log.error(e.getMessage());
        return CommonResult.failed(e.getMessage());
    }*/

    @ResponseBody
    @ExceptionHandler({RuntimeException.class})
    public RespData exceptionHandle(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return RespData.fail();
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RespData handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        log.error(e.getMessage());
        e.printStackTrace();
        return RespData.fail(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public RespData handleValidException(BizException e) {
        log.error(e.getMessage());
        return RespData.fail(e.getResult());
    }
}
