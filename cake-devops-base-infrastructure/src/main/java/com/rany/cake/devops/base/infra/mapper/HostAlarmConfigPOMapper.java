package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostAlarmConfigPO;

public interface HostAlarmConfigPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int insert(HostAlarmConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int insertSelective(HostAlarmConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    HostAlarmConfigPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int updateByPrimaryKeySelective(HostAlarmConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int updateByPrimaryKey(HostAlarmConfigPO record);
}