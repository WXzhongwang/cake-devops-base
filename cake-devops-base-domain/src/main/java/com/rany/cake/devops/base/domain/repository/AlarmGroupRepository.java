package com.rany.cake.devops.base.domain.repository;

import com.rany.cake.devops.base.domain.entity.AlarmGroup;

public interface AlarmGroupRepository {
    AlarmGroup find(Long id);

    void remove(AlarmGroup group);

    void save(AlarmGroup group);
}
