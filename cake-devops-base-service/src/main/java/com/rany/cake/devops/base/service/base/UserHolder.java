package com.rany.cake.devops.base.service.base;

import com.rany.cake.dingtalk.sdk.beans.SsoUser;

public class UserHolder {
    private static final ThreadLocal<SsoUser> LOCAL = new ThreadLocal<>();

    public static SsoUser get() {
        return LOCAL.get();
    }

    public static void set(SsoUser user) {
        LOCAL.set(user);
    }

    public static void remove() {
        LOCAL.remove();
    }
}
