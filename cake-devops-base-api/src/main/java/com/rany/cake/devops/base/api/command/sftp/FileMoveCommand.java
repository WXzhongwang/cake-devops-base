package com.rany.cake.devops.base.api.command.sftp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 移动文件请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 20:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileMoveCommand extends FileBaseCommand {

    /**
     * 绝对路径
     */
    private String source;

    /**
     * 路径 绝对路径/相对路径 可以包含../
     */
    private String target;

}
