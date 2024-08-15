package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.pk.MemberId;
import com.rany.cake.devops.base.domain.repository.param.AppMemberPageQueryParam;

import java.util.List;

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
    AppMember findByAccountId(String accountId);

    AppMember findById(String accountId);

    /**
     * 查询应用成员
     *
     * @param param 应用ID
     * @return 成员信息
     */
    List<AppMember> findByAppId(AppMemberPageQueryParam param);


    Page<AppMember> pageByAppId(AppMemberPageQueryParam appMemberPageQueryParam);

    /**
     * 保存更新
     *
     * @param appMember 成员
     */
    void saveUpdate(AppMember appMember);

    void update(AppMember appMember);

}
