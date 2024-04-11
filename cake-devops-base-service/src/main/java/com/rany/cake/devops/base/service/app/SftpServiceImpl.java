package com.rany.cake.devops.base.service.app;

import com.rany.cake.devops.base.api.command.sftp.*;
import com.rany.cake.devops.base.api.dto.*;
import com.rany.cake.devops.base.api.service.SftpService;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.List;

@Service
@Slf4j
@ShenyuService("/sftp/**")
@AllArgsConstructor
public class SftpServiceImpl implements SftpService {

    private final HostDomainService hostDomainService;

    @Override
    public FileOpenDTO open(OpenSftpCommand openSftpCommand) {
        return null;
    }

    @Override
    public FileListDTO list(FileListCommand request) {
        return null;
    }

    @Override
    public FileListDTO listDir(FileListCommand request) {
        return null;
    }

    @Override
    public String mkdir(FileMkdirCommand request) {
        return null;
    }

    @Override
    public String touch(FileTouchCommand request) {
        return null;
    }

    @Override
    public void truncate(FileTruncateCommand request) {

    }

    @Override
    public String move(FileMoveCommand request) {
        return null;
    }

    @Override
    public void remove(FileRemoveCommand request) {

    }

    @Override
    public String chmod(FileChmodCommand request) {
        return null;
    }

    @Override
    public void chown(FileChmodCommand request) {

    }

    @Override
    public void changeGroup(FileChangeGroupCommand request) {

    }

    @Override
    public List<String> checkFilePresent(FilePresentCheckCommand request) {
        return null;
    }

    @Override
    public String getUploadAccessToken(FileUploadCommand request) {
        return null;
    }

    @Override
    public SftpUploadInfoDTO checkUploadAccessToken(String accessToken) {
        return null;
    }

    @Override
    public void upload(Long machineId, List<FileUploadCommand> requestFiles) {

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
    public List<FileTransferLogDTO> transferList(Long machineId) {
        return null;
    }

    @Override
    public void transferRemove(String fileToken) {

    }

    @Override
    public Integer transferClear(Long machineId) {
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
    public Long getMachineId(String sessionToken) {
        return null;
    }

    @Override
    public SftpSessionTokenDTO getTokenInfo(String sessionToken) {
        return null;
    }
}
