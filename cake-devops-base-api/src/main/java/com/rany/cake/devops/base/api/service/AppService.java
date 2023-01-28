package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.CreateAppCommand;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface AppService {

    /**
     * 创建应用
     *
     * @param createAppCommand
     * @return
     */
    PojoResult<Long> createApp(CreateAppCommand createAppCommand);

}
