package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AlarmGroupNotifyPO;

public interface AlarmGroupNotifyPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Sun Mar 17 09:13:34 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Sun Mar 17 09:13:34 CST 2024
     */
    int insert(AlarmGroupNotifyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Sun Mar 17 09:13:34 CST 2024
     */
    int insertSelective(AlarmGroupNotifyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Sun Mar 17 09:13:34 CST 2024
     */
    AlarmGroupNotifyPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Sun Mar 17 09:13:34 CST 2024
     */
    int updateByPrimaryKeySelective(AlarmGroupNotifyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_notify
     *
     * @mbggenerated Sun Mar 17 09:13:34 CST 2024
     */
    int updateByPrimaryKey(AlarmGroupNotifyPO record);
}