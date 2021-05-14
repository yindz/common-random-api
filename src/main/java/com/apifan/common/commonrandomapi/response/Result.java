package com.apifan.common.commonrandomapi.response;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 业务响应码
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 返回成功结果
     *
     * @param data 相关数据
     * @param <T>  泛型
     * @return 成功结果
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = CommonResultCode.SUCCESS.code();
        result.data = data;
        return result;
    }

    /**
     * 返回失败结果
     *
     * @param message 相关消息
     * @param data    相关数据
     * @param <T>     泛型
     * @return 失败结果
     */
    public static <T> Result<T> fail(String message, T data) {
        return fail(CommonResultCode.FAIL.code(), message, data);
    }

    /**
     * 返回失败结果(无数据)
     *
     * @param message 相关消息
     * @param <T>     泛型
     * @return 失败结果
     */
    public static <T> Result<T> fail(String message) {
        return fail(message, null);
    }

    /**
     * 返回失败结果
     *
     * @param code    业务响应码
     * @param message 相关消息
     * @param data    相关数据
     * @param <T>     泛型
     * @return 失败结果
     */
    public static <T> Result<T> fail(int code, String message, T data) {
        Preconditions.checkArgument(code != CommonResultCode.SUCCESS.code(), "业务返回码不正确");
        Result<T> result = new Result<>();
        result.code = code;
        result.message = message;
        result.data = data;
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
