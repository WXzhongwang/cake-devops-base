package com.rany.cake.devops.base.service.terminal;


/**
 * session holder
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
