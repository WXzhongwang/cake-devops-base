package com.rany.cake.devops.ssh.base.auth.config;

import com.rany.cake.devops.ssh.base.auth.PublicKeyAuthenticatorProvider;
import com.rany.cake.devops.ssh.base.auth.impl.SshShellPublicKeyAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration
public class CustomPublicKeyConfiguration {

    @Bean
    @Primary
    public PublicKeyAuthenticatorProvider publicKeyAuthenticatorProvider() {
        return (username, publicKey, serverSession) -> {
            // User user = userService.getByUsername(username);
            // TODO: 获取用户有权限的SSH KEY
            Map<String, String> userSshKeyDict = new HashMap<>();
            if (userSshKeyDict.isEmpty()) {
                return false;
            }
            try {
                File sshShellPubKeysTmpFile = Files.createTempFile("sshShellPubKeys-", ".tmp").toFile();
                try (FileWriter fw = new FileWriter(sshShellPubKeysTmpFile)) {
                    for (String key : userSshKeyDict.keySet()) {
                        fw.write(userSshKeyDict.get(key) + "\n");
                    }
                    fw.flush();
                    return new SshShellPublicKeyAuthenticationProvider(sshShellPubKeysTmpFile).authenticate(username, publicKey, serverSession);
                } catch (Exception e) {
                    log.error("Error generating user {} public key", username);
                } finally {
                    if (!sshShellPubKeysTmpFile.delete()) {
                        log.debug("Failed to delete temporary file {}", sshShellPubKeysTmpFile.getName());
                    }
                }
            } catch (IOException ignored) {
            }
            return false;
        };
    }
}
