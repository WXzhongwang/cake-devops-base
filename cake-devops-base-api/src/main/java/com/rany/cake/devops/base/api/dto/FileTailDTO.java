package com.rany.cake.devops.base.api.dto;

import lombok.Data;

/**
 * 文件 tail 对象
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/10 18:53
 */
@Data
@SuppressWarnings("ALL")
public class FileTailDTO {

    private String filePath;

    private String userId;

    private String machineId;

    private String mode;

    private Integer offset;

    private String charset;

    private String command;

}
