package com.rany.cake.devops.base.service.handler.sftp.impl;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.api.dto.FileTransferNotifyProgressDTO;
import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import com.rany.cake.devops.base.domain.repository.FileTransferLogRepository;
import com.rany.cake.devops.base.service.SpringHolder;
import com.rany.cake.devops.base.service.handler.sftp.IFileTransferProcessor;
import com.rany.cake.devops.base.service.handler.sftp.TransferProcessorManager;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.SchedulerPools;
import com.rany.cake.devops.base.util.sftp.SftpTransferStatus;
import com.rany.cake.devops.base.util.sftp.SftpTransferType;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.toolkit.lang.Threads;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.io.Streams;
import com.rany.cake.toolkit.lang.io.compress.CompressTypeEnum;
import com.rany.cake.toolkit.lang.io.compress.FileCompressor;
import com.rany.cake.toolkit.lang.math.Numbers;
import com.rany.cake.toolkit.lang.utils.Lists;
import com.rany.cake.toolkit.lang.utils.Maps;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 文件打包处理器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/11/6 17:04
 */
@Slf4j
public class PackageFileProcessor implements IFileTransferProcessor {

    protected static FileTransferLogRepository fileTransferLogRepository = SpringHolder.getBean(FileTransferLogRepository.class);

    protected static TransferProcessorManager transferProcessorManager = SpringHolder.getBean(TransferProcessorManager.class);

    private static final String FINISH_PROGRESS = "100";

    /**
     * 打包文件
     */
    private final FileTransferLog packageFile;

    /**
     * 文件列表
     */
    private final List<FileTransferLog> fileList;

    /**
     * 文件名映射
     */
    private Map<String, FileTransferLog> nameMapping;

    /**
     * 当前大小
     */
    private final AtomicLong currentSize;

    /**
     * 文件总大小
     */
    private final long totalSize;

    /**
     * 文件压缩器
     */
    private FileCompressor compressor;

    /**
     * 压缩文件路径
     */
    private String compressPath;

    private final Long accountId;

    private final String hostId;

    private final String fileToken;

    private volatile boolean userCancel;

    private volatile boolean done;

    public PackageFileProcessor(FileTransferLog packageFile, List<FileTransferLog> fileList) {
        this.packageFile = packageFile;
        this.fileList = fileList;
        this.fileToken = packageFile.getFileToken();
        this.currentSize = new AtomicLong();
        this.totalSize = packageFile.getFileSize();
        this.accountId = packageFile.getAccountId();
        this.hostId = packageFile.getHostId();
    }

    @Override
    public void exec() {
        String localFile = packageFile.getLocalFile();
        this.compressPath = Files1.getPath(SystemEnvAttr.SWAP_PATH.getValue(), localFile);
        log.info("sftp文件打包-提交任务 fileToken: {} machineId: {}, local: {}, remote: {}, record: {}, fileList: {}",
                fileToken, hostId, compressPath, packageFile.getRemoteFile(),
                JSON.toJSONString(packageFile), JSON.toJSONString(fileList));
        Threads.start(this, SchedulerPools.SFTP_PACKAGE_SCHEDULER);
    }

    @Override
    public void run() {
        // 判断是否可以传输
        FileTransferLog fileTransferLog = fileTransferLogRepository.find(packageFile.getId());
        if (fileTransferLog == null || !SftpTransferStatus.WAIT.getStatus().equals(fileTransferLog.getTransferStatus().intValue())) {
            return;
        }
        transferProcessorManager.addProcessor(fileToken, this);
        try {
            // 通知状态runnable
            this.updateStatus(SftpTransferStatus.RUNNABLE);
            // 初始化压缩器
            this.compressor = CompressTypeEnum.ZIP.compressor().get();
            compressor.setAbsoluteCompressPath(compressPath);
            compressor.compressNotify(this::notifyProgress);
            // 添加压缩文件
            this.initCompressFiles();
            // 添加压缩清单
            this.initCompressFileRaw();
            // 二次检查状态 防止在添加文件过程中取消或者删除
            fileTransferLog = fileTransferLogRepository.find(packageFile.getId());
            if (fileTransferLog == null || !SftpTransferStatus.RUNNABLE.getStatus()
                    .equals(fileTransferLog.getTransferStatus().intValue())) {
                return;
            }
            // 开始压缩
            this.compressor.compress();
            // 传输完成通知
            this.updateStatus(SftpTransferStatus.FINISH);
        } catch (Exception e) {
            log.error("sftp压缩文件-出现异常 fileToken: {}, e: {}, message: {}", fileToken, e.getClass().getName(), e.getMessage(), e);
            // 程序错误并非传输错误修改状态
            if (!userCancel) {
                log.error("sftp传输文件-运行异常 fileToken: {}", fileToken, e);
                this.updateStatus(SftpTransferStatus.ERROR);
            }
            // e.printStackTrace();

        } finally {
            this.done = true;
            transferProcessorManager.removeProcessor(fileToken);
        }
    }

    @Override
    public void stop() {
        log.info("sftp传输打包-用户取消 fileToken: {}", fileToken);
        this.userCancel = true;
        // 修改状态为已取消
        this.updateStatus(SftpTransferStatus.CANCEL);
        // 取消
        if (compressor != null) {
            Streams.close(compressor.getCloseable());
        }
    }

    /**
     * 初始化压缩文件
     */
    private void initCompressFiles() {
        this.nameMapping = Maps.newLinkedMap();
        for (int i = 0; i < fileList.size(); i++) {
            FileTransferLog fileLog = fileList.get(i);
            String remoteFile = fileLog.getRemoteFile();
            String localFilePath = Files1.getPath(SystemEnvAttr.SWAP_PATH.getValue(), fileLog.getLocalFile());
            if (!Files1.isFile(new File(localFilePath))) {
                continue;
            }
            // 添加mapping
            String remoteFileName;
            if (nameMapping.containsKey(remoteFile)) {
                remoteFileName = remoteFile + "_" + (i + 1);
            } else {
                remoteFileName = remoteFile;
            }
            nameMapping.put(remoteFileName, fileLog);
            compressor.addFile(remoteFileName, localFilePath);
        }
    }

    /**
     * 添加压缩文件清单到压缩列表
     */
    private void initCompressFileRaw() {
        // 设置文件清单
        List<String> compressFileRaw = Lists.newList();
        for (FileTransferLog fileLog : fileList) {
            String remoteFile = fileLog.getRemoteFile();
            String label = SftpTransferType.of(fileLog.getTransferType()).getLabel();
            String localFilePath = Files1.getPath(SystemEnvAttr.SWAP_PATH.getValue(), fileLog.getLocalFile());
            String status = Files1.isFile(localFilePath) ? "成功" : "未找到文件";
            // 添加raw
            compressFileRaw.add(label + Const.SPACE + status + Const.SPACE + remoteFile);
        }
        // 设置文件清单文件
        String compressRawListFile = String.join(Const.LF, compressFileRaw) + Const.LF;
        InputStream compressRawListStream = Streams.toInputStream(compressRawListFile);
        compressor.addFile(Const.COMPRESS_LIST_FILE, compressRawListStream);
    }

    /**
     * 通知状态
     *
     * @param status status
     */
    private void updateStatus(SftpTransferStatus status) {
        packageFile.setId(packageFile.getId());
        packageFile.setTransferStatus(status.getStatus().byteValue());
        if (SftpTransferStatus.FINISH.equals(status)) {
            // 设置压缩文件实际大小
            File compressFile = new File(compressPath);
            if (Files1.isFile(compressFile)) {
                packageFile.setFileSize(compressFile.length());
                packageFile.setCurrentSize(compressFile.length());
            } else {
                packageFile.setCurrentSize(packageFile.getFileSize());
            }
            packageFile.setNowProgress(100D);
        }
        int effect = fileTransferLogRepository.update(packageFile);
        log.info("sftp传输压缩-更新状态 fileToken: {}, status: {}, effect: {}", fileToken, status, effect);
        if (SftpTransferStatus.FINISH.equals(status)) {
            // 通知进度
            FileTransferNotifyProgressDTO notifyProgress = new FileTransferNotifyProgressDTO(Strings.EMPTY, Files1.getSize(totalSize), FINISH_PROGRESS);
            transferProcessorManager.notifySessionProgressEvent(accountId, hostId, fileToken, notifyProgress);
        }
        // 通知状态
        transferProcessorManager.notifySessionStatusEvent(accountId, hostId, fileToken, status.getStatus());
    }

    /**
     * 通知进度
     *
     * @param name name
     */
    private void notifyProgress(String name) {
        if (done) {
            return;
        }
        FileTransferLog compressedFile = nameMapping.get(name);
        if (compressedFile == null) {
            return;
        }
        // 计算进度
        long curr = currentSize.addAndGet(compressedFile.getFileSize());
        double progress = this.getProgress();
        String progressRate = Numbers.setScale(progress, 2);
        // 更新进度
        packageFile.setId(packageFile.getId());
        packageFile.setCurrentSize(curr);
        packageFile.setNowProgress(progress);
        fileTransferLogRepository.update(packageFile);
        // 通知进度
        FileTransferNotifyProgressDTO notifyProgress = new FileTransferNotifyProgressDTO(Strings.EMPTY, Files1.getSize(curr), progressRate);
        transferProcessorManager.notifySessionProgressEvent(accountId, hostId, fileToken, notifyProgress);
    }

    /**
     * 获取当前进度
     *
     * @return 当前进度
     */
    protected double getProgress() {
        if (totalSize == 0) {
            return 0;
        }
        return ((double) currentSize.get() / (double) totalSize) * 100;
    }

}
