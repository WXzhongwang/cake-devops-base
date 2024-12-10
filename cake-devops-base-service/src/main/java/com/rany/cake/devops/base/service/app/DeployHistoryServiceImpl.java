package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.rany.cake.devops.base.api.dto.DeployHistoryDTO;
import com.rany.cake.devops.base.api.dto.DeployLogDTO;
import com.rany.cake.devops.base.api.query.release.DeployHistoryPageQuery;
import com.rany.cake.devops.base.api.service.DeployHistoryService;
import com.rany.cake.devops.base.domain.base.AppConfig;
import com.rany.cake.devops.base.domain.entity.DeployHistory;
import com.rany.cake.devops.base.domain.repository.DeployHistoryRepository;
import com.rany.cake.devops.base.domain.repository.param.DeployHistoryPageParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.DeployHistoryDataAdapter;
import com.rany.cake.devops.base.service.integration.sls.SlsDeployLogQueryService;
import com.rany.ops.api.facade.account.AccountFacade;
import com.rany.ops.api.query.account.AccountQuery;
import com.rany.ops.common.dto.account.AccountDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhongshengwang
 */
@AllArgsConstructor
@Service
public class DeployHistoryServiceImpl implements DeployHistoryService {

    private final SlsDeployLogQueryService slsDeployLogQueryService;
    private final DeployHistoryRepository deployHistoryRepository;
    private final DeployHistoryDataAdapter deployHistoryDataAdapter;

    private final AppConfig appConfig;
    private final AccountFacade accountFacade;

    @Override
    public List<DeployLogDTO> queryDeployLog(String pipeKey, long from, long to) {
        return slsDeployLogQueryService.queryLogs(pipeKey, from, to);
    }

    @Override
    public Page<DeployHistoryDTO> queryDeployHistory(DeployHistoryPageQuery deployHistoryPageQuery) {
        DeployHistoryPageParam deployHistoryPageParam = deployHistoryDataAdapter.convertParam(deployHistoryPageQuery);
        Page<DeployHistory> deployHistoryPage = deployHistoryRepository.pageQuery(deployHistoryPageParam);
        List<DeployHistoryDTO> deployHistoryDTOS = deployHistoryDataAdapter.sourceToTarget(new ArrayList<>(deployHistoryPage.getItems()));
        List<Long> creators = deployHistoryDTOS.stream().map(p -> Long.valueOf(p.getCreator())).collect(Collectors.toList());

        AccountQuery accountQuery = new AccountQuery();
        accountQuery.setTenantId(appConfig.getTenantId());
        accountQuery.setAccountIds(creators);
        List<AccountDTO> accountDTOList = accountFacade.findAccounts(accountQuery);
        ImmutableMap<String, AccountDTO> idMap = Maps.uniqueIndex(accountDTOList, AccountDTO::getId);
        for (DeployHistoryDTO deployHistoryDTO : deployHistoryDTOS) {
            AccountDTO accountDTO = idMap.get(deployHistoryDTO.getCreator());
            if (accountDTO != null) {
                deployHistoryDTO.setCreatorName(accountDTO.getAccountName());
            }
        }
        return PageUtils.build(deployHistoryPage, deployHistoryDTOS);
    }
}
