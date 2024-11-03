package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.dto.DeployHistoryDTO;
import com.rany.cake.devops.base.api.dto.DeployLogDTO;
import com.rany.cake.devops.base.api.query.release.DeployHistoryPageQuery;
import com.rany.cake.devops.base.api.service.DeployHistoryService;
import com.rany.cake.devops.base.domain.entity.DeployHistory;
import com.rany.cake.devops.base.domain.repository.DeployHistoryRepository;
import com.rany.cake.devops.base.domain.repository.param.DeployHistoryPageParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.DeployHistoryDataAdapter;
import com.rany.cake.devops.base.service.sls.SlsDeployLogQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DeployHistoryRemoteService implements DeployHistoryService {

    private final SlsDeployLogQueryService slsDeployLogQueryService;
    private final DeployHistoryRepository deployHistoryRepository;
    private final DeployHistoryDataAdapter deployHistoryDataAdapter;

    @Override
    public List<DeployLogDTO> queryDeployLog(String pipeKey, long from, long to) {
        return slsDeployLogQueryService.queryLogs(pipeKey, from, to);
    }

    @Override
    public Page<DeployHistoryDTO> queryDeployHistory(DeployHistoryPageQuery deployHistoryPageQuery) {
        DeployHistoryPageParam deployHistoryPageParam = deployHistoryDataAdapter.convertParam(deployHistoryPageQuery);
        Page<DeployHistory> deployHistoryPage = deployHistoryRepository.pageQuery(deployHistoryPageParam);
        List<DeployHistoryDTO> deployHistoryDTOS = deployHistoryDataAdapter.sourceToTarget(new ArrayList<>(deployHistoryPage.getItems()));
        return PageUtils.build(deployHistoryPage, deployHistoryDTOS);
    }
}
