package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ServerMonitorPO;

public interface ServerMonitorPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_monitor
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_monitor
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    int insert(ServerMonitorPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_monitor
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    int insertSelective(ServerMonitorPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_monitor
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    ServerMonitorPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_monitor
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    int updateByPrimaryKeySelective(ServerMonitorPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_monitor
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    int updateByPrimaryKey(ServerMonitorPO record);
}