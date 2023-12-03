package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.enums.AppEnvEnum;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.enums.ReleaseStatus;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import lombok.*;

import java.util.Date;

/**
 * 发布聚合
 *
 * @author zhongshengwang
 * @description 发布聚合
 * @date 2023/1/15 15:53
 * @email 18668485565163.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Release extends BaseAggregateRoot implements IAggregate<ReleaseId> {

    private ReleaseId id;
    private AppId appId;
    private String releaseNo;
    private ApprovalId approvalId;
    private Date releaseDate;
    private String releaseBranch;
    private String releaseCommitId;
    private String releaseVersion;
    private Long envId;
    private String releaseStatus;
    private String rollback;
    private ReleaseId rollbackId;

    public Release(ReleaseId id, AppId appId, Long envId, String releaseNo, Date releaseDate) {
        this.id = id;
        this.appId = appId;
        this.envId = envId;
        this.releaseNo = releaseNo;
        this.releaseDate = releaseDate;
    }

    public void init(AppEnv appEnv) {
        this.releaseStatus = ReleaseStatus.APPROVAL.name();
        this.isDeleted = DeleteStatusEnum.NO.getValue();
        this.gmtCreate = new Date();
        this.gmtModified = new Date();
        // 非线上环境不关注审批
        if (appEnv.getEnv() != AppEnvEnum.PROD) {
            this.releaseStatus = ReleaseStatus.READY.name();
        }
    }

    public void approved() {
        this.releaseStatus = ReleaseStatus.READY.name();
    }

    public void deploy() {
        this.releaseStatus = ReleaseStatus.PENDING.name();
        this.gmtModified = new Date();
    }
}
