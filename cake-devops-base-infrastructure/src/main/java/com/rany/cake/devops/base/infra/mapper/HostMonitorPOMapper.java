package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostMonitorPO;

public interface HostMonitorPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_monitor
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_monitor
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    int insert(HostMonitorPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_monitor
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    int insertSelective(HostMonitorPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_monitor
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    HostMonitorPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_monitor
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    int updateByPrimaryKeySelective(HostMonitorPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_monitor
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    int updateByPrimaryKey(HostMonitorPO record);
}