package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.HostTerminalConfig;
import com.rany.cake.devops.base.infra.po.HostTerminalConfigPO;
import org.apache.ibatis.annotations.Param;

public interface HostTerminalConfigDao {


    /**
     * 新增
     *
     * @param config 新增
     * @return 行数
     */
    int save(HostTerminalConfig config);

    HostTerminalConfigPO findByHostId(@Param("hostId") String hostId);


    /**
     * 更新
     *
     * @param config 更新
     * @return 行数
     */
    int update(HostTerminalConfig config);
}