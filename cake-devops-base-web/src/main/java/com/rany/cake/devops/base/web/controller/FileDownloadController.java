package com.rany.cake.devops.base.web.controller;

import com.rany.cake.devops.base.api.dto.FileTransferLogDTO;
import com.rany.cake.devops.base.api.service.SftpService;
import com.rany.cake.devops.base.service.handler.sftp.DirectDownloader;
import com.rany.cake.devops.base.service.utils.Servlets;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.io.Streams;
import com.rany.cake.toolkit.lang.utils.Exceptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/devops/download")
public class FileDownloadController {

    @Resource
    private SftpService sftpService;

    @GetMapping("/sftp")
    public void downloadSftpFile(HttpServletResponse servletResponse,
                                 @RequestParam("logId") Long logId) throws IOException {
        InputStream inputStream = null;
        DirectDownloader downloader = null;
        try {
            // sftp 下载文件
            FileTransferLogDTO transferLog = sftpService.getDownloadFilePath(logId);
            if (transferLog == null) {
                throw Exceptions.notFound();
            }
            String path = transferLog.getLocalFile();
            String name = Files1.getFileName(transferLog.getRemoteFile());
            // 远程文件
            downloader = new DirectDownloader(transferLog.getHostId());
            inputStream = downloader.open().getFile(path);

            // 返回
            Servlets.transfer(servletResponse, inputStream, name);
        } finally {
            Streams.close(inputStream);
            Streams.close(downloader);
        }
    }
}
