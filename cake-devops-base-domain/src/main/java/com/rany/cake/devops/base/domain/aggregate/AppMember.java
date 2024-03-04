package com.rany.cake.devops.base.domain.aggregate;

import cn.hutool.core.date.DateUtil;
import com.cake.framework.common.base.BaseAggregateRoot;
import com.cake.framework.common.base.IAggregate;
import com.google.common.collect.Lists;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.MemberId;
import com.rany.cake.devops.base.util.enums.CommonStatusEnum;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    private MemberId memberId;

    private AppId appId;

    /**
     * uic accountId
     */
    private String accountId;

    /**
     * 角色列表
     */
    private String roles;
    /**
     * 状态
     */
    private String status;

    public AppMember(MemberId memberId,
                     AppId appId,
                     String accountId) {
        this.memberId = memberId;
        this.appId = appId;
        this.accountId = accountId;
        this.roles = StringUtils.EMPTY;
    }


    /**
     * 授权
     *
     * @param roles
     */
    public void authorize(String roles) {
        if (StringUtils.isEmpty(roles)) {
            return;
        }
        List<String> finalRoles = StringUtils.isNotEmpty(this.roles) ? Lists.newArrayList(this.roles.split(",")) : new ArrayList<>();
        String[] newRoles = roles.split(",");
        for (String newRole : newRoles) {
            if (!this.roles.contains(newRole)) {
                finalRoles.add(newRole);
            }
        }
        this.roles = String.join(",", finalRoles);
        this.gmtModified = DateUtil.date();
    }


    public void update(List<String> roles, String status) {
        this.roles = String.join(",", roles);
        this.gmtModified = DateUtil.date();
        this.status = status;
    }

    public void delete() {
        this.gmtModified = DateUtil.date();
        this.status = CommonStatusEnum.DISABLED.getValue();
        this.isDeleted = DeleteStatusEnum.YES.getValue();
    }

    @Override
    public MemberId getBizID() {
        return memberId;
    }
}
