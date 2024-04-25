package com.rany.cake.devops.base.api.service;

import com.rany.cake.devops.base.api.command.sftp.*;
import com.rany.cake.devops.base.api.command.sftp.transfer.*;
import com.rany.cake.devops.base.api.dto.*;

import java.util.List;

public interface SftpService {

    /**
     * 打开sftp连接
     *
     * @param openSftpCommand command
     * @return FileOpenVO
     */
    FileOpenDTO open(OpenSftpCommand openSftpCommand);

    /**
     * 文件列表
     *
     * @param request request
     * @return list
     */
    FileListDTO list(FileListCommand request);

    /**
     * 文件夹列表
     *
     * @param request request
     * @return list
     */
    FileListDTO listDir(FileListCommand request);

    /**
     * mkdir
     *
     * @param request request
     * @return 文件目录
     */
    String mkdir(FileMkdirCommand request);

    /**
     * touch
     *
     * @param request request
     * @return 文件目录
     */
    String touch(FileTouchCommand request);

    /**
     * truncate
     *
     * @param request request
     */
    void truncate(FileTruncateCommand request);

    /**
     * mv
     *
     * @param request request
     * @return 移动后的位置
     */
    String move(FileMoveCommand request);

    /**
     * rm -rf
     *
     * @param request request
     */
    void remove(FileRemoveCommand request);

    /**
     * chmod
     *
     * @param request request
     * @return 权限字符串
     */
    String chmod(FileChmodCommand request);

    /**
     * chown
     *
     * @param request request
     */
    void chown(FileChownCommand request);

    /**
     * chgrp
     *
     * @param request request
     */
    void changeGroup(FileChangeGroupCommand request);

    /**
     * 检查文件是否存在
     *
     * @param request request
     * @return 存在的文件名称
     */
    List<String> checkFilePresent(FilePresentCheckCommand request);

    /**
     * 获取上传文件 accessToken
     *
     * @param request request
     * @return accessToken
     */
    String getUploadAccessToken(FileUploadCommand request);

    /**
     * 检查上传token
     *
     * @param accessToken accessToken
     * @return machineId
     */
    SftpUploadInfoDTO checkUploadAccessToken(String userId, String accessToken);

    /**
     * 上传文件
     *
     * @param uploadCommand uploadCommand
     */
    void upload(BatchFileUploadCommand uploadCommand);

    /**
     * 下载文件
     *
     * @param request request
     */
    void download(FileDownloadCommand request);

    /**
     * 打包下载文件
     *
     * @param request request
     */
    void packageDownload(FileDownloadCommand request);

    /**
     * 传输暂停
     *
     * @param transferPauseCommand command
     */
    void transferPause(TransferPauseCommand transferPauseCommand);

    /**
     * 传输恢复
     *
     * @param transferResumeCommand command
     */
    void transferResume(TransferResumeCommand transferResumeCommand);

    /**
     * 传输失败重试
     *
     * @param transferRetryCommand command
     */
    void transferRetry(TransferRetryCommand transferRetryCommand);

    /**
     * 重新上传
     *
     * @param transferReUploadCommand command
     */
    void transferReUpload(TransferReUploadCommand transferReUploadCommand);

    /**
     * 重新下载
     *
     * @param transferReDownloadCommand command
     */
    void transferReDownload(TransferReDownloadCommand transferReDownloadCommand);

    /**
     * 传输暂停
     *
     * @param transferPauseAllCommand command
     */
    void transferPauseAll(TransferPauseAllCommand transferPauseAllCommand);

    /**
     * 传输恢复
     *
     * @param transferResumeAllCommand command
     */
    void transferResumeAll(TransferResumeAllCommand transferResumeAllCommand);

    /**
     * 传输失败重试
     *
     * @param transferRetryAllCommand command
     */
    void transferRetryAll(TransferRetryAllCommand transferRetryAllCommand);

    /**
     * 传输列表
     *
     * @param sessionToken sessionToken
     * @return rows
     */
    List<FileTransferLogDTO> transferList(String sessionToken);

    /**
     * 传输删除 (单个)
     *
     * @param transferRemoveCommand command
     */
    void transferRemove(TransferRemoveCommand transferRemoveCommand);

    /**
     * 传输清空 (全部)
     *
     * @param command command
     * @return effect
     */
    Integer transferClear(TransferClearCommand command);

    /**
     * 传输打包 全部已完成未删除的文件
     *
     * @param sessionToken sessionToken
     * @param packageType  packageType
     */
    void transferPackage(String sessionToken, String packageType);

    /**
     * 获取 sftp 下载文件本地路径
     *
     * @param logId logId
     * @return FileTransferLogDTO
     */
    FileTransferLogDTO getDownloadFilePath(Long logId);


    /**
     * 获取 token 信息
     *
     * @param sessionToken sessionToken
     * @return token 信息
     */
    SftpSessionTokenDTO getTokenInfo(String sessionToken);
}
