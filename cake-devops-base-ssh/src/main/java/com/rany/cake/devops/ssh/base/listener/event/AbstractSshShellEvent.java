package com.rany.cake.devops.ssh.base.listener.event;


import com.rany.cake.devops.ssh.base.common.HostInfo;
import com.rany.cake.devops.ssh.base.listener.SshShellEvent;
import com.rany.cake.devops.ssh.base.listener.SshShellEventFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


/**
 * 抽象的shell event
 */
@Component
public abstract class AbstractSshShellEvent implements ISshShellEvent, InitializingBean {

    public final static HostInfo HOST_INFO = HostInfo.build();


    protected void openTerminalSession(SshShellEvent event) {

    }

    protected void closeTerminalSession(SshShellEvent event) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SshShellEventFactory.register(this);
    }

}
