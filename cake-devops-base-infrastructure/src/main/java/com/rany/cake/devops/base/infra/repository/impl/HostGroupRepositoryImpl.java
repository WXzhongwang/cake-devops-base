package com.rany.cake.devops.base.infra.repository.impl;

import com.rany.cake.devops.base.domain.aggregate.HostGroup;
import com.rany.cake.devops.base.domain.pk.HostGroupId;
import com.rany.cake.devops.base.domain.repository.HostGroupRepository;
import com.rany.cake.devops.base.infra.convertor.HostGroupDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostGroupDao;
import com.rany.cake.devops.base.infra.mapper.HostGroupPOMapper;
import com.rany.cake.devops.base.infra.po.HostGroupPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 主机组
 *
 * @author zhongshengwang
 * @description 主机组
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
@AllArgsConstructor
public class HostGroupRepositoryImpl implements HostGroupRepository {

    private final HostGroupPOMapper hostGroupPOMapper;
    private final HostGroupDao hostGroupDao;
    private final HostGroupDataConvertor hostGroupDataConvertor;

    @Override
    public HostGroup find(@NotNull HostGroupId hostId) {
        HostGroupPO hostPO = hostGroupDao.selectByHostGroupId(hostId.getHostGroupId());
        return hostGroupDataConvertor.targetToSource(hostPO);
    }

    @Override
    public void remove(@NotNull HostGroup hostGroup) {
        HostGroupPO hostPO = hostGroupDataConvertor.sourceToTarget(hostGroup);
        hostPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        hostGroupPOMapper.updateByPrimaryKey(hostPO);
    }

    @Override
    public void save(@NotNull HostGroup host) {
        hostGroupDao.save(host);
    }

    @Override
    public int update(HostGroup hostGroup) {
        return hostGroupDao.update(hostGroup);
    }

    @Override
    public HostGroup getPackagingGroup() {
        HostGroupPO packagingGroup = hostGroupDao.getPackagingGroup();
        return hostGroupDataConvertor.targetToSource(packagingGroup);
    }

    @Override
    public List<HostGroup> listAll() {
        List<HostGroupPO> groupPOS = hostGroupDao.listAll();
        return hostGroupDataConvertor.targetToSource(groupPOS);
    }
}
