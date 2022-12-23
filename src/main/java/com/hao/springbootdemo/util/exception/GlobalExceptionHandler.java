package com.hao.springbootdemo.util.exception;

import com.hao.springbootdemo.common.R;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

/**
 * @author xu.liang
 * @since 2022/12/15 09:37
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public R<String> authorizationException(AuthorizationException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        R<String> r = new R<>();
        r.setCode(500);
        r.setMsg(e.getMessage());
        return r;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public R<String> unauthorizedException(AuthorizationException e) {
        log.error(MessageFormat.format("请检查该用户是否具有指定的权限:{0}", e.getMessage()));
        R<String> r = new R<String>();
        r.setCode(500);
        r.setMsg("您无权限访问该接口");
        return r;
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public R<String> expiredJwtException(AuthorizationException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        R<String> r = new R<String>();
        r.setCode(500);
        r.setMsg("用户身份已过期,请重新登录");
        return r;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<String> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        R<String> r = new R<>();
        r.setCode(500);
        r.setMsg("不支持该请求方式");
        return r;
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(GlobalException.class)
    public R<String> exception(HttpServletRequest request, GlobalException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        R<String> r = new R<>();
        r.setCode(e.getCode());
        r.setMsg(e.getMessage());
        return r;
    }

    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return R.error("服务器异常><");
    }
}
