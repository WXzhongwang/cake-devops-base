package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostPO;

public interface HostPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int insert(HostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int insertSelective(HostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    HostPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int updateByPrimaryKeySelective(HostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int updateByPrimaryKey(HostPO record);
}