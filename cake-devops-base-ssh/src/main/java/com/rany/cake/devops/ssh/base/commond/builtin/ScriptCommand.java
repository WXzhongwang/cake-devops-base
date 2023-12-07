package com.rany.cake.devops.ssh.base.commond.builtin;


import com.rany.cake.devops.ssh.base.SshShellProperties;
import com.rany.cake.devops.ssh.base.commond.SshShellComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.commands.Script;

@Slf4j
@SshShellComponent
@ShellCommandGroup("Built-In Commands")
@ConditionalOnProperty(
        name = SshShellProperties.SSH_SHELL_PREFIX + ".commands." + ScriptCommand.GROUP + ".create",
        havingValue = "true", matchIfMissing = true
)
public class ScriptCommand implements Script.Command {

    public static final String GROUP = "script";

}
