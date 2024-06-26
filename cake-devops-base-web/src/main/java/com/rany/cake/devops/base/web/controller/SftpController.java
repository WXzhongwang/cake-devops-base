package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.sftp.*;
import com.rany.cake.devops.base.api.command.sftp.transfer.*;
import com.rany.cake.devops.base.api.dto.*;
import com.rany.cake.devops.base.api.service.SftpService;
import com.rany.cake.devops.base.service.base.PathBuilders;
import com.rany.cake.devops.base.service.base.UserHolder;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.Valid;
import com.rany.cake.devops.base.util.sftp.SftpPackageType;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.dingtalk.starter.annotation.CurrentUser;
import com.rany.cake.toolkit.lang.id.ObjectIds;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.utils.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/devops/sftp")
public class SftpController {

    @Resource
    private SftpService sftpService;

    @PostMapping("/open")
    @ApiOperation(value = "打开sftp")
    public PojoResult<FileOpenDTO> open(@RequestBody OpenSftpCommand command, @CurrentUser SsoUser ssoUser) {
        Valid.notNull(command.getHostId());
        FileOpenDTO open = sftpService.open(command);
        return PojoResult.succeed(open);
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取文件夹列表")
    public PojoResult<FileListDTO> list(@RequestBody FileListCommand command) {
        Valid.checkNormalize(command.getPath());
        FileListDTO list = sftpService.list(command);
        return PojoResult.succeed(list);
    }

    @PostMapping("/list-dir")
    @ApiOperation(value = "获取文件夹列表")
    public PojoResult<FileListDTO> listDir(@RequestBody FileListCommand request) {
        Valid.checkNormalize(request.getPath());
        FileListDTO fileListDTO = sftpService.listDir(request);
        return PojoResult.succeed(fileListDTO);
    }

    @PostMapping("/mkdir")
    @ApiOperation(value = "创建文件夹")
    public PojoResult<String> mkdir(@RequestBody FileMkdirCommand request) {
        Valid.checkNormalize(request.getPath());
        String mkdir = sftpService.mkdir(request);
        return PojoResult.succeed(mkdir);
    }

    @PostMapping("/touch")
    @ApiOperation(value = "创建文件")
    public PojoResult<String> touch(@RequestBody FileTouchCommand request) {
        Valid.checkNormalize(request.getPath());
        String touch = sftpService.touch(request);
        return PojoResult.succeed(touch);
    }

    @PostMapping("/truncate")
    @ApiOperation(value = "截断文件")
    public PojoResult<Void> truncate(@RequestBody FileTruncateCommand request) {
        Valid.checkNormalize(request.getPath());
        sftpService.truncate(request);
        return PojoResult.succeed(null);
    }

    @PostMapping("/move")
    @ApiOperation(value = "移动文件")
    public PojoResult<String> move(@RequestBody FileMoveCommand request) {
        Valid.checkNormalize(request.getSource());
        Valid.notBlank(request.getTarget());
        String move = sftpService.move(request);
        return PojoResult.succeed(move);
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除文件/文件夹")
    public PojoResult<Void> remove(@RequestBody FileRemoveCommand request) {
        List<String> paths = Valid.notEmpty(request.getPaths());
        paths.forEach(Valid::checkNormalize);
        boolean isSafe = paths.stream().noneMatch(Const.UNSAFE_FS_DIR::contains);
        Valid.isSafe(isSafe);
        sftpService.remove(request);
        return PojoResult.succeed(null);
    }

    @PostMapping("/chmod")
    @ApiOperation(value = "修改权限")
    public PojoResult<String> chmod(@RequestBody FileChmodCommand request) {
        Valid.checkNormalize(request.getPath());
        Valid.notNull(request.getPermission());
        String chmod = sftpService.chmod(request);
        return PojoResult.succeed(chmod);
    }

    @PostMapping("/chown")
    @ApiOperation(value = "修改所有者")
    public PojoResult<Void> chown(@RequestBody FileChownCommand request) {
        Valid.checkNormalize(request.getPath());
        Valid.notNull(request.getUid());
        sftpService.chown(request);
        return PojoResult.succeed(null);
    }

    @PostMapping("/chgrp")
    @ApiOperation(value = "修改所有组")
    public PojoResult<Void> changeGroup(@RequestBody FileChangeGroupCommand request) {
        Valid.checkNormalize(request.getPath());
        Valid.notNull(request.getGid());
        sftpService.changeGroup(request);
        return PojoResult.succeed(null);
    }

    @PostMapping("/check-present")
    @ApiOperation(value = "检查文件是否存在")
    public ListResult<String> checkFilePresent(@RequestBody FilePresentCheckCommand request) {
        Valid.checkNormalize(request.getPath());
        Valid.notEmpty(request.getNames());
        Valid.checkUploadSize(request.getSize());
        List<String> strings = sftpService.checkFilePresent(request);
        return ListResult.succeed(strings);
    }

    @PostMapping("/upload/token")
    @ApiOperation(value = "获取上传文件accessToken")
    public PojoResult<String> getUploadAccessToken(@RequestBody FileUploadCommand request) {
        String uploadAccessToken = sftpService.getUploadAccessToken(request);
        return PojoResult.succeed(uploadAccessToken);
    }


    @PostMapping("/upload/exec")
    @ApiOperation(value = "上传文件")
    public PojoResult<Void> uploadFile(@RequestParam("accessToken") String accessToken, @RequestParam("files") List<MultipartFile> files) throws IOException {
        SsoUser ssoUser = UserHolder.get();
        // 检查文件
        Valid.notBlank(accessToken);
        Valid.notEmpty(files);
        // 检查token
        SftpUploadInfoDTO uploadInfo = sftpService.checkUploadAccessToken(ssoUser.getUserId(), accessToken);
        String machineId = uploadInfo.getHostId();
        String remotePath = uploadInfo.getRemotePath();

        List<FileUploadCommand> requestFiles = Lists.newList();
        for (MultipartFile file : files) {
            // 传输文件到本地
            String fileToken = ObjectIds.nextId();
            String localPath = PathBuilders.getSftpUploadFilePath(fileToken);
            Path localAbsolutePath = Paths.get(SystemEnvAttr.SWAP_PATH.getValue(), localPath);
            Files1.touch(localAbsolutePath);
            file.transferTo(localAbsolutePath);

            // 提交任务
            FileUploadCommand request = new FileUploadCommand();
            request.setHostId(machineId);
            request.setFileToken(fileToken);
            request.setLocalPath(localPath);
            request.setRemotePath(Files1.getPath(remotePath, file.getOriginalFilename()));
            request.setSize(file.getSize());
            requestFiles.add(request);
        }
        BatchFileUploadCommand batchFileUploadCommand = new BatchFileUploadCommand();
        batchFileUploadCommand.setHostId(machineId);
        batchFileUploadCommand.setFiles(requestFiles);
        sftpService.upload(batchFileUploadCommand);
        return PojoResult.succeed();
    }

    @PostMapping("/download/exec")
    @ApiOperation(value = "下载文件")
    public PojoResult<Void> downloadFile(@RequestBody FileDownloadCommand request) {
        List<String> paths = Valid.notEmpty(request.getPaths());
        paths.forEach(Valid::checkNormalize);
        sftpService.download(request);
        return PojoResult.succeed();
    }

    @PostMapping("/package-download/exec")
    @ApiOperation(value = "打包下载文件")
    public PojoResult<Void> packageDownloadFile(@RequestBody FileDownloadCommand request) {
        List<String> paths = Valid.notEmpty(request.getPaths());
        paths.forEach(Valid::checkNormalize);
        sftpService.packageDownload(request);
        return PojoResult.succeed();
    }

    @GetMapping("/transfer/{fileToken}/pause")
    @ApiOperation(value = "暂停文件传输")
    public PojoResult<Void> transferPause(@PathVariable("fileToken") String fileToken,
                                          @CurrentUser SsoUser ssoUser) {
        TransferPauseCommand transferPauseCommand = new TransferPauseCommand();
        transferPauseCommand.setFileToken(fileToken);
        transferPauseCommand.setUser(ssoUser.getUserId());
        sftpService.transferPause(transferPauseCommand);
        return PojoResult.succeed();
    }

    @GetMapping("/transfer/{fileToken}/resume")
    @ApiOperation(value = "恢复文件传输")
    public PojoResult<Void> transferResume(@PathVariable("fileToken") String fileToken,
                                           @CurrentUser SsoUser ssoUser) {
        TransferResumeCommand transferResumeCommand = new TransferResumeCommand();
        transferResumeCommand.setFileToken(fileToken);
        transferResumeCommand.setUser(ssoUser.getUserId());
        sftpService.transferResume(transferResumeCommand);
        return PojoResult.succeed();
    }

    @GetMapping("/transfer/{fileToken}/retry")
    @ApiOperation(value = "传输失败重试")
    public PojoResult<Void> transferRetry(@PathVariable("fileToken") String fileToken
            ,
                                          @CurrentUser SsoUser ssoUser) {
        TransferRetryCommand transferRetryCommand = new TransferRetryCommand();
        transferRetryCommand.setFileToken(fileToken);
        transferRetryCommand.setUser(ssoUser.getUserId());
        sftpService.transferRetry(transferRetryCommand);
        return PojoResult.succeed();
    }


    @GetMapping("/transfer/{fileToken}/re-upload")
    @ApiOperation(value = "重新上传文件")
    public PojoResult<Void> transferReUpload(@PathVariable("fileToken") String fileToken
            ,
                                             @CurrentUser SsoUser ssoUser) {
        TransferReUploadCommand transferReUploadCommand = new TransferReUploadCommand();
        transferReUploadCommand.setFileToken(fileToken);
        transferReUploadCommand.setUser(ssoUser.getUserId());
        sftpService.transferReUpload(transferReUploadCommand);
        return PojoResult.succeed();
    }

    @GetMapping("/transfer/{fileToken}/re-download")
    @ApiOperation(value = "重新下载文件")
    public PojoResult<Void> transferReDownload(@PathVariable("fileToken") String fileToken,
                                               @CurrentUser SsoUser ssoUser) {
        TransferReDownloadCommand command = new TransferReDownloadCommand();
        command.setFileToken(fileToken);
        command.setUser(ssoUser.getUserId());
        sftpService.transferReDownload(command);
        return PojoResult.succeed();
    }

    @GetMapping("/transfer/{sessionToken}/pause-all")
    @ApiOperation(value = "暂停所有传输")
    public PojoResult<Void> transferPauseAll(@PathVariable("sessionToken") String sessionToken,
                                             @CurrentUser SsoUser ssoUser) {
        TransferPauseAllCommand command = new TransferPauseAllCommand();
        command.setSessionToken(sessionToken);
        command.setUser(ssoUser.getUserId());
        sftpService.transferPauseAll(command);
        return PojoResult.succeed();
    }

    @GetMapping("/transfer/{sessionToken}/resume-all")
    @ApiOperation(value = "恢复所有传输")
    public PojoResult<Void> transferResumeAll(@PathVariable("sessionToken") String sessionToken,
                                              @CurrentUser SsoUser ssoUser) {
        TransferResumeAllCommand command = new TransferResumeAllCommand();
        command.setSessionToken(sessionToken);
        command.setUser(ssoUser.getUserId());
        sftpService.transferResumeAll(command);
        return PojoResult.succeed();
    }

    @GetMapping("/transfer/{sessionToken}/retry-all")
    @ApiOperation(value = "失败重试所有")
    public PojoResult<Void> transferRetryAll(@PathVariable("sessionToken") String sessionToken
            , @CurrentUser SsoUser ssoUser) {
        TransferRetryAllCommand command = new TransferRetryAllCommand();
        command.setSessionToken(sessionToken);
        command.setUser(ssoUser.getUserId());
        sftpService.transferRetryAll(command);
        return PojoResult.succeed();
    }

    @GetMapping("/transfer/{sessionToken}/list")
    @ApiOperation(value = "获取传输列表")
    public ListResult<FileTransferLogDTO> transferList(@PathVariable("sessionToken") String sessionToken
            , @CurrentUser SsoUser ssoUser) {
        SftpSessionTokenDTO tokenInfo = sftpService.getTokenInfo(sessionToken);
        Valid.isTrue(ssoUser.getUserId().equals(tokenInfo.getUserId()));
        List<FileTransferLogDTO> fileTransferLogDTOS = sftpService.transferList(sessionToken);
        return ListResult.succeed(fileTransferLogDTOS);
    }

    @GetMapping("/transfer/{fileToken}/remove")
    @ApiOperation(value = "删除单个传输记录 (包含进行中的)")
    public PojoResult<Void> transferRemove(@PathVariable("fileToken") String fileToken
            , @CurrentUser SsoUser ssoUser) {
        TransferRemoveCommand transferRemoveCommand = new TransferRemoveCommand();
        transferRemoveCommand.setFileToken(fileToken);
        transferRemoveCommand.setUser(ssoUser.getUserId());
        sftpService.transferRemove(transferRemoveCommand);
        return PojoResult.succeed();
    }

    @GetMapping("/transfer/{sessionToken}/clear")
    @ApiOperation(value = "清空全部传输记录 (不包含进行中的)")
    public PojoResult<Void> transferClear(@PathVariable("sessionToken") String sessionToken
            , @CurrentUser SsoUser ssoUser) {
        SftpSessionTokenDTO tokenInfo = sftpService.getTokenInfo(sessionToken);
        Valid.isTrue(ssoUser.getUserId().equals(tokenInfo.getUserId()));
        TransferClearCommand command = new TransferClearCommand();
        command.setUser(ssoUser.getUserId());
        sftpService.transferClear(command);
        return PojoResult.succeed();
    }

    @GetMapping("/transfer/{sessionToken}/{packageType}/package")
    @ApiOperation(value = "传输打包全部已完成未删除的文件")
    public PojoResult<Void> transferPackage(@PathVariable("sessionToken") String sessionToken, @PathVariable("packageType") Integer packageType) {
        SftpPackageType sftpPackageType = Valid.notNull(SftpPackageType.of(packageType));
        sftpService.transferPackage(sessionToken, sftpPackageType.name());
        return PojoResult.succeed();
    }
}
