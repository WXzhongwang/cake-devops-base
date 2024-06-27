package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.HostTerminalLog;
import com.rany.cake.devops.base.domain.repository.param.TerminalLogPageQueryParam;
import com.rany.cake.devops.base.infra.po.HostTerminalLogPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 删除日志
     *
     * @param ids ids
     * @return 行数
     */
    int deleteByIds(@Param("ids") List<Long> ids);

    int deleteByHostIds(@Param("hostIds") List<String> hostIds);


    List<HostTerminalLogPO> pageQueryTerminalLog(TerminalLogPageQueryParam param);
}