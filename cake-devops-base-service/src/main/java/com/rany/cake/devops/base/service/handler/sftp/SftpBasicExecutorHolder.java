package com.rany.cake.devops.base.service.handler.sftp;

import com.rany.cake.devops.base.api.dto.SftpSessionTokenDTO;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.toolkit.lang.constant.Const;
import com.rany.cake.toolkit.lang.utils.Maps;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import com.rany.cake.toolkit.net.remote.channel.SftpExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * sftp基本操作执行holder
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/11/9 22:30
 */
@Slf4j
@Component
public class SftpBasicExecutorHolder {

    @Resource
    private HostDomainService hostDomainService;
    @Resource
    private HostRepository hostRepository;
    @Resource
    private HostConnectionService hostConnectionService;
    @Resource
    private SftpInternalService sftpInternalService;

    /**
     * 基本操作的executor 不包含(upload, download)
     * machineId: executor
     */
    private final Map<String, SftpExecutor> basicExecutorHolder = Maps.newCurrentHashMap();

    /**
     * 基本操作executor 最后使用时间
     */
    private final Map<String, Long> executorUsing = Maps.newCurrentHashMap();

    /**
     * 获取 sftp 基本操作 executor
     *
     * @param sessionToken sessionToken
     * @return SftpExecutor
     */
    public SftpExecutor getBasicExecutor(String sessionToken) {
        // 获取executor
        SftpSessionTokenDTO tokenInfo = sftpInternalService.getTokenInfo(sessionToken);
        return this.getBasicExecutorByHostId(tokenInfo.getHostId());
    }


    /**
     * 获取 sftp 基本操作 executor
     *
     * @param hostId hostId
     * @return SftpExecutor
     */
    public SftpExecutor getBasicExecutorByHostId(String hostId) {
        return this.getBasicExecutor(hostId, null);
    }

    /**
     * 获取 sftp 基本操作 executor
     *
     * @param hostId hostId
     * @param host   host
     * @return SftpExecutor
     */
    public SftpExecutor getBasicExecutor(String hostId, Host host) {
        SftpExecutor executor = basicExecutorHolder.get(hostId);
        if (executor != null) {
            if (!executor.isConnected()) {
                try {
                    executor.connect();
                } catch (Exception e) {
                    // 无法连接则重新创建实例
                    executor = null;
                }
            }
        }
        // 如果没有重新建立连接
        if (executor == null) {
            if (host == null) {
                host = hostRepository.find(new HostId(hostId));
            }
            // 获取charset
            String charset = hostDomainService.getSftpCharset(hostId);
            // 打开sftp连接
            SessionStore sessionStore = hostConnectionService.openSessionStore(host);
            executor = sessionStore.getSftpExecutor(charset);
            executor.connect();
            basicExecutorHolder.put(hostId, executor);
        }
        executorUsing.put(hostId, System.currentTimeMillis());
        return executor;
    }

    /**
     * 无效化一段时间(1分钟)未使用的执行器
     */
    @Scheduled(cron = "0 */1 * * * ?")
    private void invalidationUnusedExecutor() {
        long curr = System.currentTimeMillis();
        // 查询需要淘汰的executor的key
        List<String> expireKeys = basicExecutorHolder.keySet().stream()
                .filter(key -> curr > executorUsing.get(key) + Const.MS_S_60 * 5)
                .collect(Collectors.toList());
        // 移除
        expireKeys.forEach(key -> {
            SftpExecutor sftpExecutor = basicExecutorHolder.get(key);
            if (sftpExecutor == null) {
                return;
            }
            log.info("淘汰sftp执行器: {}", key);
            basicExecutorHolder.remove(key);
            sftpExecutor.disconnect();
        });
    }

}
