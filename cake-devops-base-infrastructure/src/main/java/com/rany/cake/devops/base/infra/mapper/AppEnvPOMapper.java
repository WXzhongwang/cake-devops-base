package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.AppEnvPO;

public interface AppEnvPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    int insert(AppEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    int insertSelective(AppEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    AppEnvPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    int updateByPrimaryKeySelective(AppEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table app_env
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    int updateByPrimaryKey(AppEnvPO record);
}