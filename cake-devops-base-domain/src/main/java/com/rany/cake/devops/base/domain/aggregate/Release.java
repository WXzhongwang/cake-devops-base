package com.rany.cake.devops.base.domain.aggregate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
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
    private String envName;
    private String releaseStatus;
    private String rollback;
    private ReleaseId rollbackId;
}
