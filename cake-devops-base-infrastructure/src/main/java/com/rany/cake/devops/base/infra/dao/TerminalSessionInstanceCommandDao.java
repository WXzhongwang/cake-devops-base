package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.TerminalSessionInstanceCommand;
import com.rany.cake.devops.base.infra.po.TerminalSessionInstanceCommandPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 实例指令
 *
 * @author zhongshengwang
 * @description 实例指令
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface TerminalSessionInstanceCommandDao {


    /**
     * 新增
     *
     * @param terminalSessionInstanceCommand 终端指令
     * @return
     */
    int save(TerminalSessionInstanceCommand terminalSessionInstanceCommand);


    /**
     * 更新
     *
     * @param terminalSessionInstanceCommand 终端指令
     * @return
     */
    int update(TerminalSessionInstanceCommand terminalSessionInstanceCommand);

    /**
     * 按实例查指令列表
     *
     * @param instanceId
     * @return
     */
    List<TerminalSessionInstanceCommandPO> selectByInstanceId(@Param("instanceId") String instanceId);

}
