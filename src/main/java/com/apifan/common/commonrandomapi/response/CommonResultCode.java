package com.apifan.common.commonrandomapi.response;

/**
 * 常用业务响应码枚举
 */
public enum CommonResultCode {

    /**
     * 成功
     */
    SUCCESS(0),

    /**
     * 失败
     */
    FAIL(1);

    private final int code;

    CommonResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
