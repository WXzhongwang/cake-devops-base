package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import com.rany.cake.devops.base.domain.repository.ReleaseRepository;
import com.rany.cake.devops.base.infra.convertor.ReleaseDataConvertor;
import com.rany.cake.devops.base.infra.mapper.ReleasePOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
@AllArgsConstructor
public class ReleaseRepositoryImpl implements ReleaseRepository {

    private final ReleasePOMapper releasePOMapper;
    private final ReleaseDataConvertor releaseDataConvertor;

    @Override
    public Release find(@NotNull ReleaseId releaseId) {
        return null;
    }

    @Override
    public void remove(@NotNull Release release) {

    }

    @Override
    public void save(@NotNull Release release) {

    }
}
