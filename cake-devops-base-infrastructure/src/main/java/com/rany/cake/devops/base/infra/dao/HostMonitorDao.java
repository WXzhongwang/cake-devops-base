package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.repository.param.HostMonitorPageQueryParam;
import com.rany.cake.devops.base.infra.po.HostMonitorPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 主机
 *
 * @author zhongshengwang
 * @description ˙主机
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface HostMonitorDao {


    /**
     * 新增监控配置
     *
     * @param hostMonitor 监控配置
     * @return 更新行数
     */
    int save(HostMonitor hostMonitor);


    /**
     * 更新
     *
     * @param hostMonitor 监控配置
     * @return 更新行数
     */
    int update(HostMonitor hostMonitor);


    HostMonitorPO selectByHostId(@Param("hostId") String hostId);

    /**
     * 查询监控列表
     *
     * @param param 监控查询
     * @return 列表
     */
    List<HostMonitorPO> queryByParam(HostMonitorPageQueryParam param);

    int clearStartingStatus();
}
