package com.rany.cake.devops.base.service.ssh.listener.event;

import com.rany.cake.devops.base.service.ssh.listener.SshShellEvent;


/**
 * event
 */
public interface ISshShellEvent {

    String getEventType();

    void handle(SshShellEvent event);

}
