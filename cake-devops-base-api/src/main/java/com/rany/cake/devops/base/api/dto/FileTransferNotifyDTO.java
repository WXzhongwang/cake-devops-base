package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;

/**
 * sftp 文件传输通知
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/27 1:02
 */
@Data
@SuppressWarnings("ALL")
public class FileTransferNotifyDTO extends DTO {

    /**
     * @see SftpNotifyType
     */
    private Integer type;

    /**
     * fileToken
     */
    private String fileToken;

    /**
     * body
     */
    private Object body;

}
