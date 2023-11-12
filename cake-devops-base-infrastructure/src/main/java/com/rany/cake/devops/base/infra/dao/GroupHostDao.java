package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.GroupHost;
import com.rany.cake.devops.base.infra.po.GroupHostPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupHostDao {


    /**
     * 新增
     *
     * @param groupHost 小组主机关系
     * @return
     */
    int save(GroupHost groupHost);


    /**
     * 更新
     *
     * @param groupHost 小组主机关系
     * @return
     */
    int update(GroupHost groupHost);

    /**
     * 根据小组查主机
     *
     * @param groupIds
     * @return
     */
    List<GroupHostPO> selectByGroupIds(@Param("groupIds") List<Long> groupIds);
}
