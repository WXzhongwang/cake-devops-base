package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.ServerKey;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.repository.ServerKeyRepository;
import com.rany.cake.devops.base.domain.repository.param.ServerKeyQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.ServerKeyDataConvertor;
import com.rany.cake.devops.base.infra.dao.ServerKeyDao;
import com.rany.cake.devops.base.infra.mapper.ServerKeyPOMapper;
import com.rany.cake.devops.base.infra.po.ServerKeyPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ServerKeyRepositoryImpl implements ServerKeyRepository {
    private final ServerKeyPOMapper serverKeyPOMapper;
    private final ServerKeyDao serverKeyDao;
    private final ServerKeyDataConvertor serverKeyDataConvertor;

    @Override
    public int update(ServerKey serverKey) {
        return serverKeyDao.update(serverKey);
    }

    @Override
    public ServerKey find(@NotNull Long serverKeyId) {
        ServerKeyPO serverKeyPO = serverKeyDao.selectByServerKeyId(serverKeyId);
        return serverKeyDataConvertor.targetToSource(serverKeyPO);
    }

    @Override
    public void remove(@NotNull ServerKey serverKey) {
        serverKey.setIsDeleted(DeleteStatusEnum.YES.getValue());
        serverKeyDao.update(serverKey);
    }

    @Override
    public void save(@NotNull ServerKey serverKey) {
        serverKeyDao.save(serverKey);
    }

    @PagingQuery
    public Page<ServerKey> pageServerKey(ServerKeyQueryParam serverKeyQueryParam) {
        List<ServerKeyPO> appPOS = serverKeyDao.queryServerKey(serverKeyQueryParam);
        PageInfo<ServerKeyPO> pageInfo = new PageInfo<>(appPOS);
        List<ServerKey> apps = serverKeyDataConvertor.targetToSource(appPOS);
        return PageUtils.build(pageInfo, apps);
    }
}
