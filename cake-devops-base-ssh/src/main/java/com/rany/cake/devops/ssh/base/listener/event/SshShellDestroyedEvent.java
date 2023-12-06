package com.rany.cake.devops.ssh.base.listener.event;

import com.rany.cake.devops.ssh.base.listener.SshShellEvent;
import com.rany.cake.devops.ssh.base.listener.SshShellEventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author baiyi
 * @Date 2022/9/1 17:09
 * @Version 1.0
 */
@Slf4j
@Component
public class SshShellDestroyedEvent extends AbstractSshShellEvent {

    @Override
    public String getEventType() {
        return SshShellEventType.SESSION_DESTROYED.name();
    }

    /**
     * 销毁补偿
     */
    @Override
    public void handle(SshShellEvent event) {
        closeTerminalSession(event);
    }

}
