package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.entity.HostAlarmConfig;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostAlarmConfigRepository;
import com.rany.cake.devops.base.infra.convertor.HostAlarmConfigDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostAlarmConfigDao;
import com.rany.cake.devops.base.infra.mapper.HostAlarmConfigPOMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class HostAlarmConfigRepositoryImpl implements HostAlarmConfigRepository {
    private final HostAlarmConfigPOMapper hostAlarmConfigPOMapper;
    private final HostAlarmConfigDataConvertor hostAlarmConfigDataConvertor;
    private final HostAlarmConfigDao hostAlarmConfigDao;

    @Override
    public List<HostAlarmConfig> find(HostId hostId) {
        return null;
    }

    @Override
    public void save(HostAlarmConfig env) {

    }

    @Override
    public void update(HostAlarmConfig env) {

    }
}
