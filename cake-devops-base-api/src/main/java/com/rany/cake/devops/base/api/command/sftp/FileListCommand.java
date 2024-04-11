package com.rany.cake.devops.base.api.command.sftp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 文件列表请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 19:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileListCommand extends FileBaseCommand {

    /**
     * 绝对路径
     */
    private String path;

}
