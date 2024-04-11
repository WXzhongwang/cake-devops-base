package com.rany.cake.devops.base.api.command.sftp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * sftp 检查文件是否存在
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/10/25 9:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FilePresentCheckCommand extends FileBaseCommand {

    /**
     * 当前路径
     */
    private String path;

    /**
     * 文件名称
     */
    private List<String> names;

    /**
     * 文件大小
     */
    private Long size;

}
