package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AppMemberPO;

public interface AppMemberPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int insert(AppMemberPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int insertSelective(AppMemberPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    AppMemberPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int updateByPrimaryKeySelective(AppMemberPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int updateByPrimaryKey(AppMemberPO record);
}