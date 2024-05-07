package com.rany.cake.devops.base.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 终端监视信息
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/7/29 10:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("ALL")
public class TerminalWatcherDTO {

    private String userId;

    private String token;

    /**
     * @see com.orion.ops.constant.Const#ENABLE
     */
    private Integer readonly;

}
