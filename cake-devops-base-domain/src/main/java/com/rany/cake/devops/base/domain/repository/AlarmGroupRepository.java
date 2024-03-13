package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.AlarmGroup;
import com.rany.cake.devops.base.domain.repository.param.AlarmGroupQueryParam;

import java.util.List;

/**
 * 报警组
 */
public interface AlarmGroupRepository {
    AlarmGroup find(Long groupId);

    List<AlarmGroup> findByGroupIds(List<Long> groupIds);

    void remove(AlarmGroup group);

    void save(AlarmGroup group);

    void update(AlarmGroup group);

    Page<AlarmGroup> page(AlarmGroupQueryParam queryParam);
}
