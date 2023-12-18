package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import com.rany.cake.devops.base.domain.repository.ReleaseRepository;
import com.rany.cake.devops.base.infra.convertor.ReleaseDataConvertor;
import com.rany.cake.devops.base.infra.dao.ReleaseDao;
import com.rany.cake.devops.base.infra.mapper.ReleasePOMapper;
import com.rany.cake.devops.base.infra.po.ReleasePO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * 发布
 *
 * @author zhongshengwang
 * @description 发布
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
@AllArgsConstructor
public class ReleaseRepositoryImpl implements ReleaseRepository {

    private final ReleasePOMapper releasePOMapper;
    private final ReleaseDao releaseDao;
    private final ReleaseDataConvertor releaseDataConvertor;

    @Override
    public Release find(@NotNull ReleaseId releaseId) {
        ReleasePO releasePO = releaseDao.selectByReleaseId(releaseId.getReleaseId());
        return releaseDataConvertor.targetToSource(releasePO);
    }

    @Override
    public void remove(@NotNull Release release) {
        ReleasePO releasePO = releaseDataConvertor.sourceToTarget(release);
        releasePO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        releasePOMapper.updateByPrimaryKey(releasePO);
    }

    @Override
    public void save(@NotNull Release release) {
        releaseDao.save(release);
    }

    @Override
    public int update(Release release) {
        return releaseDao.update(release);
    }
}
