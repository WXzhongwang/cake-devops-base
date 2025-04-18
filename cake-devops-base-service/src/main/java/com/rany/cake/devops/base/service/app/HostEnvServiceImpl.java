package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.host.env.*;
import com.rany.cake.devops.base.api.dto.HostEnvDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.host.env.HostEnvBasicQuery;
import com.rany.cake.devops.base.api.query.host.env.HostEnvPageQuery;
import com.rany.cake.devops.base.api.query.host.env.HostEnvViewQuery;
import com.rany.cake.devops.base.api.service.HostEnvService;
import com.rany.cake.devops.base.domain.entity.HostEnv;
import com.rany.cake.devops.base.domain.repository.HostEnvRepository;
import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.HostEnvDataAdapter;
import com.rany.cake.devops.base.util.EnvConst;
import com.rany.cake.devops.base.util.EnvViewType;
import com.rany.cake.toolkit.lang.collect.MutableLinkedHashMap;
import com.rany.cake.toolkit.lang.utils.Maps;
import com.rany.cake.toolkit.lang.utils.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhongshengwang
 */
@Service
@Slf4j
@AllArgsConstructor
public class HostEnvServiceImpl implements HostEnvService {
    private final HostEnvRepository hostEnvRepository;
    private final HostEnvDataAdapter hostEnvDataAdapter;

    @Override
    public String createHostEnv(CreateHostEnvCommand command) {
        HostEnv env = new HostEnv();
        env.setHostId(command.getHostId());
        env.setAttrKey(command.getAttrKey());
        env.setAttrValue(command.getAttrValue());
        env.setDescription(command.getDescription());
        env.init(command.getUser());
        hostEnvRepository.save(env);
        return env.getId().toString();
    }

    @Override
    public Boolean modifyHostEnv(ModifyHostEnvCommand command) {
        HostEnv env = hostEnvRepository.find(command.getId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.MACHINE_ENV_NOT_FOUND);
        }
        env.setAttrKey(command.getAttrKey());
        env.setAttrValue(command.getAttrValue());
        env.setDescription(command.getDescription());
        env.modify(command.getUser());
        hostEnvRepository.update(env);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteHostEnv(DeleteHostEnvCommand command) {
        HostEnv env = hostEnvRepository.find(command.getEnvId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.MACHINE_ENV_NOT_FOUND);
        }
        env.delete(command.getUser());
        hostEnvRepository.remove(env);
        return Boolean.TRUE;
    }

    @Override
    public HostEnvDTO getHostEnv(HostEnvBasicQuery query) {
        HostEnv env = hostEnvRepository.find(query.getEnvId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.MACHINE_ENV_NOT_FOUND);
        }
        return hostEnvDataAdapter.sourceToTarget(env);
    }

    @Override
    public Page<HostEnvDTO> pageHostEnv(HostEnvPageQuery pageQuery) {
        HostEnvQueryParam hostEnvQueryParam = hostEnvDataAdapter.convertParam(pageQuery);
        Page<HostEnv> page = hostEnvRepository.page(hostEnvQueryParam);
        Collection<HostEnv> items = page.getItems();
        List<HostEnvDTO> webHookConfigDTOS = hostEnvDataAdapter.sourceToTarget(new ArrayList<>(items));
        return PageUtils.build(page, webHookConfigDTOS);
    }

    @Override
    public Boolean asyncHostEnv(AsyncHostEnvCommand command) {
        // 查询列表
        Map<String, String> env = Maps.newLinkedMap();
        HostEnvQueryParam queryParam = new HostEnvQueryParam();
        queryParam.setHostId(command.getHostId());
        List<HostEnv> list = hostEnvRepository.list(queryParam);
        list.forEach(e -> env.put(e.getAttrKey(), e.getAttrValue()));
        // 逐个复制
        for (String host : command.getTargetHostIdList()) {
            hostEnvRepository.saveEnv(host, env);
        }
        return Boolean.TRUE;
    }

    @Override
    public String view(HostEnvViewQuery query) {
        EnvViewType viewType = Valid.notNull(EnvViewType.of(query.getViewType()));
        // 查询列表
        Map<String, String> env = Maps.newLinkedMap();
        HostEnvQueryParam queryParam = new HostEnvQueryParam();
        queryParam.setHostId(query.getHostId());
        List<HostEnv> list = hostEnvRepository.list(queryParam);
        list.forEach(e -> env.put(e.getAttrKey(), e.getAttrValue()));
        return viewType.toValue(env);
    }

    @Override
    public LinkedHashMap<String, String> getMachineEnv(String hostId) {
        LinkedHashMap<String, String> envMap = new LinkedHashMap<>();
        HostEnvQueryParam queryParam = new HostEnvQueryParam();
        queryParam.setHostId(hostId);
        List<HostEnv> hostEnvList = hostEnvRepository.list(queryParam);
        hostEnvList.forEach(e -> envMap.put(EnvConst.MACHINE_PREFIX + e.getAttrKey(), e.getAttrValue()));
        return envMap;
    }

    @Override
    public String saveView(HostEnvViewSaveCommand command) {
        String value = Valid.notBlank(command.getHostId());
        EnvViewType viewType = Valid.notNull(EnvViewType.of(command.getViewType()));
        MutableLinkedHashMap<String, String> result = viewType.toMap(value);
        hostEnvRepository.saveEnv(command.getHostId(), result);
        return value;
    }
}
