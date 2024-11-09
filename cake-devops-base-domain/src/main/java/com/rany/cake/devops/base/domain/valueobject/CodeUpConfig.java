package com.rany.cake.devops.base.domain.valueobject;

import lombok.Data;

/**
 * 云效代码仓库配置参数
 */
@Data
public class CodeUpConfig {

    /**
     * openapi-rdc.aliyuncs.com
     */
    private String domain;
    /**
     * 企业ID
     */
    private String organizationId;
    /**
     * x-yunxiao-token
     */
    private String headerToken;
}
