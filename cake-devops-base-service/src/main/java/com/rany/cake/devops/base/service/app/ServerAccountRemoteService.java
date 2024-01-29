package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.account.CreateServerAccountCommand;
import com.rany.cake.devops.base.api.command.account.DeleteServerAccountCommand;
import com.rany.cake.devops.base.api.command.account.ModifyServerAccountCommand;
import com.rany.cake.devops.base.api.dto.ServerAccountDTO;
import com.rany.cake.devops.base.api.query.ServerAccountBasicQuery;
import com.rany.cake.devops.base.api.query.ServerAccountPageQuery;
import com.rany.cake.devops.base.api.service.ServerAccountService;
import com.rany.cake.devops.base.domain.aggregate.ServerAccount;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.pk.ServerAccountId;
import com.rany.cake.devops.base.domain.repository.param.ServerAccountQueryParam;
import com.rany.cake.devops.base.domain.service.ServerAccountDomainService;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.ServerAccountDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.List;

/**
 * 主机账号服务
 *
 * @author zhongshengwang
 * @description 主机账号服务
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@Slf4j
@ShenyuService("/server-account/**")
@AllArgsConstructor
public class ServerAccountRemoteService implements ServerAccountService {
    private final SnowflakeIdWorker snowflakeIdWorker;
    private final ServerAccountDomainService serverAccountDomainService;
    private final ServerAccountDataAdapter serverAccountDataAdapter;

    @Override
    public PojoResult<String> createServerAccount(CreateServerAccountCommand command) {
        ServerAccount serverAccount = new ServerAccount(new ServerAccountId(String.valueOf(snowflakeIdWorker.nextId())),
                new HostId(command.getHostId()));
        serverAccount.setAccountType(command.getAccountType());
        serverAccount.setAuthMode(command.getAuthMode());
        serverAccount.setUsername(command.getUsername());
        serverAccount.setDisplayName(command.getDisplayName());
        serverAccount.setProtocol(command.getProtocol());
        serverAccount.setActive(command.getActive());
        serverAccount.setCredential(command.getCredential());
        serverAccount.setPassphrase(command.getPassphrase());
        serverAccount.setPublicKey(command.getPublicKey());
        serverAccount.init();
        serverAccountDomainService.save(serverAccount);
        return PojoResult.succeed(serverAccount.getServerAccountId().getServerAccountId());
    }

    @Override
    public PojoResult<Boolean> modifyServerAccount(ModifyServerAccountCommand command) {
        ServerAccount serverAccount = serverAccountDomainService.getServerAccount(new ServerAccountId(command.getServerAccountId()));
        serverAccount.setDisplayName(command.getDisplayName());
        serverAccount.setUsername(command.getUsername());
        serverAccount.setAccountType(command.getAccountType());
        serverAccount.setAuthMode(command.getAuthMode());
        serverAccount.setPassphrase(command.getPassphrase());
        serverAccount.setProtocol(command.getProtocol());
        serverAccount.setCredential(command.getCredential());
        serverAccount.setPublicKey(command.getPublicKey());
        serverAccount.modify();
        serverAccountDomainService.update(serverAccount);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<Boolean> deleteServerAccount(DeleteServerAccountCommand command) {
        ServerAccount serverAccount = serverAccountDomainService.getServerAccount(new ServerAccountId(command.getServerAccountId()));
        serverAccount.delete();
        serverAccountDomainService.update(serverAccount);
        return PojoResult.succeed(Boolean.TRUE);
    }

    @Override
    public PojoResult<ServerAccountDTO> getServerAccount(ServerAccountBasicQuery basicQuery) {
        ServerAccount serverAccount = serverAccountDomainService.getServerAccount(new ServerAccountId(basicQuery.getServerAccountId()));
        return PojoResult.succeed(serverAccountDataAdapter.sourceToTarget(serverAccount));
    }

    @Override
    public PageResult<ServerAccountDTO> pageServerAccount(ServerAccountPageQuery serverAccountPageQuery) {
        ServerAccountQueryParam serverAccountQueryParam = serverAccountDataAdapter.convertParam(serverAccountPageQuery);
        Page<ServerAccount> page = serverAccountDomainService.pageServerAccount(serverAccountQueryParam);
        List<ServerAccount> serverAccounts = new ArrayList<>(page.getItems());
        List<ServerAccountDTO> serverAccountDTOList = serverAccountDataAdapter.sourceToTarget(serverAccounts);
        return PageResult.succeed(PageUtils.build(page, serverAccountDTOList));
    }
}
