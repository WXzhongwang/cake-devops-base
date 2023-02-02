package com.rany.cake.devops.base.service.app;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.rany.cake.devops.base.api.command.CreateAppCommand;
import com.rany.cake.devops.base.api.dto.AppMemberDTO;
import com.rany.cake.devops.base.api.enums.AppRoleEnum;
import com.rany.cake.devops.base.api.service.AppService;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.MemberId;
import com.rany.cake.devops.base.domain.service.AppDomainService;
import com.rany.cake.devops.base.domain.service.AppMemberDomainService;
import com.rany.cake.devops.base.domain.type.AppName;
import com.rany.cake.devops.base.domain.valueobject.BusinessOwnership;
import com.rany.cake.devops.base.domain.valueobject.CodeRepository;
import com.rany.cake.devops.base.service.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.service.base.bean.TenantConfig;
import com.rany.uic.api.facade.account.AccountFacade;
import com.rany.uic.api.query.account.AccountQuery;
import com.rany.uic.common.dto.account.AccountDTO;
import com.rany.uic.common.enums.CommonStatusEnum;
import com.rany.uic.common.exception.BusinessException;
import com.rany.uic.common.exception.enums.BusinessErrorMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@Slf4j
@AllArgsConstructor
public class AppRemoteService implements AppService {

    private TenantConfig tenantConfig;
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final AccountFacade accountFacade;
    private final AppMemberDomainService appMemberDomainService;
    private final AppDomainService appDomainService;

    @Override
    public PojoResult<Long> createApp(CreateAppCommand createAppCommand) {
        App app = new App(new AppId(snowflakeIdWorker.nextId()),
                new AppName(createAppCommand.getAppName()),
                createAppCommand.getOwner(),
                createAppCommand.getDescription(),
                new CodeRepository(createAppCommand.getRepo(), createAppCommand.getDefaultBranch()),
                createAppCommand.getLanguage(),
                createAppCommand.getDevelopMode());

        app.setBusinessOwnership(new BusinessOwnership(createAppCommand.getBusinessUnit(),
                createAppCommand.getBusinessUnit(),
                createAppCommand.getDepartment()));
        app.setHealthCheck(createAppCommand.getHealthCheck());

        Set<Long> accountIds = Sets.newHashSet(createAppCommand.getOwner());
        accountIds.addAll(createAppCommand.getAppMembers().stream().map(AppMemberDTO::getAccountIds).collect(Collectors.toSet()));
        AccountQuery accountQuery = new AccountQuery();
        accountQuery.setAccountIds(new ArrayList<>(accountIds));
        accountQuery.setTenantId(tenantConfig.getTenantId());
        ListResult<AccountDTO> accounts = accountFacade.findAccounts(accountQuery);
        if (accounts == null || CollectionUtils.isEmpty(accounts.getContent())) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }


        List<AccountDTO> content = accounts.getContent();
        Map<Long, AccountDTO> accountMap = Maps.uniqueIndex(content, AccountDTO::getId);
        ArrayList<AppMember> appMembers = new ArrayList<>();
        for (Map.Entry<Long, AccountDTO> entry : accountMap.entrySet()) {
            AppMember member = appMemberDomainService.findByAccountId(entry.getKey());
            if (member == null) {
                member = new AppMember(new MemberId(snowflakeIdWorker.nextId()), app.getId(), entry.getKey());
                member.setStatus(CommonStatusEnum.ENABLE.getValue());
            }
            if (createAppCommand.getOwner().equals(entry.getKey())) {
                member.authorize(AppRoleEnum.OWNER.name());
            }
            appMembers.add(member);
            app.setAppMembers(appMembers);
        }
        app.sava();
        appDomainService.createApp(app);
        return PojoResult.succeed(app.getId().getId());
    }
}
