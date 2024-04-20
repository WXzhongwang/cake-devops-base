package com.rany.cake.devops.base.service.handler.sftp;

import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import com.rany.cake.devops.base.util.sftp.SftpTransferType;
import lombok.Data;

import java.util.List;

/**
 * 文件传输配置
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/11/17 17:48
 */
@Data
public class FileTransferHint {

    /**
     * record
     */
    private FileTransferLog record;

    /**
     * 类型
     */
    private SftpTransferType type;

    public FileTransferHint(FileTransferLog record) {
        this.record = record;
        this.type = SftpTransferType.of(record.getTransferType());
    }

    /**
     * 获取上传配置
     *
     * @param record record
     * @return 上传配置
     */
    public static FileTransferHint transfer(FileTransferLog record) {
        return new FileTransferHint(record);
    }

    /**
     * 获取打包配置
     *
     * @param record   record
     * @param fileList fileList
     * @return 上传配置
     */
    public static FilePackageHint packaged(FileTransferLog record, List<FileTransferLog> fileList) {
        return new FilePackageHint(record, fileList);
    }

}
