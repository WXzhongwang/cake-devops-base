package com.rany.cake.devops.base.service.tail;

import lombok.Data;

/**
 * 文件tail 配置
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/20 16:34
 */
@Data
public class TailFileHint {

    /**
     * token
     */
    private String token;

    /**
     * 文件
     */
    private String path;

    /**
     * 机器id
     */
    private String machineId;

    /**
     * 尾行偏移量
     */
    private Integer offset;

    /**
     * 编码格式
     */
    private String charset;

    /**
     * tail 命令
     */
    private String command;

    /**
     * 延迟时间
     */
    private int delay;

}
