package com.hao.test.year.demo2023.demo9;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

/**
 * 获取ip4地址
 *
 * @author xu.liang
 * @since 2023/9/15 10:23
 */
@Slf4j
public class IPUtils {

    public static final Set<String> IP_HEADER_SET = Sets.newHashSet(
            "browser-ip", "X-Real-IP", "browser", "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR");

    /**
     * 获取IP地址<p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip;
        // 为了防止IP记录有误，把他们都记录下来
        JSONObject ipObj = new JSONObject();
        try {
            for (String name : IP_HEADER_SET) {
                ip = request.getHeader(name);
                if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                    ipObj.put(name, ip);
                }
            }
            ip = request.getRemoteAddr();
            if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                ipObj.put("RemoteAddr", ip);
            }
            ip = request.getRemoteHost();
            if (StringUtils.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
                ipObj.put("RemoteHost", ip);
            }
        } catch (Exception e) {
            log.error("获取IP地址失败：", e);
        }
        log.info("获取到的IP地址为：{}", ipObj);
        return JSON.toJSONString(ipObj);
    }

    public static List<String> ip4List() {
        ArrayList<String> list = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = networkInterfaces.nextElement();
                Enumeration<InetAddress> nias = ni.getInetAddresses();
                while (nias.hasMoreElements()) {
                    InetAddress ia = nias.nextElement();
                    if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia instanceof Inet4Address) {
                        String hostIP = ia.getHostAddress();
                        list.add(hostIP);
                    }
                }
            }
        } catch (SocketException e) {
            log.error("获取本机IP失败：", e);
        }
        return list;
    }

    /**
     * 获取客户端ip
     *
     * @author xuyuxiang
     * @date 2020/3/19 9:32
     */
    public static String getIp(HttpServletRequest request) {
        String defaultIp = "127.0.0.1";
        if (ObjectUtil.isEmpty(request)) {
            return defaultIp;
        } else {
            try {
                String remoteHost = ServletUtil.getClientIP(request);
                log.info("获取到的IP地址为：{}", remoteHost);
                return "0:0:0:0:0:0:0:1".equals(remoteHost) ? defaultIp : remoteHost;
            } catch (Exception e) {
                log.error(">>> 获取客户端ip异常：", e);
                return defaultIp;
            }
        }
    }

    public static void main(String[] args) {
        List<String> ip4List = ip4List();
        System.out.println("ip4List = " + ip4List);
    }

}
