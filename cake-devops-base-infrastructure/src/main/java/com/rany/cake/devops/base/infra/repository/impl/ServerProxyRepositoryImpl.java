package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.aggregate.ServerProxy;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.ServerProxyId;
import com.rany.cake.devops.base.domain.repository.ServerProxyRepository;
import com.rany.cake.devops.base.domain.repository.param.ServerProxyQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.ServerProxyDataConvertor;
import com.rany.cake.devops.base.infra.dao.ServerProxyDao;
import com.rany.cake.devops.base.infra.mapper.ServerProxyPOMapper;
import com.rany.cake.devops.base.infra.po.ServerProxyPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ServerProxyRepositoryImpl implements ServerProxyRepository {
    private final ServerProxyPOMapper serverProxyPOMapper;
    private final ServerProxyDao serverProxyDao;
    private final ServerProxyDataConvertor serverProxyDataConvertor;


    @Override
    public int update(ServerProxy serverKey) {
        return serverProxyDao.update(serverKey);
    }

    @Override
    @PagingQuery
    public Page<ServerProxy> pageServerProxy(ServerProxyQueryParam serverProxyQueryParam) {
        List<ServerProxyPO> serverProxyPOS = serverProxyDao.queryServerProxy(serverProxyQueryParam);
        PageInfo<ServerProxyPO> pageInfo = new PageInfo<>(serverProxyPOS);
        List<ServerProxy> serverProxies = serverProxyDataConvertor.targetToSource(serverProxyPOS);
        return PageUtils.build(pageInfo, serverProxies);
    }

    @Override
    public ServerProxy find(@NotNull ServerProxyId serverProxyId) {
        ServerProxyPO serverProxyPO = serverProxyDao.selectByServerProxyId(serverProxyId.getServerProxyId());
        return serverProxyDataConvertor.targetToSource(serverProxyPO);
    }

    @Override
    public void remove(@NotNull ServerProxy serverProxy) {
        serverProxy.setIsDeleted(DeleteStatusEnum.YES.getValue());
        serverProxyDao.update(serverProxy);
    }

    @Override
    public void save(@NotNull ServerProxy serverProxy) {
        serverProxyDao.save(serverProxy);
    }
}
