package com.rany.cake.devops.base.web.websocket;

import com.google.gson.GsonBuilder;
import com.rany.cake.devops.base.service.terminal.server.SimpleState;

public abstract class SocketBaseController {

    protected String getState(String message) {
        SimpleState ss = new GsonBuilder().create().fromJson(message, SimpleState.class);
        return ss.getState();
    }
}
