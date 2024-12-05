package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.system.CreateSystemEnvCommand;
import com.rany.cake.devops.base.api.command.system.DeleteSystemEnvCommand;
import com.rany.cake.devops.base.api.command.system.ModifySystemEnvCommand;
import com.rany.cake.devops.base.api.dto.SystemEnvDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.system.SystemEnvBasicQuery;
import com.rany.cake.devops.base.api.query.system.SystemEnvPageQuery;
import com.rany.cake.devops.base.api.service.SystemEnvService;
import com.rany.cake.devops.base.domain.entity.SystemEnv;
import com.rany.cake.devops.base.domain.repository.SystemEnvRepository;
import com.rany.cake.devops.base.domain.repository.param.SystemEnvPageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.SystemEnvDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author zhongshengwang
 */
@Service
@Slf4j
@AllArgsConstructor
public class SystemEnvServiceImpl implements SystemEnvService {
    private SystemEnvRepository systemEnvRepository;
    private SystemEnvDataAdapter systemEnvDataAdapter;

    @Override
    public String createSystemEnv(CreateSystemEnvCommand command) {
        SystemEnv env = new SystemEnv();
        env.setAttrKey(command.getAttrKey());
        env.setAttrValue(command.getAttrValue());
        env.setDescription(command.getDescription());
        env.init(command.getUser());
        systemEnvRepository.save(env);
        return env.getId().toString();
    }

    @Override
    public void saveEnv(Map<String, String> envMap) {
        List<SystemEnv> systemEnvList = new ArrayList<>();
        for (Map.Entry<String, String> entry : envMap.entrySet()) {
            SystemEnv env = new SystemEnv();
            env.setAttrKey(entry.getKey());
            env.setAttrValue(entry.getValue());
            env.setDescription(entry.getKey());
            systemEnvList.add(env);
        }
        systemEnvRepository.batchSave(systemEnvList);
    }

    @Override
    public Boolean modifySystemEnv(ModifySystemEnvCommand command) {
        SystemEnv env = systemEnvRepository.find(command.getId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.MACHINE_ENV_NOT_FOUND);
        }
        env.setAttrKey(command.getAttrKey());
        env.setAttrValue(command.getAttrValue());
        env.setDescription(command.getDescription());
        systemEnvRepository.update(env);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteSystemEnv(DeleteSystemEnvCommand command) {
        SystemEnv env = systemEnvRepository.find(command.getId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.SYSTEM_ENV_NOT_FOUND);
        }
        systemEnvRepository.remove(env);
        return Boolean.TRUE;
    }

    @Override
    public SystemEnvDTO getSystemEnv(SystemEnvBasicQuery query) {
        SystemEnv env = systemEnvRepository.find(query.getId());
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.SYSTEM_ENV_NOT_FOUND);
        }
        return systemEnvDataAdapter.sourceToTarget(env);
    }

    @Override
    public SystemEnvDTO getSystemEnv(String envName) {
        SystemEnv env = systemEnvRepository.findByName(envName);
        if (env == null) {
            throw new DevOpsException(DevOpsErrorMessage.SYSTEM_ENV_NOT_FOUND);
        }
        return systemEnvDataAdapter.sourceToTarget(env);
    }

    @Override
    public Page<SystemEnvDTO> pageSystemEnv(SystemEnvPageQuery query) {
        SystemEnvPageQueryParam hostEnvQueryParam = systemEnvDataAdapter.convertParam(query);
        Page<SystemEnv> page = systemEnvRepository.page(hostEnvQueryParam);
        Collection<SystemEnv> items = page.getItems();
        List<SystemEnvDTO> systemEnvDTOList = systemEnvDataAdapter.sourceToTarget(new ArrayList<>(items));
        return PageUtils.build(page, systemEnvDTOList);
    }
}
