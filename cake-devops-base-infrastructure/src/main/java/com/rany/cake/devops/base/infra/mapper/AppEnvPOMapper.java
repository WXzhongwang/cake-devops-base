package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AppEnvPO;

public interface AppEnvPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insert(AppEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insertSelective(AppEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    AppEnvPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeySelective(AppEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKey(AppEnvPO record);
}