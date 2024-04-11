package com.rany.cake.devops.base.api.command.sftp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 修改所有者请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 20:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileChownCommand extends FileBaseCommand {

    /**
     * 绝对路径
     */
    private String path;

    /**
     * 用户id
     */
    private Integer uid;

}
