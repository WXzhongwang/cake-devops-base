package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.release.CreateReleaseCommand;
import com.rany.cake.devops.base.api.service.ReleaseService;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.ApprovalRepository;
import com.rany.cake.devops.base.domain.repository.ReleaseRepository;
import com.rany.cake.devops.base.domain.service.ReleaseDomainService;
import com.rany.cake.devops.base.service.adapter.ReleaseDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class ReleaseRemoteService implements ReleaseService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final ReleaseDomainService releaseDomainService;
    private final ReleaseRepository releaseRepository;
    private final ReleaseDataAdapter releaseDataAdapter;
    private final AppRepository appRepository;
    private final ApprovalRepository approvalRepository;

    @Override
    public PojoResult<Boolean> createRelease(CreateReleaseCommand createReleaseCommand) {
        App app = appRepository.find(new AppId(createReleaseCommand.getAppId()));
        AppEnv appEnv = appRepository.getAppEnv(createReleaseCommand.getEnvId());
        Approval approval = approvalRepository.find(new ApprovalId(createReleaseCommand.getApprovalId()));
        Release release = new Release(new ReleaseId(snowflakeIdWorker.nextId()),
                new AppId(createReleaseCommand.getAppId()),
                appEnv.getId(),
                UUID.randomUUID().toString(),
                createReleaseCommand.getReleaseDate()
        );
        releaseDomainService.save(release);
        return PojoResult.succeed(Boolean.TRUE);
    }
}
