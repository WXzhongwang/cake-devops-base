package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;

/**
 * 传输列表响应
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/27 23:30
 */
@Data
@SuppressWarnings("ALL")
public class FileTransferLogDTO extends DTO {

    private Long id;

    private Long machineId;

    private String fileToken;


    /**
     * 传输类型 10上传 20下载 30传输
     */
    private Integer type;

    /**
     * 远程文件
     */
    private String remoteFile;

    /**
     * 当前大小
     */
    private String current;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 当前进度
     */
    private Double progress;

    /**
     * 传输状态 10未开始 20进行中 30已暂停 40已完成 50已取消 60传输异常
     */
    private Integer status;

}
