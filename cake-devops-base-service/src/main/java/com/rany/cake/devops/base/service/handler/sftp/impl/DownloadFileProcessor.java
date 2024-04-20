package com.rany.cake.devops.base.service.handler.sftp.impl;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import com.rany.cake.devops.base.service.handler.sftp.FileTransferProcessor;
import com.rany.cake.devops.base.util.SchedulerPools;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.toolkit.lang.Threads;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.net.remote.channel.SftpDownloader;
import lombok.extern.slf4j.Slf4j;

/**
 * 文件下载处理器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/25 18:38
 */
@Slf4j
public class DownloadFileProcessor extends FileTransferProcessor {

    public DownloadFileProcessor(FileTransferLog record) {
        super(record);
    }

    @Override
    public void exec() {
        String localFile = record.getLocalFile();
        String localAbsolutePath = Files1.getPath(SystemEnvAttr.SWAP_PATH.getValue(), localFile);
        log.info("sftp文件下载-提交任务 fileToken: {}, machineId: {}, local: {}, remote: {}, record: {}",
                fileToken, hostId, localAbsolutePath, record.getRemoteFile(), JSON.toJSONString(record));
        Threads.start(this, SchedulerPools.SFTP_DOWNLOAD_SCHEDULER);
    }

    @Override
    protected void handler() {
        String remoteFile = record.getRemoteFile();
        String localFile = record.getLocalFile();
        String localAbsolutePath = Files1.getPath(SystemEnvAttr.SWAP_PATH.getValue(), localFile);
        log.info("sftp文件下载-开始传输 fileToken: {}, machineId: {}, local: {}, remote: {}",
                fileToken, hostId, localAbsolutePath, remoteFile);
        SftpDownloader download = executor.download(remoteFile, localAbsolutePath);
        this.initProgress(download.getProgress());
        download.run();
    }

}
