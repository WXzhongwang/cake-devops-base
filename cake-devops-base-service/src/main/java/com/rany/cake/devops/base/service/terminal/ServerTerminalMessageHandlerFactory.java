package com.rany.cake.devops.base.service.terminal;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ServerTerminalMessageHandlerFactory {

    static Map<String, ITerminalMessageHandler> context = new ConcurrentHashMap<>();

    public static ITerminalMessageHandler getHandlerByState(String state) {
        return context.get(state);
    }

    public static void register(ITerminalMessageHandler bean) {
        log.debug("ServerTerminalMessageHandlerFactory Registered: state={}", bean.getState());
        context.put(bean.getState(), bean);
    }
}
