package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.ddd.repository.Repository;
import com.rany.cake.devops.base.domain.aggregate.HostGroup;
import com.rany.cake.devops.base.domain.pk.HostGroupId;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 主机组
 *
 * @author zhongshengwang
 * @description 主机组
 * @date 2023/1/28 21:01
 * @email 18668485565163.com
 */
public interface HostGroupRepository extends Repository<HostGroup, HostGroupId> {

    List<HostGroup> findByGroupIds(@NotNull List<String> groupIds);

    int update(HostGroup hostGroup);

    HostGroup getPackagingGroup();

    List<HostGroup> listAll();
}

