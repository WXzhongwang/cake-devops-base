package com.rany.cake.devops.base.service.app;

import com.alibaba.fastjson.JSON;
import com.cake.framework.common.exception.BusinessException;
import com.rany.cake.devops.base.api.command.sftp.*;
import com.rany.cake.devops.base.api.dto.*;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.service.SftpService;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.handler.sftp.SftpBasicExecutorHolder;
import com.rany.cake.devops.base.service.handler.sftp.SftpInternalService;
import com.rany.cake.devops.base.util.KeyConst;
import com.rany.cake.toolkit.lang.convert.Converts;
import com.rany.cake.toolkit.lang.id.UUIds;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.net.base.file.sftp.SftpFile;
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
        // Valid.isTrue(tokenInfo.getUserId().equals(command.getUser()), MessageConst.SESSION_EXPIRE);
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
    public void upload(String hostId, List<FileUploadCommand> requestFiles) {

    }

    @Override
    public void download(FileDownloadCommand request) {

    }

    @Override
    public void packageDownload(FileDownloadCommand request) {

    }

    @Override
    public void transferPause(String fileToken) {

    }

    @Override
    public void transferResume(String fileToken) {

    }

    @Override
    public void transferRetry(String fileToken) {

    }

    @Override
    public void transferReUpload(String fileToken) {

    }

    @Override
    public void transferReDownload(String fileToken) {

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
}
