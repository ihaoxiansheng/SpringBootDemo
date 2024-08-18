package com.hao.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hao.test.year.demo2023.demo9.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

/**
 * @author xu.liang
 * @since 2024/5/10 10:27
 */
@Slf4j
@Component
public class ClientIpUtils {

    @Resource
    private RestTemplate restTemplate;

    private static final String LOCAL_IP = "127.0.0.1";

    public String getClientIP() {
        try {
            String ip = restTemplate.getForObject("https://api.ipify.org", String.class);
            log.info("公网IP为：" + ip);
            return ip;
        } catch (Exception e) {
            log.error("获取公网IP失败：", e);
            try {
                URL url = new URL("http://checkip.amazonaws.com");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String ip = br.readLine().trim();
                log.info("公网IP2为：" + ip);
                return ip;
            } catch (Exception e2) {
                log.error("获取公网IP2失败：", e);
                HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
                String ipObj = IPUtils.getIpAddress(request);
                JSONObject jsonObject = JSON.parseObject(ipObj);
                String ip = jsonObject.getString("RemoteAddr");
                log.info("客户端IP为：" + ip);
                return ip;
            }
        }
    }

    public String getIPAddress1() {
        try {
            String webUrl = "https://api.ipify.org";
            String ip = restTemplate.getForObject(webUrl, String.class);
            log.info("公网IP地址1为：" + ip);
            return ip;
        } catch (Exception e) {
            return LOCAL_IP;
        }
    }

    public String getIPAddress2() {
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String ip = br.readLine().trim();
            log.info("公网IP地址2为：" + ip);
            return ip;
        } catch (Exception e) {
            return LOCAL_IP;
        }
    }

    public String getIPAddress3() {
        try {
            URL url = new URL("http://myip.ipip.net");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String value = br.readLine().trim();
            String ip = value.substring(value.indexOf("当前 IP：") + 6, value.indexOf("  来自于："));
            log.info("公网IP地址3为：" + ip);
            return ip;
        } catch (Exception e) {
            return LOCAL_IP;
        }
    }

}
