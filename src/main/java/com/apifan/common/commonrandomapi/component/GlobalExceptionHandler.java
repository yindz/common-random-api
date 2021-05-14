package com.apifan.common.commonrandomapi.component;

import com.apifan.common.commonrandomapi.response.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 异常处理
     *
     * @param ex
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, MissingServletRequestParameterException.class, TypeMismatchException.class, IllegalArgumentException.class, IllegalStateException.class, RuntimeException.class, Exception.class})
    @ResponseBody
    public Result errorHandler(Exception ex) {
        logger.error("出现异常", ex);
        String msg;
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) ex;
            return Result.fail(getFieldErrors(me.getBindingResult().getFieldErrors()));
        } else if (ex instanceof BindException) {
            BindException be = (BindException) ex;
            return Result.fail(getFieldErrors(be.getBindingResult().getFieldErrors()));
        } else if (ex instanceof RuntimeException) {
            RuntimeException re = (RuntimeException) ex;
            return Result.fail(re.getMessage());
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            return Result.fail("请求方式错误");
        } else if (ex instanceof MissingServletRequestParameterException) {
            return Result.fail("请求参数缺失异常：" + ((MissingServletRequestParameterException) ex).getParameterName());
        } else {
            msg = ex.getMessage();
        }
        return Result.fail(msg);

    }

    /**
     * 获取字段校验不通过时的错误信息
     *
     * @param fieldErrors
     * @return
     */
    private String getFieldErrors(List<FieldError> fieldErrors) {
        String msg = "error";
        if (!fieldErrors.isEmpty()) {
            List<String> errorMsgs = fieldErrors.stream().map(FieldError::getDefaultMessage).distinct().collect(Collectors.toList());
            msg = String.join(";", errorMsgs);
        }
        return msg;
    }
}
