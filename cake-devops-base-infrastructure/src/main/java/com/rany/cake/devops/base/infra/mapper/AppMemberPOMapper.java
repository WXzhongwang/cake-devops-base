package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AppMemberPO;

public interface AppMemberPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    int insert(AppMemberPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    int insertSelective(AppMemberPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    AppMemberPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    int updateByPrimaryKeySelective(AppMemberPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_member
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    int updateByPrimaryKey(AppMemberPO record);
}