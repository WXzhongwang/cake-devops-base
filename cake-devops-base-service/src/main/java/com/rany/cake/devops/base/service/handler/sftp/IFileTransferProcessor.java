package com.rany.cake.devops.base.service.handler.sftp;

import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import com.rany.cake.devops.base.service.handler.sftp.impl.DownloadFileProcessor;
import com.rany.cake.devops.base.service.handler.sftp.impl.PackageFileProcessor;
import com.rany.cake.devops.base.service.handler.sftp.impl.UploadFileProcessor;
import com.rany.cake.devops.base.util.sftp.SftpTransferType;
import com.rany.cake.toolkit.lang.Executable;
import com.rany.cake.toolkit.lang.Stoppable;
import com.rany.cake.toolkit.lang.function.select.Branches;
import com.rany.cake.toolkit.lang.function.select.Selector;

/**
 * sftp 传输接口
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/29 19:08
 */
public interface IFileTransferProcessor extends Runnable, Stoppable, Executable {

    /**
     * 获取执行processor
     *
     * @param hint hint
     * @return IFileTransferProcessor
     */
    static IFileTransferProcessor of(FileTransferHint hint) {
        FileTransferLog record = hint.getRecord();
        return Selector.<SftpTransferType, IFileTransferProcessor>of(hint.getType())
                .test(Branches.eq(SftpTransferType.UPLOAD)
                        .then(() -> new UploadFileProcessor(record)))
                .test(Branches.eq(SftpTransferType.DOWNLOAD)
                        .then(() -> new DownloadFileProcessor(record)))
                .test(Branches.eq(SftpTransferType.PACKAGE)
                        .then(() -> new PackageFileProcessor(record, ((FilePackageHint) hint).getFileList())))
                .get();
    }

}
