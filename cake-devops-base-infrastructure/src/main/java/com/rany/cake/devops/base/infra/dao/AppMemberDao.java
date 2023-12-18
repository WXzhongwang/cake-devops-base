package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.infra.po.AppMemberPO;
import org.apache.ibatis.annotations.Param;

/**
 * 成员
 *
 * @author zhongshengwang
 * @description 成员
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface AppMemberDao {


    /**
     * 新增成员
     *
     * @param accountId
     * @return
     */
    AppMemberPO selectByAccountId(@Param("accountId") String accountId);

    /**
     * memberIdc查询
     * @param memberId
     * @return
     */
    AppMemberPO selectByMemberId(@Param("memberId") String memberId);

    /**
     * 保存更新
     *
     * @param appMember
     * @return
     */
    int saveUpdate(AppMember appMember);

}
