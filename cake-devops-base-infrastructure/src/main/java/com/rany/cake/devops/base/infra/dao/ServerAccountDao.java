package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.ServerAccount;
import com.rany.cake.devops.base.domain.repository.param.ServerAccountQueryParam;
import com.rany.cake.devops.base.infra.po.ServerAccountPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServerAccountDao {

    /**
     * 账号更新
     *
     * @param serverAccount 账号
     * @return 行数
     */
    int save(ServerAccount serverAccount);


    /**
     * 账号更新
     *
     * @param serverAccount 账号
     * @return 行数
     */
    int update(ServerAccount serverAccount);

    /**
     * 查询单个账号
     *
     * @param serverAccountId 账号ID
     * @return 单个账号
     */
    ServerAccountPO selectByServerAccountId(@Param("serverAccountId") String serverAccountId);


    List<ServerAccountPO> queryServerAccount(ServerAccountQueryParam serverAccountQueryParam);

}
