package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.Page;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.rany.cake.devops.base.api.command.app.CreateAppCommand;
import com.rany.cake.devops.base.api.command.app.CreateAppEnvCommand;
import com.rany.cake.devops.base.api.dto.*;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.app.AppBasicQuery;
import com.rany.cake.devops.base.api.query.app.AppEnvBasicQuery;
import com.rany.cake.devops.base.api.query.app.AppEnvQuery;
import com.rany.cake.devops.base.api.query.app.AppPageQuery;
import com.rany.cake.devops.base.api.service.AppService;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.base.AppConfig;
import com.rany.cake.devops.base.domain.base.DepartmentConfig;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.pk.MemberId;
import com.rany.cake.devops.base.domain.repository.param.AppQueryParam;
import com.rany.cake.devops.base.domain.service.AppDomainService;
import com.rany.cake.devops.base.domain.service.AppMemberDomainService;
import com.rany.cake.devops.base.domain.service.ClusterDomainService;
import com.rany.cake.devops.base.domain.type.AppName;
import com.rany.cake.devops.base.domain.valueobject.BusinessOwnership;
import com.rany.cake.devops.base.domain.valueobject.CodeRepository;
import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.AppDataAdapter;
import com.rany.cake.devops.base.util.enums.AppEnvEnum;
import com.rany.cake.devops.base.util.enums.AppRoleEnum;
import com.rany.cake.devops.base.util.enums.CodeLanguageEnum;
import com.rany.cake.devops.base.util.enums.DevelopMode;
import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.query.account.AccountQuery;
import com.rany.ops.common.dto.account.AccountDTO;
import com.rany.ops.common.exception.BusinessErrorMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 应用服务
 *
 * @author zhongshengwang
 * @description 应用服务
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@Slf4j
// @ShenyuService("/app/**")
@AllArgsConstructor
public class AppRemoteService implements AppService {
    private final AppConfig tenantConfig;
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final AccountFacade accountFacade;
    private final AppMemberDomainService appMemberDomainService;
    private final AppDomainService appDomainService;
    private final ClusterDomainService clusterDomainService;
    private final AppDataAdapter appDataAdapter;
    private final DepartmentConfig departmentConfig;

    @Override
    public String createApp(CreateAppCommand createAppCommand) {
        App app = new App(new AppId(String.valueOf(snowflakeIdWorker.nextId())),
                new AppName(createAppCommand.getAppName()),
                createAppCommand.getOwner(),
                createAppCommand.getDescription(),
                new CodeRepository(createAppCommand.getRepo(), createAppCommand.getDefaultBranch()),
                EnumUtils.getEnum(CodeLanguageEnum.class, createAppCommand.getLanguage()),
                EnumUtils.getEnum(DevelopMode.class, createAppCommand.getDevelopMode()));

        app.setBusinessOwnership(new BusinessOwnership(createAppCommand.getDepartmentAbbreviation(),
                createAppCommand.getDepartment()));
        app.setHealthCheck(createAppCommand.getHealthCheck());

        Set<String> accountIds = Sets.newHashSet(createAppCommand.getOwner());
        if (CollectionUtils.isNotEmpty(createAppCommand.getAppMembers())) {
            accountIds.addAll(createAppCommand.getAppMembers().stream().map(AppMemberDTO::getAccountId).collect(Collectors.toSet()));
        }
        AccountQuery accountQuery = new AccountQuery();
        // accountQuery.setAccountIds(new ArrayList<>(accountIds));
        accountQuery.setTenantId(tenantConfig.getTenantId());
        ListResult<AccountDTO> accounts = accountFacade.findAccounts(accountQuery);
        if (accounts == null || CollectionUtils.isEmpty(accounts.getContent())) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }


        List<AccountDTO> content = accounts.getContent();
        Map<Long, AccountDTO> accountMap = Maps.uniqueIndex(content, AccountDTO::getId);
        Map<String, AppMemberDTO> accountRoleMap = Maps.uniqueIndex(createAppCommand.getAppMembers(), AppMemberDTO::getAccountId);
        ArrayList<AppMember> appMembers = new ArrayList<>();
        for (Map.Entry<Long, AccountDTO> entry : accountMap.entrySet()) {
            AppMember member = appMemberDomainService.findByAccountId(String.valueOf(entry.getKey()));
            if (member == null) {
                member = new AppMember(new MemberId(String.valueOf(snowflakeIdWorker.nextId())), app.getAppId(), String.valueOf(entry.getKey()));
                member.init(createAppCommand.getUser());
            }
            // 如果是owner
            if (createAppCommand.getOwner().equals(String.valueOf(entry.getKey()))) {
                member.authorize(AppRoleEnum.OWNER.name());
            } else {
                AppMemberDTO accountRoleItem = accountRoleMap.getOrDefault(String.valueOf(entry.getKey()), null);
                if (accountRoleItem != null && CollectionUtils.isNotEmpty(accountRoleItem.getRoles())) {
                    member.authorize(String.join(",", accountRoleItem.getRoles()));
                }
            }
            appMembers.add(member);
            app.setAppMembers(appMembers);
        }
        app.sava();
        appDomainService.save(app);
        return app.getAppId().getAppId();
    }

    @Override
    public AppDTO getApp(AppBasicQuery appBasicQuery) {
        App app = appDomainService.getApp(new AppId(appBasicQuery.getAppId()));
        if (app == null) {
            throw new DevOpsException(DevOpsErrorMessage.APP_NOT_FOUND);
        }
        return appDataAdapter.sourceToTarget(app);
    }

    @Override
    public Page<AppDTO> pageApp(AppPageQuery appPageQuery) {
        AppQueryParam appQueryParam = appDataAdapter.convertParam(appPageQuery);
        Page<App> page = appDomainService.pageApp(appQueryParam);
        List<App> apps = new ArrayList<>(page.getItems());
        List<AppDTO> appDTOList = appDataAdapter.sourceToTarget(apps);
        return PageUtils.build(page, appDTOList);
    }

    @Override
    public String createAppEnv(CreateAppEnvCommand createAppEnvCommand) {
        App app = appDomainService.getApp(new AppId(createAppEnvCommand.getAppId()));
        if (app == null) {
            throw new DevOpsException(DevOpsErrorMessage.APP_NOT_FOUND);
        }
        AppEnvDTO env = createAppEnvCommand.getEnv();
        String clusterId = env.getClusterId();
        Cluster cluster = clusterDomainService.getCluster(new ClusterId(clusterId));
        if (cluster == null) {
            throw new DevOpsException(DevOpsErrorMessage.APP_NOT_FOUND);
        }
        List<AppEnv> appEnvList = app.getAppEnvList();
        List<String> appEnvNames = appEnvList.stream().map(AppEnv::getEnvName).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(appEnvNames) && appEnvNames.contains(env.getEnvName())) {
            throw new DevOpsException(DevOpsErrorMessage.ENV_DUPLICATED);
        }
        AppEnvEnum appEnvEnum = EnumUtils.getEnum(AppEnvEnum.class, env.getEnv());
        AppEnv appEnv = new AppEnv(String.valueOf(snowflakeIdWorker.nextId()), app.getAppId(), cluster.getClusterId(), env.getEnvName(), appEnvEnum);
        appEnv.setDomains(env.getDomains());
        appEnv.setAutoScaling(env.getAutoScaling());
        appEnv.setNeedApproval(env.getNeedApproval());
        ResourceStrategyDTO resourceStrategyDTO = env.getResourceStrategy();
        appEnv.setResourceStrategy(new ResourceStrategy(resourceStrategyDTO.getReplicas(), resourceStrategyDTO.getCpu(),
                resourceStrategyDTO.getMemory(),
                resourceStrategyDTO.getMaxCpu(),
                resourceStrategyDTO.getMaxMemory()));
        appDomainService.createEnv(appEnv);
        return appEnv.getId();
    }

    @Override
    public AppEnvDTO getAppEnv(AppEnvBasicQuery appEnvBasicQuery) {
        AppEnv appEnv = appDomainService.getAppEnv(appEnvBasicQuery.getEnvId());
        return appDataAdapter.envSourceToTarget(appEnv);
    }

    @Override
    public List<AppEnvDTO> listAppEnv(AppEnvQuery appEnvQuery) {
        App app = appDomainService.getApp(new AppId(appEnvQuery.getAppId()));
        if (app == null) {
            throw new DevOpsException(DevOpsErrorMessage.APP_NOT_FOUND);
        }
        List<AppEnv> appEnvs = appDomainService.listAppEnv(new AppId(appEnvQuery.getAppId()));
        return appDataAdapter.envSourceToTarget(appEnvs);
    }

    @Override
    public List<DepartmentDTO> listDepartments() {
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        List<DepartmentConfig.Department> departments = departmentConfig.getDepartments();
        for (DepartmentConfig.Department department : departments) {
            departmentDTOS.add(new DepartmentDTO().setLabel(department.getLabel()).setValue(department.getValue())
                    .setAbbr(department.getAbbr()));
        }
        return departmentDTOS;
    }
}
