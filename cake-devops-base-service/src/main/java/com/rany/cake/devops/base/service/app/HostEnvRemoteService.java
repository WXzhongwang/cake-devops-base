package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.host.env.*;
import com.rany.cake.devops.base.api.dto.HostEnvDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.HostEnvBasicQuery;
import com.rany.cake.devops.base.api.query.HostEnvPageQuery;
import com.rany.cake.devops.base.api.query.HostEnvViewQuery;
import com.rany.cake.devops.base.api.service.HostEnvService;
import com.rany.cake.devops.base.domain.entity.HostEnv;
import com.rany.cake.devops.base.domain.repository.HostEnvRepository;
import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.HostEnvDataAdapter;
import com.rany.cake.devops.base.util.EnvViewType;
import com.rany.cake.toolkit.lang.collect.MutableLinkedHashMap;
import com.rany.cake.toolkit.lang.utils.Maps;
import com.rany.cake.toolkit.lang.utils.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@ShenyuService("/host-env/**")
@Slf4j
@AllArgsConstructor
public class HostEnvRemoteService implements HostEnvService {
    private HostEnvRepository hostEnvRepository;
    private HostEnvDataAdapter hostEnvDataAdapter;

    @Override
    public PojoResult<String> createHostEnv(CreateHostEnvCommand command) {
        HostEnv env = new HostEnv();
        env.setHostId(command.getHostId());
        env.setAttrKey(command.getAttrKey());
        env.setAttrValue(command.getAttrValue());
        hostEnvRepository.save(env);
        return PojoResult.succeed(env.getId().toString());
    }

    @Override
    public PojoResult<Boolean> modifyHostEnv(ModifyHostEnvCommand command) {
        HostEnv env = hostEnvRepository.find(command.getId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.MACHINE_ENV_NOT_FOUND);
        }
        env.setHostId(command.getHostId());
        env.setAttrKey(command.getAttrKey());
        env.setAttrValue(command.getAttrValue());
        hostEnvRepository.update(env);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deleteHostEnv(DeleteHostEnvCommand command) {
        HostEnv env = hostEnvRepository.find(command.getEnvId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.MACHINE_ENV_NOT_FOUND);
        }
        hostEnvRepository.remove(env);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<HostEnvDTO> getHostEnv(HostEnvBasicQuery query) {
        HostEnv env = hostEnvRepository.find(query.getEnvId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.MACHINE_ENV_NOT_FOUND);
        }
        return PojoResult.succeed(hostEnvDataAdapter.sourceToTarget(env));
    }

    @Override
    public PageResult<HostEnvDTO> pageHostEnv(HostEnvPageQuery pageQuery) {
        HostEnvQueryParam hostEnvQueryParam = hostEnvDataAdapter.convertParam(pageQuery);
        Page<HostEnv> page = hostEnvRepository.page(hostEnvQueryParam);
        Collection<HostEnv> items = page.getItems();
        List<HostEnvDTO> webHookConfigDTOS = hostEnvDataAdapter.sourceToTarget(new ArrayList<>(items));
        Page<HostEnvDTO> build = PageUtils.build(page, webHookConfigDTOS);
        return PageResult.succeed(build);
    }

    @Override
    public PojoResult<Boolean> asyncHostEnv(AsyncHostEnvCommand command) {
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
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<String> view(HostEnvViewQuery query) {
        EnvViewType viewType = Valid.notNull(EnvViewType.of(query.getViewType()));
        // 查询列表
        Map<String, String> env = Maps.newLinkedMap();
        HostEnvQueryParam queryParam = new HostEnvQueryParam();
        queryParam.setHostId(query.getHostId());
        List<HostEnv> list = hostEnvRepository.list(queryParam);
        list.forEach(e -> env.put(e.getAttrKey(), e.getAttrValue()));
        String value = viewType.toValue(env);
        return PojoResult.succeed(value);
    }

    @Override
    public PojoResult<String> saveView(HostEnvViewSaveCommand command) {
        String value = Valid.notBlank(command.getHostId());
        EnvViewType viewType = Valid.notNull(EnvViewType.of(command.getViewType()));
        MutableLinkedHashMap<String, String> result = viewType.toMap(value);
        hostEnvRepository.saveEnv(command.getHostId(), result);
        return PojoResult.succeed(value);
    }
}
