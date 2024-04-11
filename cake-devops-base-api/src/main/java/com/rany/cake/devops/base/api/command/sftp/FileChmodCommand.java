package com.rany.cake.devops.base.api.command.sftp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 修改文件权限请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 20:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileChmodCommand extends FileBaseCommand {

    /**
     * 绝对路径
     */
    private String path;

    /**
     * 权限 10进制表现的8进制权限
     */
    private Integer permission;

}
