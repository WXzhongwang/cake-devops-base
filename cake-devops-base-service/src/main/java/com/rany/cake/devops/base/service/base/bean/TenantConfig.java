package com.rany.cake.devops.base.service.base.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/2 20:28
 * @email 18668485565163.com
 */
@Data
@Configuration
public class TenantConfig {

    @Value("${uic.tenantId}")
    private Long tenantId;
}
