package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AppPO;

public interface AppPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    int insert(AppPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    int insertSelective(AppPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    AppPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    int updateByPrimaryKeySelective(AppPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    int updateByPrimaryKey(AppPO record);
}