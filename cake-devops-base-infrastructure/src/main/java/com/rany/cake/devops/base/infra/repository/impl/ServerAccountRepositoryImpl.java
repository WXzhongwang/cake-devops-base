package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.aggregate.ServerAccount;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.ServerAccountId;
import com.rany.cake.devops.base.domain.repository.ServerAccountRepository;
import com.rany.cake.devops.base.domain.repository.param.ServerAccountQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.ServerAccountDataConvertor;
import com.rany.cake.devops.base.infra.dao.ServerAccountDao;
import com.rany.cake.devops.base.infra.mapper.ServerAccountPOMapper;
import com.rany.cake.devops.base.infra.po.ServerAccountPO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ServerAccountRepositoryImpl implements ServerAccountRepository {
    private final ServerAccountPOMapper serverAccountPOMapper;
    private final ServerAccountDao serverAccountDao;
    private final ServerAccountDataConvertor serverAccountDataConvertor;

    @Override
    public int update(ServerAccount serverAccount) {
        return serverAccountDao.update(serverAccount);
    }

    @Override
    public ServerAccount find(@NotNull ServerAccountId serverAccountId) {
        ServerAccountPO serverAccountPO = serverAccountDao.selectByServerAccountId(serverAccountId.getServerAccountId());
        return serverAccountDataConvertor.targetToSource(serverAccountPO);
    }

    @Override
    public void remove(@NotNull ServerAccount serverAccount) {
        serverAccount.setIsDeleted(DeleteStatusEnum.YES.getValue());
        serverAccountDao.update(serverAccount);
    }

    @Override
    public void save(@NotNull ServerAccount serverAccount) {
        serverAccountDao.save(serverAccount);
    }

    @PagingQuery
    public Page<ServerAccount> pageServerAccount(ServerAccountQueryParam serverAccountQueryParam) {
        List<ServerAccountPO> appPOS = serverAccountDao.queryServerAccount(serverAccountQueryParam);
        PageInfo<ServerAccountPO> pageInfo = new PageInfo<>(appPOS);
        List<ServerAccount> apps = serverAccountDataConvertor.targetToSource(appPOS);
        return PageUtils.build(pageInfo, apps);
    }
}
