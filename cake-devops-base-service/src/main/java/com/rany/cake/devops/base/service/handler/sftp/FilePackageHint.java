package com.rany.cake.devops.base.service.handler.sftp;

import com.rany.cake.devops.base.domain.entity.FileTransferLog;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 文件打包参数
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/11/17 17:53
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class FilePackageHint extends FileTransferHint {

    /**
     * 压缩文件列表
     */
    private List<FileTransferLog> fileList;

    public FilePackageHint(FileTransferLog record, List<FileTransferLog> fileList) {
        super(record);
        this.fileList = fileList;
    }

}
