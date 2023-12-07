package com.rany.cake.devops.ssh.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rany.cake.devops.ssh.base.auth.SshShellAuthenticationProvider;
import com.rany.cake.devops.ssh.base.auth.impl.SshShellPasswordAuthenticationProvider;
import com.rany.cake.devops.ssh.base.listener.SshShellListener;
import com.rany.cake.devops.ssh.base.listener.SshShellListenerService;
import com.rany.cake.devops.ssh.base.processor.provided.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.server.SshServer;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.shell.boot.LineReaderAutoConfiguration;
import org.springframework.shell.boot.SpringShellAutoConfiguration;
import org.springframework.shell.boot.SpringShellProperties;
import org.springframework.shell.context.InteractionMode;
import org.springframework.shell.context.ShellContext;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ValueProvider;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Configuration
@ConditionalOnClass(SshServer.class)
@ConditionalOnProperty(name = SshShellProperties.SSH_SHELL_ENABLE, havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({SshShellProperties.class})
@AutoConfigureAfter(value = {
        SpringShellAutoConfiguration.class, LineReaderAutoConfiguration.class
})
@ComponentScan(basePackages = {"com.rany.cake.devops.ssh.base"})
public class SshShellAutoConfiguration {
    @Resource
    private SshShellProperties shellProperties;
    @Resource
    private ApplicationContext context;
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

    @Bean
    @ConditionalOnProperty(value = "spring.main.lazy-initialization", havingValue = "true")
    public ApplicationListener<ContextRefreshedEvent> lazyInitApplicationListener() {
        return event -> {
            log.info("Lazy initialization enabled, calling configuration beans explicitly to start ssh server and initialize shell correctly");
            context.getBean(SshShellConfiguration.SshServerLifecycle.class);
            context.getBeansOfType(Terminal.class);
            context.getBeansOfType(ValueProvider.class);
        };
    }


    @Bean
    @ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
    public JsonPointerPostProcessor jsonPointerPostProcessor(ObjectMapper mapper) {
        return new JsonPointerPostProcessor(mapper);
    }

    @Bean
    @ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
    public PrettyJsonPostProcessor prettyJsonPostProcessor(ObjectMapper mapper) {
        return new PrettyJsonPostProcessor(mapper);
    }

    @Bean
    public SavePostProcessor savePostProcessor() {
        return new SavePostProcessor();
    }

    @Bean
    public GrepPostProcessor grepPostProcessor() {
        return new GrepPostProcessor();
    }

    @Bean
    public HighlightPostProcessor highlightPostProcessor() {
        return new HighlightPostProcessor();
    }

}


