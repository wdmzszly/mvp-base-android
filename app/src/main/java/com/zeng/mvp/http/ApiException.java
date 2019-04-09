package com.zeng.mvp.http;

/**
 * CCreated by Zeng on 2019/4/8.
 */

public class ApiException extends RuntimeException {
    private int code;
    public ApiException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
