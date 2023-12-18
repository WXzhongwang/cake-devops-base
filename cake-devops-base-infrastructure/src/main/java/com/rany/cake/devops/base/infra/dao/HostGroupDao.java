package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.HostGroup;
import com.rany.cake.devops.base.infra.po.ClusterPO;
import com.rany.cake.devops.base.infra.po.HostGroupPO;
import org.apache.ibatis.annotations.Param;

/**
 * 主机组
 *
 * @author zhongshengwang
 * @description ˙主机组
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface HostGroupDao {


    /**
     * 新增
     *
     * @param hostGroup 组
     * @return
     */
    int save(HostGroup hostGroup);


    /**
     * 更新
     *
     * @param hostGroup 组
     * @return
     */
    int update(HostGroup hostGroup);

    /**
     * 获取打包机器组
     *
     * @return
     */
    HostGroupPO getPackagingGroup();

    HostGroupPO selectByHostGroupId(@Param("hostGroupId") String hostGroupId);

}
