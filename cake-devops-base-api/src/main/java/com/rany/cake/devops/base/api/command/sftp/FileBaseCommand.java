package com.rany.cake.devops.base.api.command.sftp;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 19:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileBaseCommand extends BaseCommand {

    /**
     * sessionToken
     */
    private String sessionToken;

    /**
     * 是否查询隐藏文件
     */
    private Boolean all;

}
