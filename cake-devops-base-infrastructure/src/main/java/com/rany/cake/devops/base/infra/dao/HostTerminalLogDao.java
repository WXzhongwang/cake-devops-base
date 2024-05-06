package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.HostTerminalLog;
import com.rany.cake.devops.base.infra.po.HostTerminalLogPO;
import org.apache.ibatis.annotations.Param;

public interface HostTerminalLogDao {
    /**
     * 新增
     *
     * @param log 新增
     * @return 行数
     */
    int save(HostTerminalLog log);

    HostTerminalLogPO getAccessLog(@Param("accessToken") String token);

    /**
     * 更新
     *
     * @param log 更新
     * @return 行数
     */
    int update(HostTerminalLog log);
}