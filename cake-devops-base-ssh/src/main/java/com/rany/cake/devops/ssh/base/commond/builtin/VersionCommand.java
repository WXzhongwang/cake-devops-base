package com.rany.cake.devops.ssh.base.commond.builtin;

import com.rany.cake.devops.ssh.base.SshShellHelper;
import com.rany.cake.devops.ssh.base.SshShellProperties;
import com.rany.cake.devops.ssh.base.commond.AbstractCommand;
import com.rany.cake.devops.ssh.base.commond.SshShellComponent;
import com.rany.cake.devops.ssh.base.utils.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Version;

import javax.annotation.Resource;


@Slf4j
@SshShellComponent
@ShellCommandGroup("Built-In Commands")
@ConditionalOnProperty(
        name = SshShellProperties.SSH_SHELL_PREFIX + ".commands." + VersionCommand.GROUP + ".create",
        havingValue = "true", matchIfMissing = true
)
public class VersionCommand extends AbstractCommand implements Version.Command {

    public static final String GROUP = "version";
    public static final String COMMAND_VERSION = GROUP;

    private static final String VERSION = "Spring Boot<{}>  Spring Shell<2.1.1>";

    @Resource
    private SshShellHelper sshShellHelper;

    public VersionCommand(SshShellProperties properties, SshShellHelper helper) {
        super(helper, properties, properties.getCommands().getHistory());
    }

    @SuppressWarnings("SpringShellCommandInspection")
    @ShellMethod(key = COMMAND_VERSION, value = "Show version info")
    public void version() {
        sshShellHelper.print(StringFormatter.arrayFormat(VERSION, SpringBootVersion.getVersion()));
    }

}
