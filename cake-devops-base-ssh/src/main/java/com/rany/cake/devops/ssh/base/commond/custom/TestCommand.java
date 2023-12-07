package com.rany.cake.devops.ssh.base.commond.custom;

import com.rany.cake.devops.ssh.base.SshShellProperties;
import com.rany.cake.devops.ssh.base.commond.SshShellComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellMethod;

@Slf4j
@SshShellComponent
@ShellCommandGroup("Custom Tools Commands")
@ConditionalOnProperty(name = SshShellProperties.SSH_SHELL_PREFIX + ".commands.tools.create", havingValue = "true", matchIfMissing = true)
public class TestCommand {
    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }
}