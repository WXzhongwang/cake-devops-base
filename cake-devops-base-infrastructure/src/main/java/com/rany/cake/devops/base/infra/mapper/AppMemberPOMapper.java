package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AppMemberPO;

public interface AppMemberPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    int insert(AppMemberPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    int insertSelective(AppMemberPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    AppMemberPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    int updateByPrimaryKeySelective(AppMemberPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    int updateByPrimaryKey(AppMemberPO record);
}