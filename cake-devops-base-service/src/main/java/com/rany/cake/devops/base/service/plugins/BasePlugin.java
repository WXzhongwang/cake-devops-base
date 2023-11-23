package com.rany.cake.devops.base.service.plugins;

import com.rany.cake.devops.base.service.context.Plugin;
import lombok.extern.slf4j.Slf4j;

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
//    /**
//     * 运行环境变量
//     */
//    protected ThreadLocal<Map<String, Object>> args = ThreadLocal.withInitial(HashMap::new);
//
//    public void putArg(String key, Object value) {
//        Map<String, Object> argMap = this.args.get();
//        argMap.put(key, value);
//    }
}
