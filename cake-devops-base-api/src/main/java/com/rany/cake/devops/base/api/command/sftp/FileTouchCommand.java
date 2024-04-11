package com.rany.cake.devops.base.api.command.sftp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 创建文件请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 20:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileTouchCommand extends FileBaseCommand {

    /**
     * 绝对路径
     */
    private String path;

}
