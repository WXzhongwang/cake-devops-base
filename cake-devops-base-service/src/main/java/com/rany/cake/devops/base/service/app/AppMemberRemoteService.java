package com.rany.cake.devops.base.service.app;


import com.cake.framework.common.exception.BusinessException;
import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.google.common.collect.Maps;
import com.rany.cake.devops.base.api.command.member.AddAppMemberCommand;
import com.rany.cake.devops.base.api.command.member.DeleteAppMemberCommand;
import com.rany.cake.devops.base.api.command.member.UpdateAppMemberCommand;
import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.api.dto.AppMemberDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.query.app.AppMemberPageQuery;
import com.rany.cake.devops.base.api.query.member.MemberPageQuery;
import com.rany.cake.devops.base.api.service.AppMemberService;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.base.AppConfig;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.pk.MemberId;
import com.rany.cake.devops.base.domain.repository.param.AppMemberPageQueryParam;
import com.rany.cake.devops.base.domain.service.AppMemberDomainService;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.AppMemberAdapter;
import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.query.account.AccountBasicQuery;
import com.rany.ops.api.query.account.AccountPageQuery;
import com.rany.ops.api.query.account.AccountQuery;
import com.rany.ops.common.dto.account.AccountDTO;
import com.rany.ops.common.exception.BusinessErrorMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
// @ShenyuService("/member/**")
@AllArgsConstructor
public class AppMemberRemoteService implements AppMemberService {

    private final AppConfig appConfig;
    private final AccountFacade accountFacade;
    private final AppMemberAdapter appMemberAdapter;
    private final AppMemberDomainService appMemberDomainService;
    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public Page<AppAccountDTO> pageQueryMember(MemberPageQuery memberPageQuery) {
        AccountPageQuery accountQuery = new AccountPageQuery();
        accountQuery.setAccountName(memberPageQuery.getName());
        accountQuery.setTenantId(appConfig.getTenantId());
        accountQuery.setPageNo(memberPageQuery.getPageNo());
        accountQuery.setPageSize(memberPageQuery.getPageSize());
        PageResult<AccountDTO> accounts = accountFacade.pageAccounts(accountQuery);
        Page<AccountDTO> content = accounts.getContent();
        Collection<AccountDTO> items = content.getItems();
        List<AppAccountDTO> dtoList = appMemberAdapter.toDTO(new ArrayList<>(items));
        return PageUtils.build(content, dtoList);
    }

    @Override
    public Page<AppMemberDTO> pageAppMembers(AppMemberPageQuery appMemberPageQuery) {
        AppMemberPageQueryParam param = appMemberAdapter.toParam(appMemberPageQuery);
        Page<AppMember> appMemberPage = appMemberDomainService.pageByAppId(param);
        List<AppMember> members = new ArrayList<>(appMemberPage.getItems());
        List<AppMemberDTO> appMemberDTOList = appMemberAdapter.sourceToTarget(members);


        AccountQuery accountQuery = new AccountQuery();
        accountQuery.setTenantId(appConfig.getTenantId());
        accountQuery.setAccountIds(members.stream().map(p -> Long.valueOf(p.getAccountId())).collect(Collectors.toList()));
        ListResult<AccountDTO> accountsList = accountFacade.findAccounts(accountQuery);
        List<AccountDTO> accountDTOList = accountsList.getContent();
        List<AppAccountDTO> appAccountDTOList = appMemberAdapter.toDTO(accountDTOList);
        Map<String, AppAccountDTO> accountDTOMap = Maps.uniqueIndex(appAccountDTOList, AppAccountDTO::getId);
        for (AppMemberDTO appMemberDTO : appMemberDTOList) {
            if (accountDTOMap.get(appMemberDTO.getAccountId()) != null) {
                if (accountDTOMap.get(appMemberDTO.getAccountId()) != null) {
                    AppAccountDTO accountDTO = accountDTOMap.get(appMemberDTO.getAccountId());
                    appMemberDTO.setAccountDTO(accountDTO);
                }
            }
        }
        return PageUtils.build(appMemberPage, appMemberDTOList);
    }

    @Override
    public Boolean updateMember(UpdateAppMemberCommand updateAppMemberCommand) {
        AppMember appMember = appMemberDomainService.findByMemberId(updateAppMemberCommand.getMemberId());
        appMember.update(updateAppMemberCommand.getRoles(), updateAppMemberCommand.getStatus(), updateAppMemberCommand.getUser());
        appMemberDomainService.update(appMember);
        return Boolean.TRUE;
    }

    @Override
    public Boolean addMember(AddAppMemberCommand addAppMemberCommand) {
        AccountBasicQuery accountQuery = new AccountBasicQuery();
        accountQuery.setTenantId(appConfig.getTenantId());
        accountQuery.setAccountId(Long.valueOf(addAppMemberCommand.getAccountId()));
        PojoResult<AccountDTO> account = accountFacade.getAccount(accountQuery);
        if (account == null || Objects.isNull(account.getContent())) {
            throw new BusinessException(BusinessErrorMessage.ACCOUNT_NOT_FOUND);
        }
        AppMember exist = appMemberDomainService.findById(String.valueOf(addAppMemberCommand.getAccountId()),
                addAppMemberCommand.getAppId());
        if (exist != null) {
            throw new BusinessException(DevOpsErrorMessage.MEMBER_DUPLICATED);
        }
        AppMember member = new AppMember(new MemberId(String.valueOf(snowflakeIdWorker.nextId())),
                new AppId(addAppMemberCommand.getAppId()), String.valueOf(accountQuery.getAccountId()));
        member.init(addAppMemberCommand.getUser());
        // 如果是owner
        member.authorize(String.join(",", addAppMemberCommand.getRoles()));
        appMemberDomainService.saveUpdate(member);
        return Boolean.TRUE;

    }

    @Override
    public Boolean deleteMember(DeleteAppMemberCommand deleteAppMemberCommand) {
        AppMember appMember = appMemberDomainService.findByMemberId(deleteAppMemberCommand.getMemberId());
        appMember.delete(deleteAppMemberCommand.getUser());
        appMemberDomainService.update(appMember);
        return Boolean.TRUE;
    }
}
