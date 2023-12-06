package com.rany.cake.devops.ssh.base;


import com.rany.cake.devops.ssh.base.listener.SshShellEventFactory;
import com.rany.cake.devops.ssh.base.listener.SshShellEventType;
import com.rany.cake.devops.ssh.base.listener.SshShellListener;
import com.rany.cake.devops.ssh.base.listener.event.ISshShellEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SshShellListenerConfiguration {

    @Bean
    public SshShellListener sshShellListener() {
        return event -> {
            SshShellEventType eventType = event.getType();
            ISshShellEvent sshShellEvent = SshShellEventFactory.getByType(eventType.name());
            if (sshShellEvent != null) {
                sshShellEvent.handle(event);
            }
        };
    }

}
