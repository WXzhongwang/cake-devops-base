package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostAlarmConfigPO;

public interface HostAlarmConfigPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    int insert(HostAlarmConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    int insertSelective(HostAlarmConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    HostAlarmConfigPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    int updateByPrimaryKeySelective(HostAlarmConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_config
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    int updateByPrimaryKey(HostAlarmConfigPO record);
}