package com.rany.cake.devops.base.api.command.host;

import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新主机基本信息
 *
 * @author zhongshengwang
 * @description 更新主机基本信息
 * @date 2022/12/30 22:02
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyHostCommand extends BaseCommand {

    private String hostId;
    /**
     * name
     */
    private String name;
    /**
     * hostname
     */
    private String hostname;

    /**
     * port
     */
    private Integer port;

    /**
     * username
     */
    private String username;

    /**
     * pkey
     */
    private String pkey;

    /**
     * 密钥ID
     */
    private Long keyId;
    /**
     * 代理ID
     */
    private Long proxyId;
}
