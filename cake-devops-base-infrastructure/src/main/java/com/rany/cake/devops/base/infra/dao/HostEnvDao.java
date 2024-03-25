package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;
import com.rany.cake.devops.base.infra.po.HostEnvPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HostEnvDao {


    List<HostEnvPO> queryHostEnv(HostEnvQueryParam hostEnvQueryParam);

    HostEnvPO selectByName(@Param("name") String name,
                           @Param("hostId") String hostId);
}
