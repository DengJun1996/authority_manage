package com.dj.exception;

import com.dj.common.ResEntity;
import com.dj.common.ResultEnum;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 统一异常处理
 * @author jetlag
 * @date 2020/4/29
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AuthenticationException.class)
    public ResEntity<?> authenticationException(AuthenticationException e) {
        log.error("authenticationException:{}", e.getMessage());
        return ResEntity.ERROR(-1, e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResEntity<?> bindException(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("->"));
        log.error("bindException:{}", message);
        return ResEntity.ERROR(-1, message);
    }

    @ExceptionHandler(DnJwtException.class)
    public ResEntity<?> dnJwtException(DnJwtException e) {
        log.error("dnJwtException:{}", e.getMessage());
        return ResEntity.ERROR(-1, e.getMessage());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResEntity<String> handle(UnauthorizedException e) {
        log.error("handle:{}", e.getMessage());
        return ResEntity.ERROR(ResultEnum.UN_AUTHORIZED);

    }
    @ExceptionHandler(value = BaseException.class)
    public ResEntity<String> baseException(BaseException e) {
        log.error("handle:{}", e.getMessage());
        return ResEntity.ERROR(ResultEnum.UNKONW_ERROR.getCode(), e.getMessage());

    }


    @ExceptionHandler(value = Exception.class)
    public ResEntity<String> handle(Exception e) {
        log.error("handle:{}", e.getMessage());
        return ResEntity.ERROR(ResEntity.UNKONW_ERROR, "系统未知错误, 请联系管理员");

    }
}
