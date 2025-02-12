package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AlarmGroupNotifyPO;

public interface AlarmGroupNotifyPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int insert(AlarmGroupNotifyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int insertSelective(AlarmGroupNotifyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    AlarmGroupNotifyPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int updateByPrimaryKeySelective(AlarmGroupNotifyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int updateByPrimaryKey(AlarmGroupNotifyPO record);
}