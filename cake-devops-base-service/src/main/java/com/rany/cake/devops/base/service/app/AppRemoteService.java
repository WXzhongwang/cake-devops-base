package com.rany.cake.devops.base.service.app;

import com.alibaba.dubbo.config.annotation.Service;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.CreateAppCommand;
import com.rany.cake.devops.base.api.service.AppService;
import com.rany.cake.devops.base.domain.aggegrate.App;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.type.AppName;
import com.rany.cake.devops.base.service.base.SnowflakeIdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/28 20:40
 * @email 18668485565163.com
 */
@Service
@Slf4j
@AllArgsConstructor
public class AppRemoteService implements AppService {

    private final SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public PojoResult<Long> createApp(CreateAppCommand createAppCommand) {
        App app = new App(new AppId(snowflakeIdWorker.nextId()), new AppName(createAppCommand.getAppName()), createAppCommand.getOwner());
        return null;
    }
}
