package com.rany.cake.devops.ssh.base.listener.event;


import com.rany.cake.devops.ssh.base.listener.SshShellEvent;
import com.rany.cake.devops.ssh.base.listener.SshShellEventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class SshShellStoppedEvent extends AbstractSshShellEvent {

    @Override
    public String getEventType() {
        return SshShellEventType.SESSION_STOPPED.name();
    }

    @Override
    public void handle(SshShellEvent event) {
        // SessionCommandContext.remove();
        final String username = event.getSession().getServerSession().getUsername();
        log.info("{} logout of the SSH-Server", username);
        closeTerminalSession(event);
    }
}
