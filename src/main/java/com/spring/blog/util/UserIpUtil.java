package com.spring.blog.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UserIpUtil {

    public static String userIp(String userIp) {
        String ip = null;
        InetAddress address;
        try{
            address = Inet4Address.getLocalHost();
            ip = address.getHostAddress();
        }catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

}
