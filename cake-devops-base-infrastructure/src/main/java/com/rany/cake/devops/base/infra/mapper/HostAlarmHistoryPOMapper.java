package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostAlarmHistoryPO;

public interface HostAlarmHistoryPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_history
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_history
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int insert(HostAlarmHistoryPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_history
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int insertSelective(HostAlarmHistoryPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_history
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    HostAlarmHistoryPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_history
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int updateByPrimaryKeySelective(HostAlarmHistoryPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_alarm_history
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int updateByPrimaryKey(HostAlarmHistoryPO record);
}