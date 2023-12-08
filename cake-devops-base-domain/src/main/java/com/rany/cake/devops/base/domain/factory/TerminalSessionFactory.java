package com.rany.cake.devops.base.domain.factory;

import com.rany.cake.devops.base.domain.aggregate.TerminalSession;
import com.rany.cake.devops.base.domain.base.HostInfo;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.enums.SessionTypeEnum;
import com.rany.cake.devops.base.domain.pk.TerminalSessionId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class TerminalSessionFactory {
    
    private final SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 构建器
     *
     * @param sessionId   sessionId
     * @param hostInfo    服务端信息
     * @param sessionType session类型
     * @return
     */
    public TerminalSession build(String sessionId, HostInfo hostInfo, SessionTypeEnum sessionType) {
        return TerminalSession.builder()
                .id(new TerminalSessionId(snowflakeIdWorker.nextId()))
                .sessionId(sessionId)
                .serverHostname(hostInfo.getHostname())
                .serverAddr(hostInfo.getHostAddress())
                .sessionClosed(false)
                .sessionType(sessionType)
                .build();
    }
}
