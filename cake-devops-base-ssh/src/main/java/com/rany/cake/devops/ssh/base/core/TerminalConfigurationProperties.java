package com.rany.cake.devops.ssh.base.core;

import com.google.common.base.Joiner;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "terminal", ignoreInvalidFields = true)
public class TerminalConfigurationProperties {

    @Data
    public static class Audit {

        private String path;
        private Boolean open;

    }

    private Audit audit;

    public interface Suffix {
        String AUDIT_LOG = ".log";
        String COMMAND_LOG = "_commander.log";

        // formatted
        String FMT_COMMAND_LOG = "_commander_fmt.log";
    }

    public String buildAuditLogPath(String sessionId, String instanceId) {
        return Joiner.on("/").join(audit.getPath(), sessionId, instanceId + Suffix.AUDIT_LOG);
    }

    public String buildCommanderLogPath(String sessionId, String instanceId) {
        return Joiner.on("/").join(audit.getPath(), sessionId, instanceId + Suffix.COMMAND_LOG);
    }

    public String buildFmtCommanderLogPath(String sessionId, String instanceId) {
        return Joiner.on("/").join(audit.getPath(), sessionId, instanceId + Suffix.FMT_COMMAND_LOG);
    }

}