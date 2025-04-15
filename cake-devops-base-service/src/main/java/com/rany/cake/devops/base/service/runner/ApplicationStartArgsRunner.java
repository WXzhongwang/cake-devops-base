package com.rany.cake.devops.base.service.runner;

import com.rany.cake.devops.base.api.service.SystemService;
import com.rany.cake.devops.base.util.enums.EnableType;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 应用启动参数执行
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/2/20 1:23
 */
@Component
@Order(4000)
@Slf4j
public class ApplicationStartArgsRunner implements CommandLineRunner {

    /**
     * 关闭ip过滤器
     */
    private static final String DISABLE_IP_FILTER = "--disable-ip-filter";

    @Resource
    private SystemService systemService;


    @Override
    public void run(String... args) {
        log.info("应用启动参数: {}", Arrays.toString(args));
        for (String arg : args) {
            switch (arg) {
                case DISABLE_IP_FILTER:
                    // 关闭ip过滤器
                    this.disableIpFilter();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 关闭ip过滤器
     */
    private void disableIpFilter() {
        systemService.updateSystemOption(SystemEnvAttr.ENABLE_IP_FILTER, EnableType.DISABLED.getLabel());
        log.info("启动参数-IP过滤器已关闭");
    }
}
