package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostPO;

public interface HostPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    int insert(HostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    int insertSelective(HostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    HostPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    int updateByPrimaryKeySelective(HostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    int updateByPrimaryKey(HostPO record);
}