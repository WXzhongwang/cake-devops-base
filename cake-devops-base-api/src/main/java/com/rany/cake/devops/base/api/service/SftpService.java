package com.rany.cake.devops.base.api.service;

import com.rany.cake.devops.base.api.command.sftp.*;
import com.rany.cake.devops.base.api.dto.*;

import java.util.List;

public interface SftpService {

    /**
     * 打开sftp连接
     *
     * @param machineId 机器id
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
    void chown(FileChmodCommand request);

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
    SftpUploadInfoDTO checkUploadAccessToken(String accessToken);

    /**
     * 上传文件
     *
     * @param machineId    machineId
     * @param requestFiles requestFiles
     */
    void upload(Long machineId, List<FileUploadCommand> requestFiles);

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
     * @param fileToken fileToken
     */
    void transferPause(String fileToken);

    /**
     * 传输恢复
     *
     * @param fileToken fileToken
     */
    void transferResume(String fileToken);

    /**
     * 传输失败重试
     *
     * @param fileToken fileToken
     */
    void transferRetry(String fileToken);

    /**
     * 重新上传
     *
     * @param fileToken fileToken
     */
    void transferReUpload(String fileToken);

    /**
     * 重新下载
     *
     * @param fileToken fileToken
     */
    void transferReDownload(String fileToken);

    /**
     * 传输暂停
     *
     * @param sessionToken sessionToken
     */
    void transferPauseAll(String sessionToken);

    /**
     * 传输恢复
     *
     * @param sessionToken sessionToken
     */
    void transferResumeAll(String sessionToken);

    /**
     * 传输失败重试
     *
     * @param sessionToken sessionToken
     */
    void transferRetryAll(String sessionToken);

    /**
     * 传输列表
     *
     * @param machineId 机器id
     * @return rows
     */
    List<FileTransferLogDTO> transferList(Long machineId);

    /**
     * 传输删除 (单个)
     *
     * @param fileToken fileToken
     */
    void transferRemove(String fileToken);

    /**
     * 传输清空 (全部)
     *
     * @param machineId machineId
     * @return effect
     */
    Integer transferClear(Long machineId);

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
     * @param id id
     * @return FileTransferLogDO
     */
    FileTransferLogDTO getDownloadFilePath(Long id);

    /**
     * 获取机器id
     *
     * @param sessionToken sessionToken
     * @return 机器id
     */
    Long getMachineId(String sessionToken);

    /**
     * 获取 token 信息
     *
     * @param sessionToken sessionToken
     * @return token 信息
     */
    SftpSessionTokenDTO getTokenInfo(String sessionToken);
}
