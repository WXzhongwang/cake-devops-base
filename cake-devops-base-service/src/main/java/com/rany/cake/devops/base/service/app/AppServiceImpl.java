package com.rany.cake.devops.base.service.app;

import com.alibaba.fastjson.JSON;
import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.Page;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.rany.cake.devops.base.api.command.app.*;
import com.rany.cake.devops.base.api.dto.*;
import com.rany.cake.devops.base.api.dto.code.Branch;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.app.*;
import com.rany.cake.devops.base.api.service.AppService;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.aggregate.Cluster;
import com.rany.cake.devops.base.domain.aggregate.Namespace;
import com.rany.cake.devops.base.domain.base.AppConfig;
import com.rany.cake.devops.base.domain.base.DepartmentConfig;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.AppEnv;
import com.rany.cake.devops.base.domain.entity.ServiceValueObject;
import com.rany.cake.devops.base.domain.events.AppEnvCreateEvent;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.ClusterId;
import com.rany.cake.devops.base.domain.pk.MemberId;
import com.rany.cake.devops.base.domain.repository.NameSpaceRepository;
import com.rany.cake.devops.base.domain.repository.param.AppQueryParam;
import com.rany.cake.devops.base.domain.service.AppDomainService;
import com.rany.cake.devops.base.domain.service.ClusterDomainService;
import com.rany.cake.devops.base.domain.type.AppName;
import com.rany.cake.devops.base.domain.valueobject.AppExtend;
import com.rany.cake.devops.base.domain.valueobject.BusinessOwnership;
import com.rany.cake.devops.base.domain.valueobject.CodeRepository;
import com.rany.cake.devops.base.domain.valueobject.ResourceStrategy;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.AppDataAdapter;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.integration.cloud.BaseCloudService;
import com.rany.cake.devops.base.service.integration.cloud.CloudFactory;
import com.rany.cake.devops.base.service.integration.cloud.dto.*;
import com.rany.cake.devops.base.service.integration.code.BaseCodeService;
import com.rany.cake.devops.base.service.integration.code.CodeFactory;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.context.ApplicationContext;

import java.util.*;
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
@AllArgsConstructor
public class AppServiceImpl implements AppService {
    private final AppConfig tenantConfig;
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final AccountFacade accountFacade;
    private final AppDomainService appDomainService;
    private final ClusterDomainService clusterDomainService;
    private final NameSpaceRepository nameSpaceRepository;
    private final AppDataAdapter appDataAdapter;
    private final DepartmentConfig departmentConfig;
    private final ApplicationContext applicationContext;
    private final CodeFactory codeFactory;
    private final CloudFactory cloudFactory;

    @Override
    public String createApp(CreateAppCommand createAppCommand) {
        App app = new App(new AppId(String.valueOf(snowflakeIdWorker.nextId())),
                new AppName(createAppCommand.getAppName()),
                createAppCommand.getOwner(),
                createAppCommand.getDescription(),
                new CodeRepository(createAppCommand.getRepo(), createAppCommand.getDefaultBranch(),
                        createAppCommand.getCodePlatform()),
                EnumUtils.getEnum(CodeLanguageEnum.class, createAppCommand.getLanguage()),
                EnumUtils.getEnum(DevelopMode.class, createAppCommand.getDevelopMode()));
        app.setWebhook(createAppCommand.getWebhook());
        if (StringUtils.isNotEmpty(createAppCommand.getAppExtend())) {
            app.setAppExtend(JSON.parseObject(createAppCommand.getAppExtend(), AppExtend.class));
        }
        List<DepartmentConfig.Department> departments = departmentConfig.getDepartments();
        Optional<DepartmentConfig.Department> department = departments.stream().filter(p -> Objects.equals(p.getValue(), createAppCommand.getDepartment())).findFirst();
        if (!department.isPresent()) {
            throw new BusinessException(DevOpsErrorMessage.DEP_NOT_FOUND);
        }
        DepartmentConfig.Department departmentValue = department.get();
        app.setBusinessOwnership(new BusinessOwnership(departmentValue.getAbbr(),
                departmentValue.getValue()));
        app.setHealthCheck(createAppCommand.getHealthCheck());

        Set<String> accountIds = Sets.newHashSet(createAppCommand.getOwner());
        List<Long> accountIdLongs = accountIds.stream().map(NumberUtils::toLong).collect(Collectors.toList());
        AccountQuery accountQuery = new AccountQuery();
        accountQuery.setAccountIds(accountIdLongs);
        accountQuery.setTenantId(tenantConfig.getTenantId());
        List<AccountDTO> accounts = accountFacade.findAccounts(accountQuery);
        if (CollectionUtils.isEmpty(accounts)) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }


        Map<Long, AccountDTO> accountMap = Maps.uniqueIndex(accounts, AccountDTO::getId);
        ArrayList<AppMember> appMembers = new ArrayList<>();
        app.setAppMembers(appMembers);


        Map<String, AppMemberDTO> accountRoleMap =
                CollectionUtils.isEmpty(createAppCommand.getAppMembers()) ? Maps.newHashMap() :
                        Maps.uniqueIndex(createAppCommand.getAppMembers(), AppMemberDTO::getAccountId);

        for (Map.Entry<Long, AccountDTO> entry : accountMap.entrySet()) {
            AppMember member = new AppMember(new MemberId(String.valueOf(snowflakeIdWorker.nextId())), app.getAppId(), String.valueOf(entry.getKey()));
            member.init(createAppCommand.getUser());
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
        }

        app.sava(createAppCommand.getUser());
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
        List<String> envNames = appEnvList.stream().map(AppEnv::getEnv).map(AppEnvEnum::name).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(appEnvNames) && appEnvNames.contains(env.getEnvName())) {
            throw new DevOpsException(DevOpsErrorMessage.ENV_DUPLICATED);
        }
        // 同一环境只能有一个
        if (CollectionUtils.isNotEmpty(envNames) && envNames.contains(env.getEnv())) {
            throw new DevOpsException(DevOpsErrorMessage.ENV_DUPLICATED);
        }
        AppEnvEnum appEnvEnum = EnumUtils.getEnum(AppEnvEnum.class, env.getEnv());
        AppEnv appEnv = new AppEnv(String.valueOf(snowflakeIdWorker.nextId()), app.getAppId(), cluster.getClusterId(), env.getEnvName(), appEnvEnum);
        // appEnv.setDomains(env.getDomains());
        appEnv.setAutoScaling(env.getAutoScaling());
        appEnv.setNeedApproval(env.getNeedApproval());
        appEnv.setCustomBuildScript(env.getCustomBuildScript());
        ResourceStrategyDTO resourceStrategyDTO = env.getResourceStrategy();
        appEnv.setResourceStrategy(new ResourceStrategy(resourceStrategyDTO.getReplicas(), resourceStrategyDTO.getCpu(),
                resourceStrategyDTO.getMemory(),
                resourceStrategyDTO.getMaxCpu(),
                resourceStrategyDTO.getMaxMemory()));

        appEnv.sava(createAppEnvCommand.getUser());
        appDomainService.createEnv(appEnv);
        applicationContext.publishEvent(new AppEnvCreateEvent(appEnv.getAppId(), appEnv.getEnvId()));
        return appEnv.getId();
    }

    @Override
    public Boolean modifyAppConfigMap(ModifyConfigMapCommand modifyAppEnvConfigMapCommand) {
        DeployContext context = new DeployContext();
        AppEnv appEnv = appDomainService.getAppEnv(modifyAppEnvConfigMapCommand.getEnvId());
        context.setAppEnv(appEnv);
        AppId appId = appEnv.getAppId();
        ClusterId clusterId = appEnv.getClusterId();
        App app = appDomainService.getApp(appId);
        Cluster cluster = clusterDomainService.getCluster(clusterId);
        BusinessOwnership businessOwnership = app.getBusinessOwnership();
        Namespace namespace = nameSpaceRepository.findByCluster(cluster.getClusterId().getClusterId(), businessOwnership.getDepartment());
        context.setNamespace(namespace);
        context.setApp(app);
        context.setCluster(cluster);
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());


        UpdateConfigMapCmd createConfigMapCmd = new UpdateConfigMapCmd();
        createConfigMapCmd.setNamespace(namespace.getName().getName());
        createConfigMapCmd.setAppName(app.getAppName().getName());
        createConfigMapCmd.setEnvName(appEnv.getEnvName());
        createConfigMapCmd.setEnvId(appEnv.getEnvId());
        createConfigMapCmd.setConfigMap(modifyAppEnvConfigMapCommand.getConfigMap());
        createConfigMapCmd.setCurrentConfigMap(appEnv.getConfigMap());
        Boolean updated = cloudService.createOrUpdateConfigMap(context, createConfigMapCmd);
        if (updated) {
            appEnv.setConfigMap(modifyAppEnvConfigMapCommand.getConfigMap());
            appDomainService.updateAppEnv(appEnv);
        }
        return updated;
    }

    @Override
    public List<PodDTO> listAppEnvPod(AppEnvPodQuery appEnvPodQuery) {
        DeployContext context = new DeployContext();
        AppEnv appEnv = appDomainService.getAppEnv(appEnvPodQuery.getEnvId());
        context.setAppEnv(appEnv);
        AppId appId = appEnv.getAppId();
        ClusterId clusterId = appEnv.getClusterId();
        App app = appDomainService.getApp(appId);
        Cluster cluster = clusterDomainService.getCluster(clusterId);
        BusinessOwnership businessOwnership = app.getBusinessOwnership();
        Namespace namespace = nameSpaceRepository.findByCluster(cluster.getClusterId().getClusterId(), businessOwnership.getDepartment());
        context.setNamespace(namespace);
        context.setApp(app);
        context.setCluster(cluster);
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());
        ListPodCmd listPodCmd = new ListPodCmd(namespace.getName().getName(), appEnv.getDeployment());
        List<PodInfoDTO> podList = cloudService.getPodsForDeployment(context, listPodCmd);
        return appDataAdapter.convertPodInfo(podList);
    }

    @Override
    public Boolean modifyAppEnvVars(ModifyEnvVarsCommand modifyEnvVarsCommand) {
        DeployContext context = new DeployContext();
        AppEnv appEnv = appDomainService.getAppEnv(modifyEnvVarsCommand.getEnvId());
        context.setAppEnv(appEnv);
        AppId appId = appEnv.getAppId();
        ClusterId clusterId = appEnv.getClusterId();
        App app = appDomainService.getApp(appId);
        Cluster cluster = clusterDomainService.getCluster(clusterId);
        BusinessOwnership businessOwnership = app.getBusinessOwnership();
        Namespace namespace = nameSpaceRepository.findByCluster(cluster.getClusterId().getClusterId(), businessOwnership.getDepartment());
        context.setNamespace(namespace);
        context.setApp(app);
        context.setCluster(cluster);
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());
        UpdateEnvVarsCmd updateEnvVarsCmd = new UpdateEnvVarsCmd();
        updateEnvVarsCmd.setNamespace(namespace.getName().getName());
        updateEnvVarsCmd.setDeploymentName(appEnv.getDeployment());
        updateEnvVarsCmd.setEnvVars(modifyEnvVarsCommand.getEnvVars());
        Boolean updated = cloudService.updateDeploymentEnvVars(context, updateEnvVarsCmd);
        if (updated) {
            appEnv.setEnvVars(modifyEnvVarsCommand.getEnvVars());
            appDomainService.updateAppEnv(appEnv);
        }
        return updated;
    }

    @Override
    public Boolean modifyService(ModifyServiceCommand modifyServiceCommand) {
        DeployContext context = new DeployContext();
        AppEnv appEnv = appDomainService.getAppEnv(modifyServiceCommand.getEnvId());
        context.setAppEnv(appEnv);
        AppId appId = appEnv.getAppId();
        ClusterId clusterId = appEnv.getClusterId();
        App app = appDomainService.getApp(appId);
        Cluster cluster = clusterDomainService.getCluster(clusterId);
        BusinessOwnership businessOwnership = app.getBusinessOwnership();
        Namespace namespace = nameSpaceRepository.findByCluster(cluster.getClusterId().getClusterId(), businessOwnership.getDepartment());
        context.setNamespace(namespace);
        context.setApp(app);
        context.setCluster(cluster);
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());

        ModifyServiceCmd modifyServiceCmd = new ModifyServiceCmd();
        modifyServiceCmd.setNamespace(namespace.getName().getName());
        modifyServiceCmd.setServicePort(modifyServiceCommand.getServicePort());
        modifyServiceCmd.setServiceProtocol(modifyServiceCommand.getServiceProtocol());
        modifyServiceCmd.setServiceType(modifyServiceCommand.getServiceType());
        modifyServiceCmd.setContainerPort(modifyServiceCommand.getContainerPort());
        modifyServiceCmd.setNodePort(modifyServiceCommand.getNodePort());
        modifyServiceCmd.setServiceName(modifyServiceCommand.getServiceName());


        Boolean updated = cloudService.updateService(context, modifyServiceCmd);
        List<ServiceValueObject> serviceList = appEnv.getServiceList();
        serviceList.stream().filter(service -> service.getServiceName().equals(modifyServiceCommand.getServiceName())).forEach(service -> {
            service.setContainerPort(modifyServiceCommand.getContainerPort());
            service.setNodePort(modifyServiceCommand.getNodePort());
            service.setServicePort(modifyServiceCommand.getServicePort());
            service.setServiceProtocol(modifyServiceCommand.getServiceProtocol());
            service.setServiceType(modifyServiceCommand.getServiceType());
        });
        if (updated) {
            appEnv.setService(JSON.toJSONString(serviceList));
            appDomainService.updateAppEnv(appEnv);
        }
        return updated;
    }

    @Override
    public Boolean createService(CreateServiceCommand createServiceCommand) {
        DeployContext context = new DeployContext();
        AppEnv appEnv = appDomainService.getAppEnv(createServiceCommand.getEnvId());
        context.setAppEnv(appEnv);
        AppId appId = appEnv.getAppId();
        ClusterId clusterId = appEnv.getClusterId();
        App app = appDomainService.getApp(appId);
        Cluster cluster = clusterDomainService.getCluster(clusterId);
        BusinessOwnership businessOwnership = app.getBusinessOwnership();
        Namespace namespace = nameSpaceRepository.findByCluster(cluster.getClusterId().getClusterId(), businessOwnership.getDepartment());
        context.setNamespace(namespace);
        context.setApp(app);
        context.setCluster(cluster);
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());

        CreateServiceCmd createServiceCmd = new CreateServiceCmd();
        createServiceCmd.setNamespace(namespace.getName().getName());
        createServiceCmd.setServicePort(createServiceCommand.getServicePort());
        createServiceCmd.setServiceProtocol(createServiceCommand.getServiceProtocol());
        createServiceCmd.setServiceType(createServiceCommand.getServiceType());
        createServiceCmd.setContainerPort(createServiceCommand.getContainerPort());
        createServiceCmd.setNodePort(createServiceCommand.getNodePort());
        createServiceCmd.setServiceName(createServiceCommand.getServiceName());

        List<ServiceValueObject> serviceList = appEnv.getServiceList();
        serviceList.add(ServiceValueObject.builder()
                .containerPort(createServiceCommand.getContainerPort())
                .nodePort(createServiceCommand.getNodePort())
                .serviceName(createServiceCommand.getServiceName())
                .servicePort(createServiceCommand.getServicePort())
                .serviceProtocol(createServiceCommand.getServiceProtocol())
                .serviceType(createServiceCommand.getServiceType())
                .build());
        Boolean created = cloudService.createService(context, createServiceCmd);
        if (created) {
            appEnv.setService(JSON.toJSONString(appEnv.getService()));
            appDomainService.updateAppEnv(appEnv);
        }
        return created;
    }

    @Override
    public Boolean deleteService(DeleteServiceCommand deleteServiceCommand) {
        DeployContext context = new DeployContext();
        AppEnv appEnv = appDomainService.getAppEnv(deleteServiceCommand.getEnvId());
        context.setAppEnv(appEnv);
        AppId appId = appEnv.getAppId();
        ClusterId clusterId = appEnv.getClusterId();
        App app = appDomainService.getApp(appId);
        Cluster cluster = clusterDomainService.getCluster(clusterId);
        BusinessOwnership businessOwnership = app.getBusinessOwnership();
        Namespace namespace = nameSpaceRepository.findByCluster(cluster.getClusterId().getClusterId(), businessOwnership.getDepartment());
        context.setNamespace(namespace);
        context.setApp(app);
        context.setCluster(cluster);
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());

        DeleteServiceCmd deleteServiceCmd = new DeleteServiceCmd();
        deleteServiceCmd.setNamespace(namespace.getName().getName());
        deleteServiceCmd.setServiceName(deleteServiceCommand.getServiceName());
        Boolean deleted = cloudService.deleteService(context, deleteServiceCmd);
        List<ServiceValueObject> serviceList = appEnv.getServiceList();
        serviceList.removeIf(service -> service.getServiceName().equals(deleteServiceCommand.getServiceName()));
        if (deleted) {
            appEnv.setService(JSON.toJSONString(appEnv.getService()));
            appDomainService.updateAppEnv(appEnv);
        }
        return deleted;
    }

    @Override
    public Boolean modifyAppEnvResource(ModifyEnvResourceCommand modifyEnvResourceCommand) {
        DeployContext context = new DeployContext();
        AppEnv appEnv = appDomainService.getAppEnv(modifyEnvResourceCommand.getEnvId());
        context.setAppEnv(appEnv);
        AppId appId = appEnv.getAppId();
        ClusterId clusterId = appEnv.getClusterId();
        App app = appDomainService.getApp(appId);
        Cluster cluster = clusterDomainService.getCluster(clusterId);
        BusinessOwnership businessOwnership = app.getBusinessOwnership();
        Namespace namespace = nameSpaceRepository.findByCluster(cluster.getClusterId().getClusterId(), businessOwnership.getDepartment());
        context.setNamespace(namespace);
        context.setApp(app);
        context.setCluster(cluster);
        context.setDeploymentName(app.getAppName().getName());
        ResourceStrategy resourceStrategy = appDataAdapter.strategyTargetToSource(modifyEnvResourceCommand.getResourceStrategyDTO());
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());
        UpdateResourceCmd updateResourceCmd = new UpdateResourceCmd();
        updateResourceCmd.setResourceStrategy(resourceStrategy);
        updateResourceCmd.setDeploymentName(appEnv.getDeployment());
        updateResourceCmd.setNamespace(namespace.getName().getName());
        Boolean updated = cloudService.updateDeploymentResources(context, updateResourceCmd);
        if (updated) {
            appEnv.setResourceStrategy(resourceStrategy);
            appDomainService.updateAppEnv(appEnv);
        }
        return updated;
    }

    @Override
    public Boolean scale(ScaleEnvCommand scaleEnvCommand) {
        DeployContext context = new DeployContext();
        AppEnv appEnv = appDomainService.getAppEnv(scaleEnvCommand.getEnvId());
        context.setAppEnv(appEnv);
        AppId appId = appEnv.getAppId();
        ClusterId clusterId = appEnv.getClusterId();
        App app = appDomainService.getApp(appId);
        Cluster cluster = clusterDomainService.getCluster(clusterId);
        BusinessOwnership businessOwnership = app.getBusinessOwnership();
        Namespace namespace = nameSpaceRepository.findByCluster(cluster.getClusterId().getClusterId(), businessOwnership.getDepartment());
        context.setNamespace(namespace);
        context.setApp(app);
        context.setCluster(cluster);
        context.setDeploymentName(app.getAppName().getName());
        ResourceStrategy resourceStrategy = appEnv.getResourceStrategy();
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());
        ScaleDeploymentCmd scaleDeploymentCmd = new ScaleDeploymentCmd();
        scaleDeploymentCmd.setNamespace(namespace.getName().getName());
        scaleDeploymentCmd.setReplicas(scaleEnvCommand.getReplicas());
        scaleDeploymentCmd.setDeploymentName(appEnv.getDeployment());
        Boolean updated = cloudService.scaleDeployment(context, scaleDeploymentCmd);
        if (updated) {
            resourceStrategy.setReplicas(scaleEnvCommand.getReplicas());
            appEnv.setResourceStrategy(resourceStrategy);
            appDomainService.updateAppEnv(appEnv);
        }
        return updated;
    }

    @Override
    public Boolean modifyAppEnvDomains(ModifyAppEnvDomainCommand modifyAppEnvDomainCommand) {
        DeployContext context = new DeployContext();
        AppEnv appEnv = appDomainService.getAppEnv(modifyAppEnvDomainCommand.getEnvId());
        context.setAppEnv(appEnv);
        AppId appId = appEnv.getAppId();
        ClusterId clusterId = appEnv.getClusterId();
        App app = appDomainService.getApp(appId);
        Cluster cluster = clusterDomainService.getCluster(clusterId);
        BusinessOwnership businessOwnership = app.getBusinessOwnership();
        Namespace namespace = nameSpaceRepository.findByCluster(cluster.getClusterId().getClusterId(), businessOwnership.getDepartment());
        context.setNamespace(namespace);
        context.setApp(app);
        context.setCluster(cluster);
        BaseCloudService cloudService = cloudFactory.build(context.getCluster().getClusterType(),
                context.getCluster().getConnectionString(), context.getCluster().getToken());
        appEnv.setDomains(modifyAppEnvDomainCommand.getDomains());
        CreateIngressCmd createIngressCmd = new CreateIngressCmd(namespace.getName().getName(), modifyAppEnvDomainCommand.getServiceName(),
                modifyAppEnvDomainCommand.getServicePort(),
                appEnv.getDomains(),
                modifyAppEnvDomainCommand.getIngressName()
        );
        Boolean updated = cloudService.createOrUpdateIngress(context, createIngressCmd);
        if (updated) {
            appDomainService.updateAppEnv(appEnv);
        }
        return updated;
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

    @Override
    public List<Branch> listAppBranch(String appId, String search) {
        App app = appDomainService.getApp(new AppId(appId));
        if (app == null) {
            throw new DevOpsException(DevOpsErrorMessage.APP_NOT_FOUND);
        }
        CodeRepository codeRepository = app.getCodeRepository();
        AppExtend appExtend = app.getAppExtend();
        BaseCodeService codeService = codeFactory.build(codeRepository.of(), appExtend);
        return codeService.listBranch(codeRepository.getRepo(), search, 1, 100);
    }
}
