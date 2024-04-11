package com.rany.cake.devops.base.api.command.sftp;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 打开请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 19:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileOpenCommand extends BaseCommand {

    /**
     * 机器id
     */
    private Long machineId;

}
