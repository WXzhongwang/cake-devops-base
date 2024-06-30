package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.key.CreateServerKeyCommand;
import com.rany.cake.devops.base.api.command.key.DeleteServerKeyCommand;
import com.rany.cake.devops.base.api.command.key.ModifyServerKeyCommand;
import com.rany.cake.devops.base.api.dto.ServerKeyDTO;
import com.rany.cake.devops.base.api.query.key.ServerKeyBasicQuery;
import com.rany.cake.devops.base.api.query.key.ServerKeyPageQuery;
import com.rany.cake.devops.base.api.service.ServerKeyService;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.ServerKey;
import com.rany.cake.devops.base.domain.repository.param.ServerKeyQueryParam;
import com.rany.cake.devops.base.domain.service.ServerKeyDomainService;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.ServerKeyDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 主机秘钥服务
 *
 * @author zhongshengwang
 * @description 主机秘钥服务
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@Slf4j
//@ShenyuService("/server-key/**")
@AllArgsConstructor
public class ServerKeyRemoteService implements ServerKeyService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final ServerKeyDomainService serverKeyDomainService;
    private final ServerKeyDataAdapter serverKeyDataAdapter;

    @Override
    public Long createServerKey(CreateServerKeyCommand command) {
        ServerKey serverKey = new ServerKey();
        serverKey.setAccountType(command.getAccountType());
        serverKey.setDisplayName(command.getDisplayName());
        serverKey.setProtocol(command.getProtocol());
        serverKey.setActive(command.getActive());
        serverKey.setCredential(command.getCredential());
        serverKey.setPassphrase(command.getPassphrase());
        serverKey.setPublicKey(command.getPublicKey());
        serverKey.setKeyPath(command.getKeyPath());
        serverKey.init(command.getUser());
        serverKeyDomainService.save(serverKey);
        return serverKey.getId();
    }

    @Override
    public Boolean modifyServerKey(ModifyServerKeyCommand command) {
        ServerKey serverKey = serverKeyDomainService.getServerKey(command.getId());
        serverKey.setDisplayName(command.getDisplayName());
        serverKey.setAccountType(command.getAccountType());
        serverKey.setPassphrase(command.getPassphrase());
        serverKey.setProtocol(command.getProtocol());
        serverKey.setCredential(command.getCredential());
        serverKey.setPublicKey(command.getPublicKey());
        serverKey.modify(command.getUser());
        serverKeyDomainService.update(serverKey);
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteServerKey(DeleteServerKeyCommand command) {
        ServerKey serverKey = serverKeyDomainService.getServerKey(command.getServerKeyId());
        serverKey.delete(command.getUser());
        serverKeyDomainService.update(serverKey);
        return Boolean.TRUE;
    }

    @Override
    public ServerKeyDTO getServerKey(ServerKeyBasicQuery basicQuery) {
        ServerKey serverKey = serverKeyDomainService.getServerKey(basicQuery.getServerKeyId());
        return serverKeyDataAdapter.sourceToTarget(serverKey);
    }

    @Override
    public Page<ServerKeyDTO> pageServerKey(ServerKeyPageQuery serverKeyPageQuery) {
        ServerKeyQueryParam serverKeyQueryParam = serverKeyDataAdapter.convertParam(serverKeyPageQuery);
        Page<ServerKey> page = serverKeyDomainService.pageServerAccount(serverKeyQueryParam);
        List<ServerKey> serverKeys = new ArrayList<>(page.getItems());
        List<ServerKeyDTO> serverKeyDTOList = serverKeyDataAdapter.sourceToTarget(serverKeys);
        return PageUtils.build(page, serverKeyDTOList);
    }
}
