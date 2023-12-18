package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.infra.po.AppEnvPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppEnvDao {


    /**
     * 新增
     *
     * @param appEnv 小组主机关系
     * @return
     */
    int save(AppEnv appEnv);


    /**
     * 更新
     *
     * @param appEnv 小组主机关系
     * @return
     */
    int update(AppEnv appEnv);

    /**
     * 根据appId查环境
     *
     * @param appId
     * @return
     */
    List<AppEnvPO> selectByAppId(@Param("appId") String appId);
    AppEnvPO selectByEnvId(@Param("envId") String envId);
}
