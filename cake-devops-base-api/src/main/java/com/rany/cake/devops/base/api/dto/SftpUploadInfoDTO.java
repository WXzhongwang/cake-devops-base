package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * sftp 文件上传对象
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/5/13 17:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SftpUploadInfoDTO extends DTO {

    /**
     * 远程路径
     */
    private String remotePath;

    /**
     * 机器id
     */
    private Long machineId;

    /**
     * 机器id
     */
    private List<Long> machineIdList;

    /**
     * 用户id
     */
    private Long userId;

}
