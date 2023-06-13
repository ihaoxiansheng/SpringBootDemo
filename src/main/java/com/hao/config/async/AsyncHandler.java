package com.hao.config.async;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author xu.liang
 * @since 2022/11/5 15:32
 */
@Aspect
@Component
@Slf4j
public class AsyncHandler {

    @Around("@annotation(org.springframework.scheduling.annotation.Async)")
    private Object handle(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object retVal = pjp.proceed();
            return retVal;
        } catch (Throwable e) {
            log.error("in ASYNC, method: " + pjp.getSignature().toLongString() + ", args: " + Arrays.toString(pjp.getArgs()) + ", exception: " + e, e);
            throw new RuntimeException("错误");
        }
    }

}
