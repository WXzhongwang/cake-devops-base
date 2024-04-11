package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.host.*;
import com.rany.cake.devops.base.api.dto.HostDTO;
import com.rany.cake.devops.base.api.query.host.HostBasicQuery;
import com.rany.cake.devops.base.api.query.host.HostPageQuery;

/**
 * 主机服务
 *
 * @author zhongshengwang
 * @description 主机服务
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface HostService {

    /**
     * 创建主机
     *
     * @param createHostCommand 创建
     * @return result
     */
    String createHost(CreateHostCommand createHostCommand);

    /**
     * 复制机器
     *
     * @param copyHostCommand 复制
     * @return 机器ID
     */
    String copyHost(CopyHostCommand copyHostCommand);

    /**
     * ping主机
     *
     * @param copyHostCommand ping主机
     * @return
     */
    String ping(PingHostCommand copyHostCommand);

    /**
     * 获取主机信息
     *
     * @param hostBasicQuery 详情
     * @return 详情
     */
    HostDTO getHost(HostBasicQuery hostBasicQuery);

    /**
     * 分页查询主机列表
     *
     * @param hostPageQuery 分页查询
     * @return app集合
     */
    Page<HostDTO> pageHost(HostPageQuery hostPageQuery);


    /**
     * 删除主机
     *
     * @param deleteHostCommand 删除
     * @return 成功
     */
    Boolean deleteHost(DeleteHostCommand deleteHostCommand);

    /**
     * 更新主机基本信息
     *
     * @param modifyHostCommand 更新
     * @return 成功
     */
    Boolean modifyHost(ModifyHostCommand modifyHostCommand);
}
