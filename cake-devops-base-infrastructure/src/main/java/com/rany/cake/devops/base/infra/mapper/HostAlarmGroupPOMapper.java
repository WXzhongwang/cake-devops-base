package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostAlarmGroupPO;

public interface HostAlarmGroupPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_group
     *
     * @mbggenerated Fri Apr 05 18:27:18 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_group
     *
     * @mbggenerated Fri Apr 05 18:27:18 CST 2024
     */
    int insert(HostAlarmGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_group
     *
     * @mbggenerated Fri Apr 05 18:27:18 CST 2024
     */
    int insertSelective(HostAlarmGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_group
     *
     * @mbggenerated Fri Apr 05 18:27:18 CST 2024
     */
    HostAlarmGroupPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_group
     *
     * @mbggenerated Fri Apr 05 18:27:18 CST 2024
     */
    int updateByPrimaryKeySelective(HostAlarmGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_group
     *
     * @mbggenerated Fri Apr 05 18:27:18 CST 2024
     */
    int updateByPrimaryKey(HostAlarmGroupPO record);
}