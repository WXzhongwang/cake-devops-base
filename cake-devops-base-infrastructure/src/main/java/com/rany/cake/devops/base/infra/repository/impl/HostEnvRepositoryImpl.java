package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.HostEnv;
import com.rany.cake.devops.base.domain.repository.HostEnvRepository;
import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.HostEnvDataConvertor;
import com.rany.cake.devops.base.infra.dao.HostEnvDao;
import com.rany.cake.devops.base.infra.mapper.HostEnvPOMapper;
import com.rany.cake.devops.base.infra.po.HostEnvPO;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.toolkit.lang.utils.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class HostEnvRepositoryImpl implements HostEnvRepository {
    private HostEnvPOMapper hostEnvPOMapper;
    private HostEnvDao hostEnvDao;
    private HostEnvDataConvertor hostEnvDataConvertor;

    @Override
    public HostEnv find(Long id) {
        HostEnvPO hostEnvPO = hostEnvPOMapper.selectByPrimaryKey(id);
        return hostEnvDataConvertor.targetToSource(hostEnvPO);
    }

    @Override
    public HostEnv findByKey(String hostId, String key) {
        HostEnvPO hostEnvPO = hostEnvDao.selectByName(key, hostId);
        return hostEnvDataConvertor.targetToSource(hostEnvPO);
    }

    @Override
    public void remove(HostEnv env) {
        HostEnvPO hostEnvPO = hostEnvPOMapper.selectByPrimaryKey(env.getId());
        hostEnvPO.setIsDeleted(DeleteStatusEnum.YES.getValue());
        hostEnvPOMapper.updateByPrimaryKey(hostEnvPO);
    }

    @Override
    public void save(HostEnv env) {
        HostEnvPO hostEnvPO = hostEnvDataConvertor.sourceToTarget(env);
        hostEnvPOMapper.insertSelective(hostEnvPO);
    }

    @Override
    public void update(HostEnv env) {
        HostEnvPO hostEnvPO = hostEnvDataConvertor.sourceToTarget(env);
        hostEnvPOMapper.updateByPrimaryKey(hostEnvPO);
    }

    @Override
    @PagingQuery
    public Page<HostEnv> page(HostEnvQueryParam queryParam) {
        List<HostEnvPO> hostEnvPOS = hostEnvDao.queryHostEnv(queryParam);
        PageInfo<HostEnvPO> pageInfo = new PageInfo<>(hostEnvPOS);
        List<HostEnv> configs = hostEnvDataConvertor.targetToSource(hostEnvPOS);
        return PageUtils.build(pageInfo, configs);
    }

    @Override
    public List<HostEnv> list(HostEnvQueryParam queryParam) {
        List<HostEnvPO> hostEnvPOS = hostEnvDao.queryHostEnv(queryParam);
        return hostEnvDataConvertor.targetToSource(hostEnvPOS);
    }

    @Override
    public void saveEnv(String hostId, Map<String, String> attributes) {
        // 倒排
        List<Map.Entry<String, String>> entries = Lists.newList(attributes.entrySet());
        HostEnvQueryParam hostEnvQueryParam = new HostEnvQueryParam();
        hostEnvQueryParam.setHostId(hostId);
        List<HostEnvPO> hostEnvPOS = hostEnvDao.queryHostEnv(hostEnvQueryParam);

        for (int i = entries.size() - 1; i >= 0; i--) {
            Map.Entry<String, String> entry = entries.get(i);
            HostEnvPO envPO;
            Optional<HostEnvPO> first = hostEnvPOS.stream().filter(p -> p.getAttrKey().equals(entry.getKey())).findFirst();
            envPO = first.orElseGet(HostEnvPO::new);
            envPO.setAttrKey(entry.getKey());
            envPO.setAttrValue(entry.getValue());
            if (envPO.getId() == null) {
                envPO.setHostId(hostId);
                hostEnvPOMapper.insert(envPO);
                continue;
            }
            hostEnvPOMapper.updateByPrimaryKey(envPO);
        }
    }
}
