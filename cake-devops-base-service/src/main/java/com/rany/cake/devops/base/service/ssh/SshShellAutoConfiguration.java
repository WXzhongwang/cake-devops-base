package com.rany.cake.devops.base.service.ssh;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.boot.SpringShellProperties;
import org.springframework.shell.context.InteractionMode;
import org.springframework.shell.context.ShellContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@EnableConfigurationProperties({SshShellProperties.class})
@ConditionalOnProperty(name = SshShellProperties.SSH_SHELL_ENABLE, havingValue = "true", matchIfMissing = true)
@ComponentScan(basePackages = {"com.rany.cake.devops.base.service.ssh"})
public class SshShellAutoConfiguration {
    @Resource
    private SshShellProperties shellProperties;
    @Resource
    private SpringShellProperties springShellProperties;
    @Resource
    private ShellContext shellContext;

    @PostConstruct
    public void init() {
        // override some spring shell properties
        springShellProperties.getHistory().setName(shellProperties.getHistoryFile().getAbsolutePath());
        // set interactive mode so that ThrowableResultHandler.showShortError() returns true
        shellContext.setInteractionMode(InteractionMode.INTERACTIVE);
    }
}

