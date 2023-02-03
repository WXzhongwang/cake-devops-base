package com.rany.cake.devops.base.domain.dao;

import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.infra.po.AppMemberPO;
import org.apache.ibatis.annotations.Param;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface AppMemberDao {


    /**
     * 根据账号获取member
     *
     * @param accountId
     * @return
     */
    AppMemberPO selectByAccountId(@Param("id") Long accountId);

    /**
     * 保存更新
     *
     * @param appMember
     * @return
     */
    int saveUpdate(AppMember appMember);

}
