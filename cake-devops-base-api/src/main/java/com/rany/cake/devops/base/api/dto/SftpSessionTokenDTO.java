package com.rany.cake.devops.base.api.dto;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * sftp 会话信息
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/5/16 9:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SftpSessionTokenDTO extends DTO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 机器id
     */
    private String hostId;

    /**
     * 机器id (批量上传用)
     */
    private List<String> hostIdList;

}
