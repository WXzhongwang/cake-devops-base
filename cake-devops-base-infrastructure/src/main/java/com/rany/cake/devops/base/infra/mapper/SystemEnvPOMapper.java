package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.SystemEnvPO;

public interface SystemEnvPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_env
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_env
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int insert(SystemEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_env
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int insertSelective(SystemEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_env
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    SystemEnvPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_env
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int updateByPrimaryKeySelective(SystemEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_env
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int updateByPrimaryKey(SystemEnvPO record);
}