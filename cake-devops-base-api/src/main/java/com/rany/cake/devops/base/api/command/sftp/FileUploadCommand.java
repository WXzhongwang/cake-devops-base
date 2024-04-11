package com.rany.cake.devops.base.api.command.sftp;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 上传文件请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/28 18:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileUploadCommand extends BaseCommand {

    /**
     * sessionToken
     */
    private String sessionToken;

    /**
     * 机器id
     */
    private Long machineId;

    /**
     * 文件token
     */
    private String fileToken;

    /**
     * 本地文件路径
     */
    private String localPath;

    /**
     * 远程文件路径
     */
    private String remotePath;

    /**
     * 文件大小
     */
    private Long size;

}
