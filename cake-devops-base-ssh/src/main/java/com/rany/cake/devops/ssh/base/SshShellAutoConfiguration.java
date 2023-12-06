package com.rany.cake.devops.ssh.base;

import com.rany.cake.devops.ssh.base.auth.SshShellAuthenticationProvider;
import com.rany.cake.devops.ssh.base.auth.impl.SshShellPasswordAuthenticationProvider;
import com.rany.cake.devops.ssh.base.listener.SshShellListener;
import com.rany.cake.devops.ssh.base.listener.SshShellListenerService;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.boot.SpringShellProperties;
import org.springframework.shell.context.InteractionMode;
import org.springframework.shell.context.ShellContext;
import org.springframework.shell.jline.PromptProvider;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@EnableConfigurationProperties({SshShellProperties.class})
@ConditionalOnProperty(name = SshShellProperties.SSH_SHELL_ENABLE, havingValue = "true", matchIfMissing = true)
public class SshShellAutoConfiguration {
    @Resource
    private SshShellProperties shellProperties;
    @Resource
    private SpringShellProperties springShellProperties;
    @Resource
    private ShellContext shellContext;

    @Bean
    public SshShellHelper sshShellHelper() {
        return new SshShellHelper(shellProperties.getConfirmationWords());
    }

    @PostConstruct
    public void init() {
        // override some spring shell properties
        springShellProperties.getHistory().setName(shellProperties.getHistoryFile().getAbsolutePath());
        // set interactive mode so that ThrowableResultHandler.showShortError() returns true
        shellContext.setInteractionMode(InteractionMode.INTERACTIVE);
    }

    /**
     * Primary prompt provider
     *
     * @return prompt provider
     */
    @Bean
    @ConditionalOnMissingBean
    public PromptProvider sshPromptProvider() {
        return () -> new AttributedString(shellProperties.getPrompt().getText(),
                AttributedStyle.DEFAULT.foreground(shellProperties.getPrompt().getColor().toJlineAttributedStyle()));
    }

    @Bean
    @ConditionalOnMissingBean
    public SshShellAuthenticationProvider sshShellSimpleAuthenticationProvider() {
        return new SshShellPasswordAuthenticationProvider(shellProperties.getUser(), shellProperties.getPassword());
    }

    /**
     * Creates ssh listener service
     *
     * @param listeners found listeners in context
     * @return listener service
     */
    @Bean
    public SshShellListenerService sshShellListenerService(@Autowired(required = false) List<SshShellListener> listeners) {
        return new SshShellListenerService(listeners);
    }
}


