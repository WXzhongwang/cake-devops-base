package com.rany.cake.devops.base.dao;

import com.rany.cake.devops.base.po.AppMemberPO;
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

}
