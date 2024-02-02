package com.hao.test.year.demo2023.demo9;

import lombok.extern.slf4j.Slf4j;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 获取ip4地址
 *
 * @author xu.liang
 * @since 2023/9/15 10:23
 */
@Slf4j
public class IPUtils {
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
            log.error("获取本机IP失败");
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> ip4List = ip4List();
        System.out.println("ip4List = " + ip4List);
    }

}
