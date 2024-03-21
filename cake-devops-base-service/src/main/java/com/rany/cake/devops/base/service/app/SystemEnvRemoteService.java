package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.system.CreateSystemEnvCommand;
import com.rany.cake.devops.base.api.command.system.DeleteSystemEnvCommand;
import com.rany.cake.devops.base.api.command.system.ModifySystemEnvCommand;
import com.rany.cake.devops.base.api.dto.SystemEnvDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.SystemEnvBasicQuery;
import com.rany.cake.devops.base.api.query.SystemEnvPageQuery;
import com.rany.cake.devops.base.api.service.SystemEnvService;
import com.rany.cake.devops.base.domain.entity.SystemEnv;
import com.rany.cake.devops.base.domain.repository.SystemEnvRepository;
import com.rany.cake.devops.base.domain.repository.param.SystemEnvPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.SystemEnvDataAdapter;
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
public class SystemEnvRemoteService implements SystemEnvService {
    private SystemEnvRepository hostEnvRepository;
    private SystemEnvDataAdapter hostEnvDataAdapter;

    @Override
    public PojoResult<String> createSystemEnv(CreateSystemEnvCommand command) {
        SystemEnv env = new SystemEnv();
        env.setAttrKey(command.getAttrKey());
        env.setAttrValue(command.getAttrValue());
        env.setDescription(command.getDescription());
        hostEnvRepository.save(env);
        return PojoResult.succeed(env.getId().toString());
    }

    @Override
    public PojoResult<Void> saveEnv(Map<String, String> envMap) {
        List<SystemEnv> systemEnvLIst = new ArrayList<>();
        for (Map.Entry<String, String> entry : envMap.entrySet()) {
            SystemEnv env = new SystemEnv();
            env.setAttrKey(entry.getKey());
            env.setAttrValue(entry.getValue());
            env.setDescription(entry.getKey());
            systemEnvLIst.add(env);
        }
        hostEnvRepository.batchSave(systemEnvLIst);
        return PojoResult.succeed();
    }

    @Override
    public PojoResult<Boolean> modifySystemEnv(ModifySystemEnvCommand command) {
        SystemEnv env = hostEnvRepository.find(command.getId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.MACHINE_ENV_NOT_FOUND);
        }
        env.setAttrKey(command.getAttrKey());
        env.setAttrValue(command.getAttrValue());
        env.setDescription(command.getDescription());
        hostEnvRepository.update(env);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deleteSystemEnv(DeleteSystemEnvCommand command) {
        SystemEnv env = hostEnvRepository.find(command.getId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.SYSTEM_ENV_NOT_FOUND);
        }
        hostEnvRepository.remove(env);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<SystemEnvDTO> getSystemEnv(SystemEnvBasicQuery query) {
        SystemEnv env = hostEnvRepository.find(query.getId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.SYSTEM_ENV_NOT_FOUND);
        }
        return PojoResult.succeed(hostEnvDataAdapter.sourceToTarget(env));
    }

    @Override
    public PageResult<SystemEnvDTO> pageSystemEnv(SystemEnvPageQuery query) {
        SystemEnvPageQueryParam hostEnvQueryParam = hostEnvDataAdapter.convertParam(query);
        Page<SystemEnv> page = hostEnvRepository.page(hostEnvQueryParam);
        Collection<SystemEnv> items = page.getItems();
        List<SystemEnvDTO> systemEnvDTOList = hostEnvDataAdapter.sourceToTarget(new ArrayList<>(items));
        Page<SystemEnvDTO> build = PageUtils.build(page, systemEnvDTOList);
        return PageResult.succeed(build);
    }
}
