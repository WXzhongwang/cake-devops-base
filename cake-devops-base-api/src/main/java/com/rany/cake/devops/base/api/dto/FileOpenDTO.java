package com.rany.cake.devops.base.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * sftp 打开连接响应
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 18:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileOpenDTO extends FileListDTO {

    /**
     * 根目录
     */
    private String home;

    /**
     * sessionToken
     */
    private String sessionToken;

    /**
     * 编码格式
     */
    private String charset;

}
