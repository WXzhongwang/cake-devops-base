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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@ShenyuService("/cluster/**")
@Slf4j
@AllArgsConstructor
public class HostEnvRemoteService implements HostEnvService {
    private HostEnvRepository hostEnvRepository;
    private HostEnvDataAdapter hostEnvDataAdapter;

    @Override
    public PojoResult<String> createHostEnv(CreateHostEnvCommand command) {
        HostEnv env = new HostEnv();
        env.setMachineId(command.getHostId());
        env.setAttrKey(command.getAttrKey());
        env.setAttrValue(command.getAttrValue());
        hostEnvRepository.save(env);
        return PojoResult.succeed(env.getId().toString());
    }

    @Override
    public PojoResult<Boolean> modifyHostEnv(ModifyHostEnvCommand command) {
        HostEnv env = hostEnvRepository.find(Long.valueOf(command.getEnvId()));
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.MACHINE_ENV_NOT_FOUND);
        }
        env.setMachineId(command.getEnvId());
        env.setAttrKey(command.getAttrKey());
        env.setAttrValue(command.getAttrValue());
        hostEnvRepository.update(env);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deleteHostEnv(DeleteHostEnvCommand command) {
        HostEnv env = hostEnvRepository.find(Long.valueOf(command.getEnvId()));
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.MACHINE_ENV_NOT_FOUND);
        }
        hostEnvRepository.remove(env);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<HostEnvDTO> getHostEnv(HostEnvBasicQuery query) {
        HostEnv env = hostEnvRepository.find(Long.valueOf(query.getEnvId()));
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
        return null;
    }

    @Override
    public PojoResult<String> view(HostEnvViewQuery query) {
        return null;
    }

    @Override
    public PojoResult<String> saveView(HostEnvViewSaveCommand command) {
        return null;
    }
}
