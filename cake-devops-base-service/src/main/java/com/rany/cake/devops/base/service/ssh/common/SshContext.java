package com.rany.cake.devops.base.service.ssh.common;

import com.rany.cake.devops.base.service.ssh.auth.SshAuthentication;
import com.rany.cake.devops.base.service.ssh.processor.PostProcessorObject;
import lombok.Getter;
import lombok.Setter;
import org.apache.sshd.server.Environment;
import org.apache.sshd.server.session.ServerSession;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

/**
 * Ssh context to hold terminal, exit callback and thread per thread
 */
@Getter
public class SshContext {

    /**
     * ssh
     * 运行线程
     */
    private SshShellRunnable sshShellRunnable;

    /**
     * 终端窗口
     */
    private Terminal terminal;

    /**
     * jline
     */
    private LineReader lineReader;

    /**
     * 用户凭证信息
     */
    private SshAuthentication authentication;

    private final List<PostProcessorObject> postProcessorsList = new ArrayList<>();

    @Setter
    private boolean background;

    private long backgroundCount = 0;

    /**
     * Default empty constructor
     */
    public SshContext() {
    }

    /**
     * Constructor
     *
     * @param sshShellRunnable ssh runnable
     * @param terminal         ssh terminal
     * @param lineReader       ssh line reader
     * @param authentication   (optional) spring authentication objects
     */
    public SshContext(SshShellRunnable sshShellRunnable, Terminal terminal, LineReader lineReader,
                      SshAuthentication authentication) {
        this.sshShellRunnable = sshShellRunnable;
        this.terminal = terminal;
        this.lineReader = lineReader;
        this.authentication = authentication;
    }

    /**
     * Check if current prompt is the one started with application
     *
     * @return if local prompt or not
     */
    public boolean isLocalPrompt() {
        return sshShellRunnable == null;
    }

    /**
     * Return current ssh session
     *
     * @return ssh session, or null of is local prompt
     */
    public ServerSession getSshSession() {
        return isLocalPrompt() ? null : sshShellRunnable.getSshSession();
    }

    /**
     * Return current ssh env
     *
     * @return ssh env, or null of is local prompt
     */
    public Environment getSshEnv() {
        return isLocalPrompt() ? null : sshShellRunnable.getSshEnv();
    }

    /**
     * Increment background sessions count
     */
    public void incrementBackgroundCount() {
        this.backgroundCount++;
    }
}
