package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.api.enums.AppEnvEnum;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import lombok.Getter;

import java.util.List;

/**
 * 应用部署环境
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 15:49
 * @email 18668485565163.com
 */

@Getter
public class AppEnv extends BaseEntity<Long> {

    private AppId appId;
    private ClusterId clusterId;
    private AppEnvEnum envEnum;
    private List<String> domain;
    private ResourceStrategy resourceStrategy;

    /**
     * 是否自动扩容
     */
    private Boolean autoScaling;
    /**
     * 是否开启部署 需要审批
     */
    private Boolean needApproval;

}