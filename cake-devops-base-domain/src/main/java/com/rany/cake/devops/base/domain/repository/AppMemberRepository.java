package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.pk.MemberId;

/**
 * 成员
 *
 * @author zhongshengwang
 * @description 成员
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface AppMemberRepository extends Repository<AppMember, MemberId> {


    /**
     * 通过accountId找到member
     *
     * @param accountId
     * @return
     */
    AppMember findByAccountId(Long accountId);


    /**
     * 保存更新
     *
     * @param appMember
     * @return
     */
    int saveUpdate(AppMember appMember);

}
