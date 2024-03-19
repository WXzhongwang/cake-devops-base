package com.rany.cake.devops.base.api.command.host;


import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 添加主机
 *
 * @author zhongshengwang
 * @description 添加主机
 * @date 2022/12/27 20:40
 * @email 18668485565163.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateHostCommand extends BaseCommand {

    /**
     * name
     */
    private String name;
    /**
     * hostname
     */
    private String hostName;

    /**
     * 服务地址
     */
    private String serverAddr;

    /**
     * port
     */
    private Integer port;

    /**
     * username
     */
    private String username;

    /**
     * pwd
     */
    private String pwd;


    /**
     * 主机组
     */
    private List<String> hostGroupIds;

    /**
     * 密钥ID
     */
    private Long keyId;
    /**
     * 代理ID
     */
    private Long proxyId;
}
