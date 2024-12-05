package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.repository.param.UserEventLogQueryPageParam;
import com.rany.cake.devops.base.infra.po.UserEventLogPO;

import java.util.List;

public interface UserEventLogDao {

    /**
     * 支持按参数查询
     *
     * @param param 查询
     * @return 日志结果
     */
    List<UserEventLogPO> selectList(UserEventLogQueryPageParam param);
}
