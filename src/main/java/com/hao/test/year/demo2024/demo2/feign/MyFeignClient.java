package com.hao.test.year.demo2024.demo2.feign;

import com.google.common.base.Stopwatch;
import feign.Client;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Feign的继承类
 *
 * @author xu.liang
 * @since 2024/3/7 13:43
 */
@Slf4j
public class MyFeignClient extends Client.Default {

    public MyFeignClient(SSLSocketFactory sslContextFactory, HostnameVerifier hostnameVerifier) {
        super(sslContextFactory, hostnameVerifier);
    }

    @Override
    public Response execute(Request request, Request.Options options) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("实际feign转发地址：{}", request.url());
            return super.execute(request, options);
        } catch (IOException e) {
            log.error("调用异常，异常信息为：{}", ExceptionUtils.getStackTrace(e));
            throw e;
        } finally {
            log.info("调用结束，耗时 {} 毫秒", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        }
    }
}
