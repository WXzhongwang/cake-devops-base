package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;
import com.rany.cake.devops.base.infra.po.HostEnvPO;

import java.util.List;

public interface HostEnvDao {


    List<HostEnvPO> queryHostEnv(HostEnvQueryParam hostEnvQueryParam);

}
