package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AlarmGroupPO;

public interface AlarmGroupPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    int insert(AlarmGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    int insertSelective(AlarmGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    AlarmGroupPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    int updateByPrimaryKeySelective(AlarmGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alarm_group
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    int updateByPrimaryKey(AlarmGroupPO record);
}