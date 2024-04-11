package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * sftp 文件列表响应
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/23 18:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FileListDTO extends DTO {

    /**
     * 当前路径
     */
    private String path;

    /**
     * 文件
     */
    private List<FileDetailDTO> files;

}
