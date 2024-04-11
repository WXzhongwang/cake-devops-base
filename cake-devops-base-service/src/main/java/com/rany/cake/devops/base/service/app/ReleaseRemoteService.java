package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.rany.cake.devops.base.api.command.release.CloseReleaseCommand;
import com.rany.cake.devops.base.api.command.release.CreateReleaseCommand;
import com.rany.cake.devops.base.api.command.release.DeployCommand;
import com.rany.cake.devops.base.api.dto.ApprovalDTO;
import com.rany.cake.devops.base.api.dto.ReleaseDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.release.ReleasePageQuery;
import com.rany.cake.devops.base.api.service.ReleaseService;
import com.rany.cake.devops.base.domain.aggregate.*;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.AppEnv;
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
import com.rany.cake.devops.base.service.adapter.ApprovalDataAdapter;
import com.rany.cake.devops.base.service.adapter.ReleaseDataAdapter;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.service.code.RedisSerialNumberGenerator;
import com.rany.cake.devops.base.service.lock.client.RedissonLockClient;
import com.rany.cake.devops.base.util.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private final ApprovalDataAdapter approvalDataAdapter;

    @Override
    public Boolean createRelease(CreateReleaseCommand createReleaseCommand) {
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
        return Boolean.TRUE;
    }

    @Override
    public Page<ReleaseDTO> pageRelease(ReleasePageQuery releasePageQuery) {
        ReleasePageQueryParam releasePageQueryParam = releaseDataAdapter.convertParam(releasePageQuery);
        Page<Release> page = releaseDomainService.pageRelease(releasePageQueryParam);
        List<Release> releases = new ArrayList<>(page.getItems());
        List<ReleaseDTO> releaseDTOList = releaseDataAdapter.sourceToTarget(releases);
        List<String> approvalIdList = releaseDTOList.stream().map(ReleaseDTO::getApprovalId).filter(Objects::nonNull).collect(Collectors.toList());
        List<Approval> approvalList = approvalRepository.findByIds(approvalIdList);
        List<ApprovalDTO> approvalDTOS = approvalDataAdapter.sourceToTarget(approvalList);
        ImmutableMap<String, ApprovalDTO> approvalMap = Maps.uniqueIndex(approvalDTOS, ApprovalDTO::getApprovalId);
        for (ReleaseDTO releaseDTO : releaseDTOList) {
            if (StringUtils.isNotEmpty(releaseDTO.getApprovalId())) {
                ApprovalDTO approvalDTO = approvalMap.get(releaseDTO.getApprovalId());
                releaseDTO.setApprovalDTO(approvalDTO);
            }
        }
        return PageUtils.build(page, releaseDTOList);
    }

    @Override
    public Boolean deploy(DeployCommand deployCommand) {
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
            if (approval != null && !StringUtils.equals(approval.getApprovalStatus(), ApprovalStatus.APPROVED.name())
                    && !StringUtils.equals(approval.getApprovalStatus(), ApprovalStatus.AUTO_APPROVED.name())) {
                throw new DevOpsException(DevOpsErrorMessage.APPROVAL_NOT_APPROVED);
            }
        }

        String lockKey = String.format(Constants.CI_CD_DEPLOY_LOCK, app.getAppId(), appEnv.getEnvId());
        try {
            redissonLockClient.lock(lockKey);
            releaseCenter.release(release, app, appEnv, namespace, cluster);
        } catch (Exception ex) {
            log.error("发生错误，请重试", ex);
        } finally {
            redissonLockClient.unlock(lockKey);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean close(CloseReleaseCommand command) {
        Release release = releaseRepository.find(new ReleaseId(String.valueOf(command.getReleaseId())));
        if (release == null) {
            throw new DevOpsException(DevOpsErrorMessage.RELEASE_NOT_FOUND);
        }
        release.closed();
        releaseDomainService.update(release);
        return Boolean.TRUE;
    }
}
