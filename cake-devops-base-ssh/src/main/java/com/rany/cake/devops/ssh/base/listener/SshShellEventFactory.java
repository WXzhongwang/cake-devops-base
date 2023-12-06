package com.rany.cake.devops.ssh.base.listener;

import com.rany.cake.devops.ssh.base.listener.event.ISshShellEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class SshShellEventFactory {

    private static final Map<String, ISshShellEvent> CONTEXT = new ConcurrentHashMap<>();

    public static ISshShellEvent getByType(String key) {
        return CONTEXT.get(key);
    }

    public static void register(ISshShellEvent bean) {
        CONTEXT.put(bean.getEventType(), bean);
        log.debug("SshShellEventFactory Registered: eventType={}, beanName={}",
                bean.getEventType(), bean.getClass().getSimpleName());
    }
}
