package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.entity.GroupHost;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.repository.param.HostPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.HostDataConvertor;
import com.rany.cake.devops.base.infra.dao.GroupHostDao;
import com.rany.cake.devops.base.infra.dao.HostDao;
import com.rany.cake.devops.base.infra.mapper.HostPOMapper;
import com.rany.cake.devops.base.infra.po.GroupHostPO;
import com.rany.cake.devops.base.infra.po.HostPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 主机
 *
 * @author zhongshengwang
 * @description 主机
 * @date 2023/1/28 21:02
 * @email 18668485565163.com
 */
@Service
@AllArgsConstructor
public class HostRepositoryImpl implements HostRepository {

    private final HostPOMapper hostPOMapper;
    private final HostDao hostDao;
    private final GroupHostDao groupHostDao;
    private final HostDataConvertor hostDataConvertor;

    @Override
    public Host find(@NotNull HostId hostId) {
        HostPO hostPO = hostDao.selectByHostId(hostId.getHostId());
        return hostDataConvertor.targetToSource(hostPO);
    }

    @Override
    public void remove(@NotNull Host host) {
        HostPO hostPO = hostDataConvertor.sourceToTarget(host);
        hostPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        hostPOMapper.updateByPrimaryKey(hostPO);
    }

    @Override
    public void save(@NotNull Host host) {
        hostDao.save(host);
    }


    public void saveGroupHosts(List<GroupHost> groupHosts) {
        for (GroupHost groupHost : groupHosts) {
            groupHostDao.save(groupHost);
        }
    }

    @Override
    public int update(Host host) {
        return hostDao.update(host);
    }

    @Override
    public List<Host> getHostsByGroupIds(List<String> groupIds) {
        List<GroupHostPO> groupHostPOS = groupHostDao.selectByGroupIds(groupIds);
        List<String> hostIds = groupHostPOS.stream().map(GroupHostPO::getHostId).collect(Collectors.toList());
        List<HostPO> hostPOS = hostDao.selectByPrimaryKeyList(hostIds);
        return hostDataConvertor.targetToSource(hostPOS);
    }

    @Override
    @PagingQuery
    public Page<Host> pageHost(HostPageQueryParam hostPageQueryParam) {
        List<HostPO> hostPOS = hostDao.queryHost(hostPageQueryParam);
        PageInfo<HostPO> pageInfo = new PageInfo<>(hostPOS);
        List<Host> hosts = hostDataConvertor.targetToSource(hostPOS);
        return PageUtils.build(pageInfo, hosts);
    }
}
