package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.proxy.CreateServerProxyCommand;
import com.rany.cake.devops.base.api.command.proxy.DeleteServerProxyCommand;
import com.rany.cake.devops.base.api.command.proxy.ModifyServerProxyCommand;
import com.rany.cake.devops.base.api.dto.ServerProxyDTO;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.query.ServerProxyBasicQuery;
import com.rany.cake.devops.base.api.query.ServerProxyPageQuery;
import com.rany.cake.devops.base.api.service.ServerProxyService;
import com.rany.cake.devops.base.domain.entity.ServerProxy;
import com.rany.cake.devops.base.domain.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.domain.repository.ServerProxyRepository;
import com.rany.cake.devops.base.domain.repository.param.ServerProxyQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.ServerProxyDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 主机秘钥服务
 *
 * @author zhongshengwang
 * @description 主机代理服务
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@Slf4j
@ShenyuService("/server-proxy/**")
@AllArgsConstructor
public class ServerProxyRemoteService implements ServerProxyService {

    private ServerProxyRepository serverProxyRepository;
    private ServerProxyDataAdapter serverProxyDataAdapter;

    @Override
    public PojoResult<String> createServerProxy(CreateServerProxyCommand command) {
        ServerProxy serverProxy = new ServerProxy();
        serverProxy.setProxyHost(command.getProxyHost());
        serverProxy.setProxyPort(command.getProxyPort());
        serverProxy.setProxyType(command.getProxyType());
        serverProxy.setProxyUsername(command.getProxyUsername());
        serverProxy.setProxyPassword(command.getProxyPassword());
        serverProxyRepository.save(serverProxy);
        return PojoResult.succeed(serverProxy.getId().toString());
    }

    @Override
    public PojoResult<Boolean> modifyServerProxy(ModifyServerProxyCommand command) {
        ServerProxy serverProxy = serverProxyRepository.find(command.getServerProxyId());
        if (serverProxy == null) {
            throw new DevOpsException(DevOpsErrorMessage.PROXY_NOT_FOUND);
        }
        serverProxy.setProxyHost(command.getProxyHost());
        serverProxy.setProxyPort(command.getProxyPort());
        serverProxy.setProxyType(command.getProxyType());
        serverProxy.setProxyUsername(command.getProxyUsername());
        serverProxy.setProxyPassword(command.getProxyPassword());
        serverProxyRepository.update(serverProxy);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deleteServerProxy(DeleteServerProxyCommand command) {
        ServerProxy serverProxy = serverProxyRepository.find(command.getServerProxyId());
        if (serverProxy == null) {
            throw new DevOpsException(DevOpsErrorMessage.PROXY_NOT_FOUND);
        }
        serverProxy.setIsDeleted(DeleteStatusEnum.YES.getValue());
        serverProxyRepository.update(serverProxy);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<ServerProxyDTO> getServerProxy(ServerProxyBasicQuery basicQuery) {
        ServerProxy serverProxy = serverProxyRepository.find(basicQuery.getServerProxyId());
        if (serverProxy == null) {
            throw new DevOpsException(DevOpsErrorMessage.PROXY_NOT_FOUND);
        }
        ServerProxyDTO serverProxyDTO = serverProxyDataAdapter.sourceToTarget(serverProxy);
        return PojoResult.succeed(serverProxyDTO);
    }

    @Override
    public PageResult<ServerProxyDTO> pageServerProxy(ServerProxyPageQuery pageQuery) {
        ServerProxyQueryParam serverProxyQueryParam = serverProxyDataAdapter.convertParam(pageQuery);
        Page<ServerProxy> page = serverProxyRepository.pageServerProxy(serverProxyQueryParam);
        Collection<ServerProxy> items = page.getItems();
        List<ServerProxyDTO> serverProxyDTOList = serverProxyDataAdapter.sourceToTarget(new ArrayList<>(items));
        Page<ServerProxyDTO> build = PageUtils.build(page, serverProxyDTOList);
        return PageResult.succeed(build);
    }
}
