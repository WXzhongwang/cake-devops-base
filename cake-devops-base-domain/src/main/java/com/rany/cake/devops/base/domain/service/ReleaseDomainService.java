package com.rany.cake.devops.base.domain.service;

import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import com.rany.cake.devops.base.domain.repository.ReleaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 发布
 *
 * @author zhongshengwang
 * @description 发布
 * @date 2023/1/28 20:59
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class ReleaseDomainService {

    private final ReleaseRepository releaseRepository;

    /**
     * 创建一次发布
     *
     * @param release
     */

    public void save(Release release) {
        releaseRepository.save(release);
    }

    public void update(Release release) {
        releaseRepository.update(release);
    }

    public Release getRelease(ReleaseId releaseId) {
        return releaseRepository.find(releaseId);
    }
}
