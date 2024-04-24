package com.rany.cake.devops.base.service.app;

import com.alibaba.fastjson.JSON;
import com.cake.framework.common.exception.BusinessException;
import com.rany.cake.devops.base.api.command.sftp.*;
import com.rany.cake.devops.base.api.dto.*;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.service.SftpService;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.FileTransferLogRepository;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.base.PathBuilders;
import com.rany.cake.devops.base.service.handler.host.HostConnectionService;
import com.rany.cake.devops.base.service.handler.sftp.*;
import com.rany.cake.devops.base.service.utils.Utils;
import com.rany.cake.devops.base.util.KeyConst;
import com.rany.cake.devops.base.util.MessageConst;
import com.rany.cake.devops.base.util.Valid;
import com.rany.cake.devops.base.util.sftp.SftpTransferStatus;
import com.rany.cake.devops.base.util.sftp.SftpTransferType;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.toolkit.lang.convert.Converts;
import com.rany.cake.toolkit.lang.id.ObjectIds;
import com.rany.cake.toolkit.lang.id.UUIds;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.progress.IgnoreOutputStream;
import com.rany.cake.toolkit.lang.utils.Exceptions;
import com.rany.cake.toolkit.lang.utils.Lists;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.net.base.file.sftp.SftpFile;
import com.rany.cake.toolkit.net.remote.CommandExecutors;
import com.rany.cake.toolkit.net.remote.channel.CommandExecutor;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import com.rany.cake.toolkit.net.remote.channel.SftpExecutor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@ShenyuService("/sftp/**")
@AllArgsConstructor
public class SftpRemoteService implements SftpService {

    private final HostDomainService hostDomainService;
    private final HostRepository hostRepository;
    private final SftpInternalService sftpInternalService;
    private final SftpBasicExecutorHolder sftpBasicExecutorHolder;
    private final FileTransferLogRepository fileTransferLogRepository;
    private final TransferProcessorManager transferProcessorManager;
    private final HostConnectionService hostConnectionService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public FileOpenDTO open(OpenSftpCommand openSftpCommand) {
        String currentUser = openSftpCommand.getUser();
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(openSftpCommand.getHostId());
        String sessionToken = sftpInternalService.createSessionToken(currentUser, openSftpCommand.getHostId());
        // 查询列表
        String path = executor.getHome();
        FileListDTO list = this.list(path, false, executor);
        // 返回数据
        FileOpenDTO openVO = new FileOpenDTO();
        openVO.setSessionToken(sessionToken);
        openVO.setHome(path);
        openVO.setCharset(executor.getCharset());
        openVO.setPath(list.getPath());
        openVO.setFiles(list.getFiles());
        return openVO;
    }

    private FileListDTO list(String path, Boolean all, SftpExecutor executor) {
        synchronized (executor) {
            List<SftpFile> fileList;
            if (all == null || !all) {
                // 不查询隐藏文件
                fileList = executor.listFilesFilter(path, f -> !f.getName().startsWith("."), false, true);
            } else {
                // 查询隐藏文件
                fileList = executor.ll(path);
            }
            // 返回
            FileListDTO fileListVO = new FileListDTO();
            List<FileDetailDTO> files = Converts.toList(fileList, FileDetailDTO.class);
            fileListVO.setPath(path);
            fileListVO.setFiles(files);
            return fileListVO;
        }
    }

    @Override
    public FileListDTO list(FileListCommand request) {
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        return this.list(request.getPath(), request.getAll(), executor);
    }

    @Override
    public FileListDTO listDir(FileListCommand request) {
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        synchronized (executor) {
            String path = request.getPath();
            List<SftpFile> fileList = executor.listDirs(path);
            // 返回
            FileListDTO fileListVO = new FileListDTO();
            List<FileDetailDTO> files = Converts.toList(fileList, FileDetailDTO.class);
            fileListVO.setPath(path);
            fileListVO.setFiles(files);
            return fileListVO;
        }
    }

    @Override
    public String mkdir(FileMkdirCommand request) {
        String path = Files1.getPath(request.getPath());
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        synchronized (executor) {
            executor.mkdirs(path);
        }
        // 设置日志参数
        return path;
    }

    @Override
    public String touch(FileTouchCommand request) {
        String path = Files1.getPath(request.getPath());
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        synchronized (executor) {
            executor.touch(path);
        }
        return path;
    }

    @Override
    public void truncate(FileTruncateCommand request) {
        String path = Files1.getPath(request.getPath());
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        synchronized (executor) {
            executor.truncate(path);
        }
    }

    @Override
    public String move(FileMoveCommand request) {
        String source = Files1.getPath(request.getSource());
        String target = Files1.getPath(request.getTarget());
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        synchronized (executor) {
            executor.mv(source, target);
        }
        return target;
    }

    @Override
    public void remove(FileRemoveCommand request) {
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        List<String> paths = request.getPaths();
        synchronized (executor) {
            paths.stream()
                    .map(Files1::getPath)
                    .forEach(executor::rm);
        }
    }

    @Override
    public String chmod(FileChmodCommand request) {
        String path = Files1.getPath(request.getPath());
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        synchronized (executor) {
            executor.chmod(path, request.getPermission());
        }
        // 返回权限
        return Files1.permission10toString(request.getPermission());
    }

    @Override
    public void chown(FileChownCommand request) {
        String path = Files1.getPath(request.getPath());
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        synchronized (executor) {
            executor.chown(path, request.getUid());
        }
    }

    @Override
    public void changeGroup(FileChangeGroupCommand request) {
        String path = Files1.getPath(request.getPath());
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        synchronized (executor) {
            executor.chgrp(path, request.getGid());
        }
    }

    @Override
    public List<String> checkFilePresent(FilePresentCheckCommand request) {
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        synchronized (executor) {
            return request.getNames().stream()
                    .filter(Strings::isNotBlank)
                    .filter(s -> executor.getFile(Files1.getPath(request.getPath(), s)) != null)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public String getUploadAccessToken(FileUploadCommand command) {
        SftpSessionTokenDTO tokenInfo = this.getTokenInfo(command.getSessionToken());
        if (!Objects.equals(command.getUser(), tokenInfo.getUserId())) {
            throw new BusinessException(DevOpsErrorMessage.SFTP_TOKEN_EXPIRE);
        }
        // 设置缓存信息
        SftpUploadInfoDTO uploadInfo = Converts.to(command, SftpUploadInfoDTO.class);
        uploadInfo.setUserId(command.getUser());
        uploadInfo.setHostId(tokenInfo.getHostId());
        // 设置缓存
        String accessToken = UUIds.random32();
        String key = Strings.format(KeyConst.SFTP_UPLOAD_ACCESS_TOKEN, accessToken);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(uploadInfo),
                KeyConst.SFTP_UPLOAD_ACCESS_EXPIRE, TimeUnit.SECONDS);
        return accessToken;
    }

    @Override
    public SftpUploadInfoDTO checkUploadAccessToken(String user, String accessToken) {
        // 获取缓存
        String key = Strings.format(KeyConst.SFTP_UPLOAD_ACCESS_TOKEN, accessToken);
        String value = redisTemplate.opsForValue().get(key);
        if (value == null || value.isEmpty()) {
            throw new BusinessException(DevOpsErrorMessage.SFTP_TOKEN_EXPIRE);
        }
        // 解析缓存
        SftpUploadInfoDTO uploadInfo = JSON.parseObject(value, SftpUploadInfoDTO.class);
        if (!Objects.equals(user, uploadInfo.getUserId())) {
            throw new BusinessException(DevOpsErrorMessage.SFTP_TOKEN_EXPIRE);
        }
        // 删除缓存
        redisTemplate.delete(key);
        return uploadInfo;
    }

    @Override
    public void upload(BatchFileUploadCommand command) {
        String user = command.getUser();
        List<FileUploadCommand> requestFiles = command.getFiles();
        String hostId = command.getHostId();
        // 初始化上传信息
        List<FileTransferLog> uploadFiles = Lists.newList();
        for (FileUploadCommand requestFile : requestFiles) {
            FileTransferLog upload = new FileTransferLog();
            upload.setAccountId(user);
            // upload.setUserName(user.getUsername());
            upload.setFileToken(requestFile.getFileToken());
            upload.setTransferType(SftpTransferType.UPLOAD.getType().byteValue());
            upload.setHostId(hostId);
            upload.setRemoteFile(requestFile.getRemotePath());
            upload.setLocalFile(requestFile.getLocalPath());
            upload.setCurrentSize(0L);
            upload.setFileSize(requestFile.getSize());
            upload.setNowProgress(0D);
            upload.setTransferStatus(SftpTransferStatus.WAIT.getStatus().byteValue());
            upload.init(user);

            uploadFiles.add(upload);
            fileTransferLogRepository.save(upload);
            // 通知添加
            transferProcessorManager.notifySessionAddEvent(user, hostId, upload);
        }
        // 提交上传任务
        for (FileTransferLog uploadFile : uploadFiles) {
            IFileTransferProcessor.of(FileTransferHint.transfer(uploadFile)).exec();
        }
    }

    @Override
    public void download(FileDownloadCommand request) {
        SftpSessionTokenDTO sftpSessionTokenDTO = sftpInternalService.getTokenInfo(request.getSessionToken());
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        String user = request.getUser();
        // 定义文件转换器
        Function<SftpFile, FileTransferLog> convert = file -> {
            // 本地保存路径
            String fileToken = ObjectIds.nextId();
            // 设置传输信息
            FileTransferLog download = new FileTransferLog();
            download.setAccountId(user);
            // download.setUserName(user.getUsername());
            download.setFileToken(fileToken);
            download.setTransferType(SftpTransferType.DOWNLOAD.getType().byteValue());
            download.setHostId(sftpSessionTokenDTO.getHostId());
            download.setRemoteFile(file.getPath());
            download.setLocalFile(PathBuilders.getSftpDownloadFilePath(fileToken));
            download.setCurrentSize(0L);
            download.setFileSize(file.getSize());
            download.setNowProgress(0D);
            download.setTransferStatus(SftpTransferStatus.WAIT.getStatus().byteValue());
            return download;
        };
        // 初始化下载信息
        List<FileTransferLog> downloadFiles = Lists.newList();
        List<String> paths = request.getPaths();
        for (String path : paths) {
            SftpFile file = executor.getFile(path);
            Valid.notNull(file, Strings.format(MessageConst.FILE_NOT_FOUND, path));
            // 如果是文件夹则递归所有文件
            if (file.isDirectory()) {
                List<SftpFile> childFiles = executor.listFiles(path, true, false);
                childFiles.forEach(f -> downloadFiles.add(convert.apply(f)));
            } else {
                downloadFiles.add(convert.apply(file));
            }
        }
        for (FileTransferLog downloadFile : downloadFiles) {
            fileTransferLogRepository.save(downloadFile);
            // 通知添加
            transferProcessorManager.notifySessionAddEvent(user, sftpSessionTokenDTO.getUserId(), downloadFile);
        }
        // 提交下载任务
        for (FileTransferLog downloadFile : downloadFiles) {
            IFileTransferProcessor.of(FileTransferHint.transfer(downloadFile)).exec();
        }
    }

    @Override
    public void packageDownload(FileDownloadCommand request) {
        String user = request.getUser();
        List<String> paths = request.getPaths();
        // 查询机器信息
        SftpSessionTokenDTO sftpSessionTokenDTO = sftpInternalService.getTokenInfo(request.getSessionToken());
        Host host = hostRepository.find(new HostId(sftpSessionTokenDTO.getHostId()));
        // 执行压缩命令
        String fileToken = ObjectIds.nextId();
        String zipPath = PathBuilders.getSftpPackageTempPath(host.getUsername(), fileToken, paths);
        String command = Utils.getSftpPackageCommand(zipPath, paths);
        try (SessionStore session = hostConnectionService.openSessionStore(host);
             CommandExecutor executor = session.getCommandExecutor(Strings.replaceCRLF(command))) {
            // 执行命令
            CommandExecutors.syncExecCommand(executor, new IgnoreOutputStream());
        } catch (Exception e) {
            throw Exceptions.app(MessageConst.EXECUTE_SFTP_ZIP_COMMAND_ERROR, e);
        }
        // 获取压缩文件信息
        SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(request.getSessionToken());
        SftpFile zipFile = executor.getFile(zipPath);
        Valid.notNull(zipFile, MessageConst.SFTP_ZIP_FILE_ABSENT);
        // 设置传输明细
        FileTransferLog download = new FileTransferLog();
        download.setAccountId(user);
        download.setFileToken(fileToken);
        download.setTransferType(SftpTransferType.DOWNLOAD.getType().byteValue());
        download.setHostId(sftpSessionTokenDTO.getHostId());
        download.setRemoteFile(zipPath);
        download.setLocalFile(PathBuilders.getSftpDownloadFilePath(fileToken));
        download.setCurrentSize(0L);
        download.setFileSize(zipFile.getSize());
        download.setNowProgress(0D);
        download.setTransferStatus(SftpTransferStatus.WAIT.getStatus().byteValue());
        download.init(user);
        fileTransferLogRepository.save(download);
        // 通知添加
        transferProcessorManager.notifySessionAddEvent(user, sftpSessionTokenDTO.getHostId(), download);
        // 提交任务
        IFileTransferProcessor.of(FileTransferHint.transfer(download)).exec();
    }

    @Override
    public void transferPause(String fileToken) {
        // 获取请求文件
        FileTransferLog transferLog = fileTransferLogRepository.getTransferLogByToken(fileToken);
        Valid.notNull(transferLog, MessageConst.UNSELECTED_TRANSFER_LOG);
        // 判断状态是否为进行中
        Integer status = Integer.valueOf(transferLog.getTransferStatus());
        Valid.isTrue(SftpTransferStatus.WAIT.getStatus().equals(status)
                || SftpTransferStatus.RUNNABLE.getStatus().equals(status), MessageConst.ILLEGAL_STATUS);
        // 获取执行器
        IFileTransferProcessor processor = transferProcessorManager.getProcessor(fileToken);
        if (processor != null) {
            // 执行器不为空则终止
            processor.stop();
        } else {
            Integer changeStatus;
            if (Objects.equals(SftpTransferType.PACKAGE.getType().byteValue(), transferLog.getTransferType())) {
                changeStatus = SftpTransferStatus.CANCEL.getStatus();
            } else {
                changeStatus = SftpTransferStatus.PAUSE.getStatus();
            }
            // 修改状态
            transferLog.setTransferStatus(changeStatus.byteValue());
            fileTransferLogRepository.update(transferLog);
            // 通知状态
            transferProcessorManager.notifySessionStatusEvent(transferLog.getAccountId(),
                    transferLog.getHostId(), transferLog.getFileToken(), changeStatus);
        }
    }

    @Override
    public void transferResume(String fileToken) {
        // 获取请求文件
        FileTransferLog transferLog = fileTransferLogRepository.getTransferLogByToken(fileToken);
        Valid.notNull(transferLog, MessageConst.UNSELECTED_TRANSFER_LOG);
        String hostId = transferLog.getHostId();
        // 判断状态是否为暂停
        Valid.eq(SftpTransferStatus.PAUSE.getStatus(), transferLog.getTransferStatus(), MessageConst.ILLEGAL_STATUS);
        this.transferResumeRetry(transferLog, hostId);
    }

    @Override
    public void transferRetry(String fileToken) {
        // 获取请求文件
        FileTransferLog transferLog = fileTransferLogRepository.getTransferLogByToken(fileToken);
        Valid.notNull(transferLog, MessageConst.UNSELECTED_TRANSFER_LOG);
        String hostId = transferLog.getHostId();
        // 判断状态是否为失败
        Valid.eq(SftpTransferStatus.ERROR.getStatus(), transferLog.getTransferStatus(), MessageConst.ILLEGAL_STATUS);
        this.transferResumeRetry(transferLog, hostId);
    }

    @Override
    public void transferReUpload(String fileToken) {
        this.transferReTransfer(fileToken, true);
    }

    @Override
    public void transferReDownload(String fileToken) {
        this.transferReTransfer(fileToken, false);
    }

    @Override
    public void transferPauseAll(String sessionToken) {

    }

    @Override
    public void transferResumeAll(String sessionToken) {

    }

    @Override
    public void transferRetryAll(String sessionToken) {

    }

    @Override
    public List<FileTransferLogDTO> transferList(String hostId) {
        return null;
    }

    @Override
    public void transferRemove(String fileToken) {

    }

    @Override
    public Integer transferClear(String hostId) {
        return null;
    }

    @Override
    public void transferPackage(String sessionToken, String packageType) {

    }

    @Override
    public FileTransferLogDTO getDownloadFilePath(Long id) {
        return null;
    }

    @Override
    public SftpSessionTokenDTO getTokenInfo(String sessionToken) {
        return null;
    }


    private void transferResumeRetry(FileTransferLog transferLog, String machineId) {
        // 修改状态为等待
        transferLog.setTransferStatus(SftpTransferStatus.WAIT.getStatus().byteValue());
        fileTransferLogRepository.update(transferLog);
        // 通知状态
        transferProcessorManager.notifySessionStatusEvent(transferLog.getAccountId(),
                machineId,
                transferLog.getFileToken(), SftpTransferStatus.WAIT.getStatus());
        // 提交下载
        IFileTransferProcessor.of(FileTransferHint.transfer(transferLog)).exec();
    }

    /**
     * 重新传输
     */
    private void transferReTransfer(String fileToken, boolean isUpload) {
        // 获取请求文件
        FileTransferLog transferLog = fileTransferLogRepository.getTransferLogByToken(fileToken);
        Valid.notNull(transferLog, MessageConst.UNSELECTED_TRANSFER_LOG);
        String machineId = transferLog.getHostId();
        // 暂停
        IFileTransferProcessor processor = transferProcessorManager.getProcessor(transferLog.getFileToken());
        if (processor != null) {
            processor.stop();
        }
        if (isUpload) {
            // 删除远程文件
            SftpExecutor executor = sftpBasicExecutorHolder.getBasicExecutor(machineId);
            SftpFile file = executor.getFile(transferLog.getRemoteFile());
            if (file != null) {
                executor.removeFile(transferLog.getRemoteFile());
            }
        } else {
            // 删除本地文件
            String localPath = Files1.getPath(SystemEnvAttr.SWAP_PATH.getValue(), transferLog.getLocalFile());
            Files1.delete(localPath);
        }
        // 修改进度
        transferLog.setId(transferLog.getId());
        transferLog.setTransferStatus(SftpTransferStatus.WAIT.getStatus().byteValue());
        transferLog.setNowProgress(0D);
        transferLog.setCurrentSize(0L);
        fileTransferLogRepository.update(transferLog);
        // 通知进度
        FileTransferNotifyProgressDTO progress = new FileTransferNotifyProgressDTO(Strings.EMPTY,
                Files1.getSize(transferLog.getFileSize()), "0");
        transferProcessorManager.notifySessionProgressEvent(transferLog.getAccountId(), machineId, transferLog.getFileToken(), progress);
        // 通知状态
        transferProcessorManager.notifySessionStatusEvent(transferLog.getAccountId(), machineId, transferLog.getFileToken(), SftpTransferStatus.WAIT.getStatus());
        // 提交下载
        IFileTransferProcessor.of(FileTransferHint.transfer(transferLog)).exec();
    }
}
