package com.rany.cake.devops.base.dao.mapper;

import com.rany.cake.devops.base.dao.po.AppPO;

public interface AppPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Jan 08 21:26:44 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Jan 08 21:26:44 CST 2023
     */
    int insert(AppPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Jan 08 21:26:44 CST 2023
     */
    int insertSelective(AppPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Jan 08 21:26:44 CST 2023
     */
    AppPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Jan 08 21:26:44 CST 2023
     */
    int updateByPrimaryKeySelective(AppPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Sun Jan 08 21:26:44 CST 2023
     */
    int updateByPrimaryKey(AppPO record);
}