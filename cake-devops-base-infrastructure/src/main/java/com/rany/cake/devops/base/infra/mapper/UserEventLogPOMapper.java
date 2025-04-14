package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.UserEventLogPO;
import com.rany.cake.devops.base.infra.po.UserEventLogPOWithBLOBs;

public interface UserEventLogPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_event_log
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_event_log
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insert(UserEventLogPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_event_log
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insertSelective(UserEventLogPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_event_log
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    UserEventLogPOWithBLOBs selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_event_log
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeySelective(UserEventLogPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_event_log
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeyWithBLOBs(UserEventLogPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_event_log
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKey(UserEventLogPO record);
}