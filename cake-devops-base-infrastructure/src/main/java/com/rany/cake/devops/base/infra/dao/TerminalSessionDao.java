package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.aggregate.TerminalSession;
import com.rany.cake.devops.base.infra.po.TerminalSessionPO;

import java.util.List;

/**
 * 发布
 *
 * @author zhongshengwang
 * @description 发布
 * @date 2023/2/2 21:24
 * @email 18668485565163.com
 */
public interface TerminalSessionDao {


    /**
     * 新增
     *
     * @param terminalSession 终端
     * @return
     */
    int save(TerminalSession terminalSession);


    /**
     * 更新
     *
     * @param terminalSession 终端
     * @return
     */
    int update(TerminalSession terminalSession);

    /**
     * 查看所有session
     *
     * @return
     */
    List<TerminalSessionPO> selectAll();

}
