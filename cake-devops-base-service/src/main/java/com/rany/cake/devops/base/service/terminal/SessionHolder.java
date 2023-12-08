package com.rany.cake.devops.base.service.terminal;

/**
 * @Author baiyi
 * @Date 2020/1/7 1:52 下午
 * @Version 1.0
 */
public class SessionHolder {

    private static final ThreadLocal<String> username = new ThreadLocal<>();

    public static String getUsername() {
        return username.get();
    }

    public static void setUsername(String param) {
        username.set(param);
    }

}
