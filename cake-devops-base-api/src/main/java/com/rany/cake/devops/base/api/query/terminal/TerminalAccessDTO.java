package com.rany.cake.devops.base.api.query.terminal;

import com.rany.cake.devops.base.api.common.base.DTO;
import lombok.Data;

/**
 * 访问终端响应
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/4/1 16:40
 */
@Data
@SuppressWarnings("ALL")
public class TerminalAccessDTO extends DTO {
    private Long id;
    private String host;
    private Integer port;
    private String hostName;
    private String hostId;
    private String username;
    private String terminalType;
    private String backgroundColor;
    private String fontColor;
    private Integer fontSize;
    private String fontFamily;
    private String accessToken;
    private Integer enableWebLink;

}
