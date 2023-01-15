package com.rany.cake.devops.base.domain.aggegrate;

import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.MemberId;
import lombok.*;

/**
 * 应用人员表
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 15:53
 * @email 18668485565163.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppMember extends BaseAggregateRoot implements IAggregate<MemberId> {


    private MemberId id;

    private AppId appId;

    /**
     * uic accountId
     */
    private Long accountId;

    /**
     * 员工状态
     */
    private String status;

    public AppMember(MemberId memberId,
                     AppId appId,
                     Long accountId) {
        this.id = memberId;
        this.appId = appId;
        this.accountId = accountId;
    }
}
