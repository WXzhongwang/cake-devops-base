package com.rany.cake.devops.ssh.base.common;

import com.jcraft.jsch.Channel;
import com.rany.cake.devops.base.service.terminal.HostSystem;
import com.rany.cake.devops.base.service.terminal.SessionOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.OutputStream;
import java.io.PrintStream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JSchSession {

    private String termSessionId;

    private String sessionId;
    /**
     * hostId
     */
    private String instanceId;

    private PrintStream commander;


    private OutputStream inputToChannel;
    private Channel channel;
    private HostSystem hostSystem;
    private static SessionOutput sessionOutput;


    public void setSessionOutput(SessionOutput sessionOutput) {
        JSchSession.sessionOutput = sessionOutput;
    }

    public SessionOutput getSessionOutput() {
        return JSchSession.sessionOutput;
    }

}
