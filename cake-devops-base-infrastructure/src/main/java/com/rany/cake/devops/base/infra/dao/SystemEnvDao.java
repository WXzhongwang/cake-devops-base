package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.SystemEnv;
import com.rany.cake.devops.base.domain.repository.param.SystemEnvPageQueryParam;
import com.rany.cake.devops.base.infra.po.SystemEnvPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemEnvDao {


    List<SystemEnvPO> querySystemEnv(SystemEnvPageQueryParam systemEnvPageQueryParam);

    List<SystemEnvPO> findAllSystemEnv(@Param("systemEnv") Integer systemEnv);

    SystemEnvPO selectByName(@Param("name") String name);

    List<SystemEnvPO> selectByNames(@Param("names") List<String> names);


    /**
     * 保存
     *
     * @param systemEnv env
     * @return
     */
    int save(SystemEnv systemEnv);


    /**
     * 更新
     *
     * @param systemEnv env
     * @return 行数
     */
    int update(SystemEnv systemEnv);
}
