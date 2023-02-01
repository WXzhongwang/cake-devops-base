package com.rany.cake.devops.base.service.plugins;

import com.rany.cake.devops.base.service.context.Plugin;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础插件
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/1 19:48
 * @email 18668485565163.com
 */
@Slf4j
public abstract class BasePlugin implements Plugin {
    /**
     * 运行环境变量
     */
    protected Map<String, Object> args = new HashMap<>();
}
