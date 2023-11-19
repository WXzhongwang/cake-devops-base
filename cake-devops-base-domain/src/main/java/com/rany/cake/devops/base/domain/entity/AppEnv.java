package com.rany.cake.devops.base.domain.entity;

import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.domain.enums.AppEnvEnum;
import com.rany.cake.devops.base.domain.enums.CommonStatusEnum;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * 应用部署环境
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 15:49
 * @email 18668485565163.com
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class AppEnv extends BaseEntity<Long> {

    private AppId appId;
    private ClusterId clusterId;
    private String envName;
    private AppEnvEnum env;
    private List<String> domains;

    private Map<String, String> configMap;
    /**
     * 环境资源策略
     */
    private ResourceStrategy resourceStrategy;

    /**
     * 是否自动扩容
     */
    private Boolean autoScaling;
    /**
     * 是否开启部署 需要审批
     */
    private Boolean needApproval;
    /**
     * 状态
     */
    private String status;

    public AppEnv(AppId appId, ClusterId clusterId, String envName, AppEnvEnum env) {
        this.appId = appId;
        this.clusterId = clusterId;
        this.envName = envName;
        this.env = env;
        this.status = CommonStatusEnum.ENABLE.getCode();
    }
}
