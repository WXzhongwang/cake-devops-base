package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AppPO;

public interface AppPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    int insert(AppPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    int insertSelective(AppPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    AppPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    int updateByPrimaryKeySelective(AppPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    int updateByPrimaryKey(AppPO record);
}