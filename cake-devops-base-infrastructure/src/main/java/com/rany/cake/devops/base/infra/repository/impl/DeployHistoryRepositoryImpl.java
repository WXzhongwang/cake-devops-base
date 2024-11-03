package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.DeployHistory;
import com.rany.cake.devops.base.domain.repository.DeployHistoryRepository;
import com.rany.cake.devops.base.domain.repository.param.DeployHistoryPageParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.DeployHistoryConvertor;
import com.rany.cake.devops.base.infra.dao.DeployHistoryDao;
import com.rany.cake.devops.base.infra.mapper.DeployHistoryPOMapper;
import com.rany.cake.devops.base.infra.po.DeployHistoryPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DeployHistoryRepositoryImpl implements DeployHistoryRepository {

    private final DeployHistoryPOMapper deployHistoryPOMapper;
    private final DeployHistoryDao deployHistoryDao;
    private final DeployHistoryConvertor deployHistoryConvertor;

    @Override
    public DeployHistory find(Long id) {
        DeployHistoryPO deployHistoryPO = deployHistoryPOMapper.selectByPrimaryKey(id);
        return deployHistoryConvertor.targetToSource(deployHistoryPO);
    }

    @Override
    public void save(DeployHistory log) {
        DeployHistoryPO deployHistoryPO = deployHistoryConvertor.sourceToTarget(log);
        deployHistoryPOMapper.insertSelective(deployHistoryPO);
    }

    @Override
    public int update(DeployHistory log) {
        DeployHistoryPO deployHistoryPO = deployHistoryConvertor.sourceToTarget(log);
        return deployHistoryPOMapper.updateByPrimaryKey(deployHistoryPO);
    }

    @Override
    @PagingQuery
    public Page<DeployHistory> pageQuery(DeployHistoryPageParam param) {
        List<DeployHistoryPO> deployHistoryList = deployHistoryDao.pageQuery(param);
        PageInfo<DeployHistoryPO> pageInfo = new PageInfo<>(deployHistoryList);
        List<DeployHistory> deployHistories = deployHistoryConvertor.targetToSource(deployHistoryList);
        return PageUtils.build(pageInfo, deployHistories);
    }
}
