package com.rany.cake.devops.base.service.handler.sftp;

import com.rany.cake.devops.base.api.dto.FileTransferNotifyProgressDTO;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.FileTransferLogRepository;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.infra.po.FileTransferLogPO;
import com.rany.cake.devops.base.service.SpringHolder;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.util.SchedulerPools;
import com.rany.cake.devops.base.util.sftp.SftpTransferStatus;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.math.Numbers;
import com.rany.cake.toolkit.lang.progress.ByteTransferRateProgress;
import com.rany.cake.toolkit.lang.utils.Exceptions;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import com.rany.cake.toolkit.net.remote.channel.SftpExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * sftp 传输文件基类
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/26 14:58
 */
@Slf4j
public abstract class FileTransferProcessor implements IFileTransferProcessor {

    protected HostDomainService hostDomainService = SpringHolder.getBean(HostDomainService.class);
    protected HostRepository hostRepository = SpringHolder.getBean(HostRepository.class);
    protected HostConnectionService hostConnectionService = SpringHolder.getBean(HostConnectionService.class);
    protected FileTransferLogRepository fileTransferLogRepository = SpringHolder.getBean(FileTransferLogRepository.class);
    protected TransferProcessorManager transferProcessorManager = SpringHolder.getBean(TransferProcessorManager.class);
    protected SessionStore sessionStore;
    protected SftpExecutor executor;

    protected FileTransferLog record;
    protected String accountId;

    protected String hostId;
    protected String fileToken;

    protected ByteTransferRateProgress progress;
    protected volatile boolean userCancel;

    public FileTransferProcessor(FileTransferLog record) {
        this.record = record;
        this.fileToken = record.getFileToken();
        this.accountId = record.getAccountId();
        this.hostId = record.getHostId();
    }

    @Override
    public void run() {
        // 判断是否可以传输
        this.record = fileTransferLogRepository.find(record.getId());
        if (record == null || !SftpTransferStatus.WAIT.getStatus().equals(record.getTransferStatus().intValue())) {
            return;
        }
        transferProcessorManager.addProcessor(fileToken, this);
        try {
            // 开始传输
            this.updateStatusAndNotify(SftpTransferStatus.RUNNABLE.getStatus());
            // 打开连接
            Host host = hostRepository.find(new HostId(hostId));
            this.sessionStore = hostConnectionService.openSessionStore(host);
            String charset = hostDomainService.getSftpCharset(hostId);
            this.executor = sessionStore.getSftpExecutor(charset);
            executor.connect();
            log.info("sftp传输文件-初始化完毕, 准备处理传输 fileToken: {}", fileToken);
            // 检查是否可以用文件系统传输
            if (SftpSupport.checkUseFileSystem(executor)) {
                // 直接拷贝
                SftpSupport.usingFsCopy(this);
            } else {
                // 处理
                this.handler();
            }
            log.info("sftp传输文件-传输完毕 fileToken: {}", fileToken);
        } catch (Exception e) {
            log.error("sftp传输文件-出现异常 fileToken: {}, e: {}, message: {}", fileToken, e.getClass().getName(), e.getMessage(), e);
            // 程序错误并非传输错误修改状态
            if (!userCancel) {
                log.error("sftp传输文件-运行异常 fileToken: {}", fileToken, e);
                this.updateStatusAndNotify(SftpTransferStatus.ERROR.getStatus());
            }
            // e.printStackTrace();
        } finally {
            transferProcessorManager.removeProcessor(fileToken);
            this.disconnected();
        }
    }

    @Override
    public void stop() {
        log.info("sftp传输文件-用户暂停 fileToken: {}", fileToken);
        this.userCancel = true;
        this.updateStatusAndNotify(SftpTransferStatus.PAUSE.getStatus());
        this.disconnected();
    }

    /**
     * 处理操作
     */
    protected abstract void handler();

    /**
     * 初始化进度条
     */
    protected void initProgress(ByteTransferRateProgress progress) {
        this.progress = progress;
        progress.computeRate();
        progress.rateExecutor(SchedulerPools.SFTP_TRANSFER_RATE_SCHEDULER);
        progress.rateAcceptor(this::transferAccept);
        progress.callback(this::transferDoneCallback);
    }

    /**
     * 传输回调
     *
     * @param progress progress
     */
    protected void transferAccept(ByteTransferRateProgress progress) {
        try {
            if (progress.isDone()) {
                return;
            }
            String progressRate = Numbers.setScale(progress.getProgress() * 100, 2);
            String transferRate = Files1.getSize(progress.getNowRate());
            String transferCurrent = Files1.getSize(progress.getCurrent());
            // debug
            // log.info(transferCurrent + " " + progressRate + "% " + transferRate + "/s");
            // notify progress
            this.notifyProgress(transferRate, transferCurrent, progressRate);
        } catch (Exception e) {
            log.error("sftp-传输信息回调异常 fileToken: {}, digest: {}", fileToken, Exceptions.getDigest(e), e);
        }
    }

    /**
     * 传输完成回调
     */
    protected void transferDoneCallback() {
        try {
            FileTransferLogPO update = new FileTransferLogPO();
            update.setId(record.getId());
            if (progress.isError()) {
                // 非用户取消更新状态
                if (!userCancel) {
                    this.updateStatusAndNotify(SftpTransferStatus.ERROR.getStatus());
                }
            } else {
                String transferCurrent = Files1.getSize(progress.getCurrent());
                String transferRate = Files1.getSize(progress.getNowRate());
                // notify progress
                this.notifyProgress(transferRate, transferCurrent, "100");
                // notify status
                this.updateStatusAndNotify(SftpTransferStatus.FINISH.getStatus(), 100D, progress.getEnd());
            }
        } catch (Exception e) {
            log.error("sftp-传输完成回调异常 fileToken: {}, digest: {}", fileToken, Exceptions.getDigest(e), e);
            // e.printStackTrace();
        }
    }

    protected void updateStatusAndNotify(Integer status) {
        if (progress == null) {
            this.updateStatusAndNotify(status, null, null);
        } else {
            this.updateStatusAndNotify(status, progress.getProgress() * 100, progress.getCurrent());
        }
    }

    /**
     * 更新状态并且通知
     *
     * @param status      status
     * @param progress    progress
     * @param currentSize currentSize
     */
    protected void updateStatusAndNotify(Integer status, Double progress, Long currentSize) {
        Long id = record.getId();
        if (id == null) {
            return;
        }
        record.setTransferStatus(status.byteValue());
        record.setNowProgress(progress);
        record.setCurrentSize(currentSize);
        int effect = fileTransferLogRepository.update(record);
        log.info("sftp传输文件-更新状态 fileToken: {}, status: {}, progress: {}, currentSize: {}, effect: {}", fileToken, status, progress, currentSize, effect);
        // notify status
        transferProcessorManager.notifySessionStatusEvent(accountId, hostId, fileToken, status);
    }

    /**
     * 通知进度
     *
     * @param rate     速度
     * @param current  当前位置
     * @param progress 进度
     */
    protected void notifyProgress(String rate, String current, String progress) {
        FileTransferNotifyProgressDTO notifyProgress = new FileTransferNotifyProgressDTO(rate, current, progress);
        transferProcessorManager.notifySessionProgressEvent(accountId, hostId, fileToken, notifyProgress);
    }

    /**
     * 断开连接
     */
    protected void disconnected() {
        if (executor != null) {
            executor.disconnect();
        }
    }

}
