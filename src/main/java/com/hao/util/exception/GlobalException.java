package com.hao.util.exception;

import lombok.Data;

/**
 * @author xu.liang
 * @since 2022/12/15 09:37
 */
@Data
public class GlobalException extends RuntimeException {

    private int code = 500;

    public GlobalException(String msg) {
        super(msg);
    }

    public GlobalException(String msg, Throwable e) {
        super(msg, e);
    }

    public GlobalException(String msg, int code) {
        super(msg);
        this.setCode(code);
    }

    public GlobalException(String msg, int code, Throwable e) {
        super(msg, e);
        this.setCode(code);
    }
}
