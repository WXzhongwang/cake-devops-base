package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.ServerProxy;
import com.rany.cake.devops.base.domain.repository.param.ServerProxyQueryParam;
import com.rany.cake.devops.base.infra.po.ServerProxyPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServerProxyDao {

    /**
     * 账号更新
     *
     * @param serverKey 账号
     * @return 行数
     */
    int save(ServerProxy serverKey);


    /**
     * 账号更新
     *
     * @param serverKey 账号
     * @return 行数
     */
    int update(ServerProxy serverKey);

    /**
     * 查询单个账号
     *
     * @param proxyId 账号ID
     * @return 单个账号
     */
    ServerProxyPO selectByServerProxyId(@Param("proxyId") String proxyId);


    List<ServerProxyPO> queryServerProxy(ServerProxyQueryParam serverProxyQueryParam);

}
