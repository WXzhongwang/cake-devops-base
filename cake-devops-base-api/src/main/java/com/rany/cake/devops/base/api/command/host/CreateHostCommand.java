package com.rany.cake.devops.base.api.command.host;


import com.rany.cake.devops.base.api.common.base.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String hostname;
    
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
     * pkey
     */
    private String pkey;

}
