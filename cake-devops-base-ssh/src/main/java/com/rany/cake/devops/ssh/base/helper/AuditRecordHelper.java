package com.rany.cake.devops.ssh.base.helper;

import com.rany.cake.devops.ssh.base.core.TerminalConfigurationProperties;
import com.rany.cake.devops.ssh.base.utils.IOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 审计日志记录器
 */
@Slf4j
@Component
public class AuditRecordHelper {

    private static TerminalConfigurationProperties terminalConfigurationProperties;

    @Autowired
    private void setTerminalConfig(TerminalConfigurationProperties terminalConfigurationProperties) {
        AuditRecordHelper.terminalConfigurationProperties = terminalConfigurationProperties;
    }

    public static void record(String sessionId, String instanceId, char[] buf, int off, int len) {
        try {
            IOUtil.appendFile(new String(buf).substring(off, len), terminalConfigurationProperties.buildAuditLogPath(sessionId, instanceId));
        } catch (Exception e) {
            log.error("Web终端会话日志写入失败! sessionId={}, instanceId={}", sessionId, instanceId);
        }
    }

    public static String getAuditLogPath(String sessionId, String instanceId) {
        return terminalConfigurationProperties.buildAuditLogPath(sessionId, instanceId);
    }
}
