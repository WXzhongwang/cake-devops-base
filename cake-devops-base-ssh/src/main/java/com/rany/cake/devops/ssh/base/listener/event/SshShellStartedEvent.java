package com.rany.cake.devops.ssh.base.listener.event;

import com.rany.cake.devops.ssh.base.PromptColor;
import com.rany.cake.devops.ssh.base.SshShellHelper;
import com.rany.cake.devops.ssh.base.listener.SshShellEvent;
import com.rany.cake.devops.ssh.base.listener.SshShellEventType;
import com.rany.cake.devops.ssh.base.utils.StringFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SshShellStartedEvent extends AbstractSshShellEvent {

    private final SshShellHelper sshShellHelper;

    @Value("${ssh.shell.version}")
    private String version;

    private static final String WELCOME = "Dear {}, Welcome to SSH-Server<{}>@{} \n";

    @Override
    public String getEventType() {
        return SshShellEventType.SESSION_STARTED.name();
    }

    @Override
    public void handle(SshShellEvent event) {
        openTerminalSession(event);
        final String username = event.getSession().getServerSession().getUsername();
        final String sshServerInstance = sshShellHelper.getColored(AbstractSshShellEvent.HOST_INFO.getHostname(), PromptColor.BLUE);
        String welcome = StringFormatter.arrayFormat(WELCOME, username, version, sshServerInstance);
        sshShellHelper.print(welcome, PromptColor.CYAN);
    }

    private void preHandle() {

    }

}
