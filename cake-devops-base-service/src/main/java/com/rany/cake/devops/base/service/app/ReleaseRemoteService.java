package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.release.CreateReleaseCommand;
import com.rany.cake.devops.base.api.command.release.DeployCommand;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.service.ReleaseService;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.Approval;
import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.enums.ApprovalStatus;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import com.rany.cake.devops.base.domain.repository.AppRepository;
import com.rany.cake.devops.base.domain.repository.ApprovalRepository;
import com.rany.cake.devops.base.domain.repository.ReleaseRepository;
import com.rany.cake.devops.base.domain.service.ReleaseDomainService;
import com.rany.cake.devops.base.service.code.RedisSerialNumberGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class ReleaseRemoteService implements ReleaseService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final ReleaseDomainService releaseDomainService;
    private final ReleaseRepository releaseRepository;
    private final AppRepository appRepository;
    private final ApprovalRepository approvalRepository;
    private final RedisSerialNumberGenerator redisSerialNumberGenerator;

    @Override
    public PojoResult<Boolean> createRelease(CreateReleaseCommand createReleaseCommand) {
        App app = appRepository.find(new AppId(createReleaseCommand.getAppId()));
        if (app == null) {
            throw new DevOpsException(DevOpsErrorMessage.APP_NOT_FOUND);
        }
        AppEnv appEnv = appRepository.getAppEnv(createReleaseCommand.getEnvId());
        if (appEnv == null) {
            throw new DevOpsException(DevOpsErrorMessage.ENV_NOT_FOUND);
        }
        Release release = new Release(new ReleaseId(snowflakeIdWorker.nextId()),
                new AppId(createReleaseCommand.getAppId()),
                appEnv.getId(),
                redisSerialNumberGenerator.generateReleaseSerialNumber(),
                createReleaseCommand.getReleaseDate()
        );
        release.init(appEnv);
        if (Objects.nonNull(createReleaseCommand.getApprovalId())) {
            Approval approval = approvalRepository.find(new ApprovalId(createReleaseCommand.getApprovalId()));
            if (approval == null) {
                throw new DevOpsException(DevOpsErrorMessage.APPROVAL_NOT_FOUND);
            }
            if (StringUtils.equals(approval.getApprovalStatus(), ApprovalStatus.APPROVED.name())) {
                release.approved();
            }
        }
        releaseDomainService.save(release);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deploy(DeployCommand deployCommand) {
        Release release = releaseRepository.find(new ReleaseId(deployCommand.getReleaseId()));
        if (release == null) {
            throw new DevOpsException(DevOpsErrorMessage.RELEASE_NOT_FOUND);
        }
        if (Objects.nonNull(release.getApprovalId())) {
            Approval approval = approvalRepository.find(release.getApprovalId());
            if (approval != null && !StringUtils.equals(approval.getApprovalStatus(), ApprovalStatus.APPROVED.name())) {
                throw new DevOpsException(DevOpsErrorMessage.APPROVAL_NOT_APPROVED);
            }
        }
        release.deploy();
        releaseDomainService.update(release);
        // TODO 开始发布
        return PojoResult.succeed(Boolean.TRUE);
    }
}
