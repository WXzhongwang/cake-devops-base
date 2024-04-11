package com.rany.cake.devops.base.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 文件传输进度
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/2 11:32
 */
@Data
@AllArgsConstructor
public class FileTransferNotifyProgressDTO {

    /**
     * 速度
     */
    private String rate;

    /**
     * 当前位置
     */
    private String current;

    /**
     * 进度
     */
    private String progress;

}
