package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostEnvPO;

public interface HostEnvPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_env
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_env
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    int insert(HostEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_env
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    int insertSelective(HostEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_env
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    HostEnvPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_env
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    int updateByPrimaryKeySelective(HostEnvPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_env
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    int updateByPrimaryKey(HostEnvPO record);
}