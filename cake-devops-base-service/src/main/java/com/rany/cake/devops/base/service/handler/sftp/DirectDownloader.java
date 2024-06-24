package com.rany.cake.devops.base.service.handler.sftp;

import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.SpringHolder;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.util.MessageConst;
import com.rany.cake.devops.base.util.Valid;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.io.SafeCloseable;
import com.rany.cake.toolkit.lang.io.Streams;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import com.rany.cake.toolkit.net.remote.channel.SftpExecutor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件直接上传
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/12/13 10:47
 */
@Slf4j
public class DirectDownloader implements SafeCloseable {

    private static final HostDomainService hostDomainService = SpringHolder.getBean(HostDomainService.class);
    private static final HostConnectionService hostConnectionService = SpringHolder.getBean(HostConnectionService.class);

    /**
     * 机器id
     */
    private final String machineId;

    /**
     * session
     */
    private SessionStore session;

    /**
     * 执行器
     */
    private SftpExecutor executor;

    public DirectDownloader(String machineId) {
        this.machineId = machineId;
    }

    /**
     * 打开连接
     *
     * @return this
     */
    public DirectDownloader open() {
        log.info("直接下载远程文件-建立连接-开始 machineId: {}", machineId);
        try {
            Host host = hostDomainService.getHost(new HostId(machineId));
            this.session = hostConnectionService.openSessionStore(host);
            log.info("直接下载远程文件-建立连接-成功 machineId: {}", machineId);
            return this;
        } catch (Exception e) {
            log.error("直接下载远程文件-建立连接-失败 machineId: {}", machineId, e);
            throw e;
        }
    }

    /**
     * 获取文件
     *
     * @param path path
     * @return 文件流
     * @throws IOException IOException
     */
    public InputStream getFile(String path) throws IOException {
        log.info("直接下载远程文件-开始执行 machineId: {}, path: {}", machineId, path);
        Valid.notNull(session, MessageConst.UNCONNECTED);
        try {
            // 打开执行器
            String charset = hostDomainService.getSftpCharset(machineId);
            this.executor = session.getSftpExecutor(charset);
            executor.connect();
            // 检查是否为本机
            if (SftpSupport.checkUseFileSystem(executor)) {
                // 是本机则返回文件流
                return Files1.openInputStreamFast(path);
            } else {
                // 不是本机获取sftp文件
                return executor.openInputStream(path);
            }
        } catch (IOException e) {
            log.error("直接下载远程文件-执行失败 machineId: {}, path: {}", machineId, path, e);
            throw e;
        }
    }

    /**
     * 关闭执行器
     */
    public void closeExecutor() {
        Streams.close(executor);
    }

    @Override
    public void close() {
        Streams.close(executor);
        Streams.close(session);
    }

}
