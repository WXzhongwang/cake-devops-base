package com.rany.cake.devops.base.service.ssh;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.io.File;

/**
 * 属性装配
 */
@Data
@ConfigurationProperties(prefix = SshShellProperties.SSH_SHELL_PREFIX)
public class SshShellProperties {
    public static final String SSH_SHELL_PREFIX = "ssh.shell";
    public static final String SSH_SHELL_ENABLE = SSH_SHELL_PREFIX + ".enable";

    private String host = "127.0.0.1";

    private int port = 2222;

    private boolean displayBanner = true;

    private File hostKeyFile = new File(System.getProperty("java.io.tmpdir"), "hostKey.ser");

    private Resource authorizedPublicKeys;

    private File historyFile = new File(System.getProperty("java.io.tmpdir"), "sshShellHistory.log");

    private boolean sharedHistory = true;

    /**
     * Note: only used if @link {@link SshShellProperties#sharedHistory}} set to false
     */
    private File historyDirectory = new File(System.getProperty("java.io.tmpdir"));
}