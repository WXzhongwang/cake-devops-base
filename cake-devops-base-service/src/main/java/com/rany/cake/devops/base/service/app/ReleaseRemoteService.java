package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.release.CreateReleaseCommand;
import com.rany.cake.devops.base.api.command.release.DeployCommand;
import com.rany.cake.devops.base.api.dto.ReleaseDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.ReleasePageQuery;
import com.rany.cake.devops.base.api.service.ReleaseService;
import com.rany.cake.devops.base.domain.aggregate.*;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.enums.ApprovalStatus;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.ApprovalId;
import com.rany.cake.devops.base.domain.pk.NamespaceId;
import com.rany.cake.devops.base.domain.pk.ReleaseId;
import com.rany.cake.devops.base.domain.repository.*;
import com.rany.cake.devops.base.domain.repository.param.ReleasePageQueryParam;
import com.rany.cake.devops.base.domain.service.ApprovalDomainService;
import com.rany.cake.devops.base.domain.service.ReleaseDomainService;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.ReleaseCenter;
import com.rany.cake.devops.base.service.adapter.ReleaseDataAdapter;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.code.RedisSerialNumberGenerator;
import com.rany.cake.devops.base.service.lock.client.RedissonLockClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@ShenyuService("/release/**")
@Slf4j
@AllArgsConstructor
public class ReleaseRemoteService implements ReleaseService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final ReleaseDomainService releaseDomainService;
    private final ApprovalDomainService approvalDomainService;
    private final ReleaseRepository releaseRepository;
    private final AppRepository appRepository;
    private final ApprovalRepository approvalRepository;
    private final ClusterRepository clusterRepository;
    private final NameSpaceRepository nameSpaceRepository;
    private final RedisSerialNumberGenerator redisSerialNumberGenerator;
    private final RedissonLockClient redissonLockClient;
    private final ReleaseCenter releaseCenter;
    private final ReleaseDataAdapter releaseDataAdapter;

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
        Release release = new Release(new ReleaseId(String.valueOf(snowflakeIdWorker.nextId())),
                new AppId(createReleaseCommand.getAppId()),
                appEnv.getEnvId(),
                redisSerialNumberGenerator.generateReleaseSerialNumber(),
                createReleaseCommand.getReleaseDate()
        );
        release.init(appEnv, createReleaseCommand.getReleaseBranch(), createReleaseCommand.getReleaseCommitId(),
                createReleaseCommand.getReleaseVersion());
        // 发起审批
        Approval approval = new Approval(new ApprovalId(String.valueOf(snowflakeIdWorker.nextId())),
                createReleaseCommand.getDocAddress(),
                createReleaseCommand.getReleaseDate(),
                ApprovalStatus.PENDING.name(),
                createReleaseCommand.getComment());
        approval.init(appEnv.getNeedApproval());

        release.setApprovalId(approval.getApprovalId());
        approvalDomainService.save(approval);
        releaseDomainService.save(release);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PageResult<ReleaseDTO> pageRelease(ReleasePageQuery releasePageQuery) {
        ReleasePageQueryParam releasePageQueryParam = releaseDataAdapter.convertParam(releasePageQuery);
        Page<Release> page = releaseDomainService.pageRelease(releasePageQueryParam);
        List<Release> releases = new ArrayList<>(page.getItems());
        List<ReleaseDTO> releaseDTOList = releaseDataAdapter.sourceToTarget(releases);
        return PageResult.succeed(PageUtils.build(page, releaseDTOList));
    }

    @Override
    public PojoResult<Boolean> deploy(DeployCommand deployCommand) {
        Release release = releaseRepository.find(new ReleaseId(String.valueOf(deployCommand.getReleaseId())));
        if (release == null) {
            throw new DevOpsException(DevOpsErrorMessage.RELEASE_NOT_FOUND);
        }
        App app = appRepository.find(release.getAppId());
        AppEnv appEnv = appRepository.getAppEnv(release.getEnvId());
        Cluster cluster = clusterRepository.find(appEnv.getClusterId());
        Namespace namespace = nameSpaceRepository.find(new NamespaceId("1"));

        if (Objects.nonNull(release.getApprovalId())) {
            Approval approval = approvalRepository.find(release.getApprovalId());
            if (approval != null && !StringUtils.equals(approval.getApprovalStatus(), ApprovalStatus.APPROVED.name())) {
                throw new DevOpsException(DevOpsErrorMessage.APPROVAL_NOT_APPROVED);
            }
        }

        String lockKey = String.format(Constants.CI_CD_DEPLOY_LOCK, app.getAppId(), appEnv.getEnvId());
        try {
            redissonLockClient.lock(lockKey);
            releaseCenter.release(release, app, appEnv, namespace, cluster);
            releaseDomainService.update(release);
        } catch (Exception ex) {
            log.error("发生错误，请重试", ex);
        } finally {
            redissonLockClient.unlock(lockKey);
        }
        return PojoResult.succeed(Boolean.TRUE);
    }
}
