package com.rany.cake.devops.base.api.command.sftp;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * sftp 上传文件请求
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/28 18:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BatchFileUploadCommand extends BaseCommand {

    /**
     * sessionToken
     */
    private String sessionToken;

    /**
     * hostId
     */
    private String hostId;


    private List<FileUploadCommand> files;

}
