package com.rany.cake.devops.ssh.base.listener.event;


import com.rany.cake.devops.ssh.base.listener.SshShellEvent;
import com.rany.cake.devops.ssh.base.listener.SshShellEventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SshShellUnexpectedlyEvent extends AbstractSshShellEvent {

    @Override
    public String getEventType() {
        return SshShellEventType.SESSION_STOPPED_UNEXPECTEDLY.name();
    }

    @Override
    public void handle(SshShellEvent event) {
        // SessionCommandContext.remove();
        final String username = event.getSession().getServerSession().getUsername();
        log.warn("{} disconnects SSH-Server", username);
        closeTerminalSession(event);
    }

}
