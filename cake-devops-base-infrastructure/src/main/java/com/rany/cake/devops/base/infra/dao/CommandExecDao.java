package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.CommandExec;
import com.rany.cake.devops.base.domain.repository.param.CommandExecQueryParam;
import com.rany.cake.devops.base.infra.po.CommandExecPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommandExecDao {

    List<CommandExecPO> findByIds(@Param("ids") List<Long> ids);

    int deleteByIds(@Param("ids") List<Long> ids, @Param("modifier") String modifier);

    /**
     * 新增
     *
     * @param commandExec 新增
     * @return 行数
     */
    int save(CommandExec commandExec);


    /**
     * 更新
     *
     * @param commandExec 更新
     * @return 行数
     */
    int update(CommandExec commandExec);

    /**
     * 查询
     *
     * @param commandExecQueryParam 查询
     * @return 模版查询
     */
    List<CommandExecPO> queryCommandExec(CommandExecQueryParam commandExecQueryParam);
}