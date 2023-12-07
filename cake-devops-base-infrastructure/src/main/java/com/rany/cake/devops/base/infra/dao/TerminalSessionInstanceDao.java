package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.TerminalSessionInstance;
import com.rany.cake.devops.base.infra.po.TerminalSessionInstancePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 实例
 *
 * @author zhongshengwang
 * @description 实例
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface TerminalSessionInstanceDao {


    /**
     * 新增
     *
     * @param terminalSession 终端
     * @return
     */
    int save(TerminalSessionInstance terminalSession);


    /**
     * 更新
     *
     * @param terminalSession 终端
     * @return
     */
    int update(TerminalSessionInstance terminalSession);

    /**
     * 查看所有session
     *
     * @return
     */
    List<TerminalSessionInstancePO> selectBySessionId(@Param("sessionId") String sessionId);

    TerminalSessionInstancePO selectByInstanceId(@Param("instanceId") String instanceId);

}
