package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.pk.HostId;

/**
 * 主机
 *
 * @author zhongshengwang
 * @description 主机
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface HostRepository extends Repository<Host, HostId> {
}
