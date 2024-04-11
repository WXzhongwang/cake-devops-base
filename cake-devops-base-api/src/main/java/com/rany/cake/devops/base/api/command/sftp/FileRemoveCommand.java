package com.rany.cake.devops.base.api.command.sftp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * sftp 删除文件命令
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 23:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileRemoveCommand extends FileBaseCommand {

    /**
     * 绝对路径
     */
    private List<String> paths;

}
