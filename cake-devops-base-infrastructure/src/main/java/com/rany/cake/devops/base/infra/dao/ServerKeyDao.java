package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.ServerKey;
import com.rany.cake.devops.base.domain.repository.param.ServerKeyQueryParam;
import com.rany.cake.devops.base.infra.po.ServerKeyPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ServerKeyDao {

    /**
     * 账号更新
     *
     * @param serverKey 账号
     * @return 行数
     */
    int save(ServerKey serverKey);


    /**
     * 账号更新
     *
     * @param serverKey 账号
     * @return 行数
     */
    int update(ServerKey serverKey);

    /**
     * 查询单个账号
     *
     * @param keyId 账号ID
     * @return 单个账号
     */
    ServerKeyPO selectByServerKeyId(@Param("keyId") Long keyId);


    List<ServerKeyPO> queryServerKey(ServerKeyQueryParam serverKeyQueryParam);

}
