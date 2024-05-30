package com.rany.cake.devops.base.service.adapter;

import com.rany.cake.toolkit.lang.process.Attempt;
import com.rany.cake.toolkit.lang.reflect.PackageScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 类型转换注册器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/10 17:23
 */
@Component
@Slf4j
public class MappingConversionProvider implements InitializingBean {

    private final String CONVERSION_PACKAGE = this.getClass().getPackage().getName() + ".conversions.*";

    @Override
    public void afterPropertiesSet() throws Exception {
        new PackageScanner(CONVERSION_PACKAGE)
                .with(MappingConversionProvider.class)
                .scan()
                .getClasses()
                .forEach(Attempt.rethrows(s -> {
                    log.info("register type conversion {}", s.getName());
                    Class.forName(s.getName());
                }));
    }

}
