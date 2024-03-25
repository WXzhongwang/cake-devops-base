package com.rany.cake.devops.base.service.app;


import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.google.common.collect.Maps;
import com.rany.cake.devops.base.api.command.member.DeleteAppMemberCommand;
import com.rany.cake.devops.base.api.command.member.UpdateAppMemberCommand;
import com.rany.cake.devops.base.api.dto.AppAccountDTO;
import com.rany.cake.devops.base.api.dto.AppMemberDTO;
import com.rany.cake.devops.base.api.query.AppMemberPageQuery;
import com.rany.cake.devops.base.api.query.MemberPageQuery;
import com.rany.cake.devops.base.api.service.AppMemberService;
import com.rany.cake.devops.base.domain.aggregate.AppMember;
import com.rany.cake.devops.base.domain.base.AppConfig;
import com.rany.cake.devops.base.domain.repository.param.AppMemberPageQueryParam;
import com.rany.cake.devops.base.domain.service.AppMemberDomainService;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.AppMemberAdapter;
import com.rany.uic.api.facade.account.AccountFacade;
import com.rany.uic.api.query.account.AccountPageQuery;
import com.rany.uic.api.query.account.AccountQuery;
import com.rany.uic.common.dto.account.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
@ShenyuService("/member/**")
@AllArgsConstructor
public class AppMemberRemoteService implements AppMemberService {

    private final AppConfig tenantConfig;
    private final AccountFacade accountFacade;
    private final AppMemberAdapter appMemberAdapter;
    private final AppMemberDomainService appMemberDomainService;

    @Override
    public Page<AppAccountDTO> pageQueryMember(MemberPageQuery memberPageQuery) {
        AccountPageQuery accountQuery = new AccountPageQuery();
        accountQuery.setAccountName(memberPageQuery.getName());
        accountQuery.setTenantId(tenantConfig.getTenantId());
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
        accountQuery.setTenantId(tenantConfig.getTenantId());
        accountQuery.setAccountIds(members.stream().map(p -> Long.valueOf(p.getAccountId())).collect(Collectors.toList()));
        ListResult<AccountDTO> accountsList = accountFacade.findAccounts(accountQuery);
        List<AccountDTO> accountDTOList = accountsList.getContent();
        List<AppAccountDTO> appAccountDTOList = appMemberAdapter.toDTO(accountDTOList);
        Map<Long, AppAccountDTO> accountDTOMap = Maps.uniqueIndex(appAccountDTOList, AppAccountDTO::getId);
        for (AppMemberDTO appMemberDTO : appMemberDTOList) {
            if (accountDTOMap.get(NumberUtils.toLong(appMemberDTO.getAccountId())) != null) {
                if (accountDTOMap.get(NumberUtils.toLong(appMemberDTO.getAccountId())) != null) {
                    AppAccountDTO accountDTO = accountDTOMap.get(NumberUtils.toLong(appMemberDTO.getAccountId()));
                    appMemberDTO.setAccountDTO(accountDTO);
                }
            }
        }
        return PageUtils.build(appMemberPage, appMemberDTOList);
    }

    @Override
    public Boolean updateMember(UpdateAppMemberCommand updateAppMemberCommand) {
        AppMember appMember = appMemberDomainService.findByMemberId(updateAppMemberCommand.getMemberId());
        appMember.update(updateAppMemberCommand.getRoles(), updateAppMemberCommand.getStatus());
        appMemberDomainService.update(appMember);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteMember(DeleteAppMemberCommand deleteAppMemberCommand) {
        AppMember appMember = appMemberDomainService.findByMemberId(deleteAppMemberCommand.getMemberId());
        appMember.delete();
        appMemberDomainService.update(appMember);
        return Boolean.TRUE;
    }
}
