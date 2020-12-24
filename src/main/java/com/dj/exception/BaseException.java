package com.dj.exception;

/**
 * 基础异常类
 * @author  jetlag
 * @date 2020/5/29
 **/
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 3669904095261498415L;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
