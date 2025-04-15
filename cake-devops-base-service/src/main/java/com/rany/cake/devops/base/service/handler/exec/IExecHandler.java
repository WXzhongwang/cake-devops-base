package com.rany.cake.devops.base.service.handler.exec;


import com.rany.cake.toolkit.lang.Executable;
import com.rany.cake.toolkit.lang.io.SafeCloseable;
import com.rany.ops.common.dto.account.AccountDTO;

/**
 * 命令执行器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/4 22:54
 */
public interface IExecHandler extends Runnable, Executable, SafeCloseable {

    /**
     * 写入
     *
     * @param out out
     */
    void write(String out);

    /**
     * 停止
     */
    void terminate();

    /**
     * 获取实际执行 handler
     *
     * @param execId execId
     * @return handler
     */
    static IExecHandler with(Long execId, AccountDTO account) {
        return new CommandExecHandler(execId, account);
    }

}
