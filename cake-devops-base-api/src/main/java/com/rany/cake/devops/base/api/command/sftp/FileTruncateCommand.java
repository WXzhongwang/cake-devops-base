package com.rany.cake.devops.base.api.command.sftp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 截断文件请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/24 19:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileTruncateCommand extends FileBaseCommand {

    /**
     * 绝对路径
     */
    private String path;

}
