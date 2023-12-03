package com.rany.cake.devops.base.service.utils;

import java.util.concurrent.TimeUnit;

public final class SleepUtils {
    private SleepUtils() {
    }

    public static void sleep(long s) {
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException ignored) {
        }
    }

    public static void millisecondsSleep(long m) {
        try {
            TimeUnit.MILLISECONDS.sleep(m);
        } catch (InterruptedException ignored) {
        }
    }

}
