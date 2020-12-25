package com.dj.exception;

/**
 * jwt异常
 * @author jetlag
 * @date 2020/5/14
 **/
public class DnJwtException extends BaseException {

    private static final long serialVersionUID = 6367719059222570295L;

    public DnJwtException() {
        super();
    }

    public DnJwtException(String message) {
        super(message);
    }

    public DnJwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public DnJwtException(Throwable cause) {
        super(cause);
    }

    protected DnJwtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
