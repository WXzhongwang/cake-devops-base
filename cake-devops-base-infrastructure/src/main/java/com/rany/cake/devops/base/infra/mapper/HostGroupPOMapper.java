package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostGroupPO;

public interface HostGroupPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Thu Jan 18 21:54:04 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Thu Jan 18 21:54:04 CST 2024
     */
    int insert(HostGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Thu Jan 18 21:54:04 CST 2024
     */
    int insertSelective(HostGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Thu Jan 18 21:54:04 CST 2024
     */
    HostGroupPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Thu Jan 18 21:54:04 CST 2024
     */
    int updateByPrimaryKeySelective(HostGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Thu Jan 18 21:54:04 CST 2024
     */
    int updateByPrimaryKey(HostGroupPO record);
}