package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.repository.param.HostMonitorPageQueryParam;
import com.rany.cake.devops.base.domain.repository.param.HostPageQueryParam;
import com.rany.cake.devops.base.infra.po.HostPO;
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
public interface HostDao {


    /**
     * 新增
     *
     * @param host 命名空间
     * @return
     */
    int save(Host host);


    /**
     * 更新
     *
     * @param host 命名空间
     * @return
     */
    int update(Host host);


    List<HostPO> selectByPrimaryKeyList(@Param("hostIds") List<String> hostIds);


    HostPO selectByHostId(@Param("hostId") String hostId);


    List<HostPO> queryHost(HostPageQueryParam hostPageQueryParam);

    List<HostPO> queryMonitorHost(HostMonitorPageQueryParam hostPageQueryParam);
}
