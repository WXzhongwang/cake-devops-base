package com.rany.cake.devops.ssh.base.listener.event;

import com.rany.cake.devops.ssh.base.listener.SshShellEvent;


/**
 * event
 */
public interface ISshShellEvent {

    String getEventType();

    void handle(SshShellEvent event);

}
