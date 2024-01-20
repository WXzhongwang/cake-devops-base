package com.rany.cake.devops.base.service.plugins;

import com.rany.cake.devops.base.service.context.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础插件
 *
 * @author zhongshengwang
 * @description 基础插件
 * @date 2023/2/1 19:48
 * @email 18668485565163.com
 */
public abstract class BasePlugin implements Plugin {

    protected static final Logger log = LoggerFactory.getLogger("RabbitMq");
}
