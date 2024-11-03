package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AlarmGroupUserPO;

public interface AlarmGroupUserPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_user
     *
     * @mbggenerated Sun Nov 03 09:43:54 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_user
     *
     * @mbggenerated Sun Nov 03 09:43:54 CST 2024
     */
    int insert(AlarmGroupUserPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_user
     *
     * @mbggenerated Sun Nov 03 09:43:54 CST 2024
     */
    int insertSelective(AlarmGroupUserPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_user
     *
     * @mbggenerated Sun Nov 03 09:43:54 CST 2024
     */
    AlarmGroupUserPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_user
     *
     * @mbggenerated Sun Nov 03 09:43:54 CST 2024
     */
    int updateByPrimaryKeySelective(AlarmGroupUserPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group_user
     *
     * @mbggenerated Sun Nov 03 09:43:54 CST 2024
     */
    int updateByPrimaryKey(AlarmGroupUserPO record);
}