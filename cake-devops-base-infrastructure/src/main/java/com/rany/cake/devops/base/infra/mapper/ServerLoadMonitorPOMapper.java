package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ServerLoadMonitorPO;

public interface ServerLoadMonitorPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_load_monitor
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_load_monitor
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    int insert(ServerLoadMonitorPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_load_monitor
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    int insertSelective(ServerLoadMonitorPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_load_monitor
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    ServerLoadMonitorPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_load_monitor
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    int updateByPrimaryKeySelective(ServerLoadMonitorPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_load_monitor
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    int updateByPrimaryKey(ServerLoadMonitorPO record);
}