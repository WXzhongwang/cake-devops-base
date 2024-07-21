package com.rany.cake.devops.base.domain.entity;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseEntity;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import com.rany.cake.devops.base.util.enums.AppEnvEnum;
import com.rany.cake.devops.base.util.enums.CommonStatusEnum;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.util.enums.EnvDeployStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
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
public class AppEnv extends BaseEntity<String> {

    private String envId;
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

    /**
     * 发布状态
     */
    private String deployStatus;

    /**
     * 发布进度
     */
    private String progress;

    public AppEnv() {
    }

    public AppEnv(String envId, AppId appId, ClusterId clusterId, String envName, AppEnvEnum env) {
        this.envId = envId;
        this.appId = appId;
        this.clusterId = clusterId;
        this.envName = envName;
        this.env = env;
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.deployStatus = EnvDeployStatusEnum.NORMAL.getValue();
    }

    public void sava(String user) {
        this.gmtCreate = DateUtil.date();
        this.gmtModified = DateUtil.date();
        this.status = CommonStatusEnum.ENABLE.getValue();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.creator = user;
        this.modifier = user;
    }


    public void deploy(String progress) {
        this.deployStatus = EnvDeployStatusEnum.DEPLOYING.getValue();
        this.gmtModified = new Date();
        this.progress = progress;
    }

    public void recover(String progress) {
        this.deployStatus = EnvDeployStatusEnum.NORMAL.getValue();
        this.gmtModified = new Date();
        this.progress = progress;
    }
}
