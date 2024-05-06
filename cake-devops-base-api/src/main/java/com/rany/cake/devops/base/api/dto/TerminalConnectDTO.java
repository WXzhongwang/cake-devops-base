package com.rany.cake.devops.base.api.dto;

import lombok.Data;

/**
 * 终端连接参数
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/4/17 20:12
 */
@Data
public class TerminalConnectDTO {

    private Integer cols;

    private Integer rows;

    private String loginToken;

}
