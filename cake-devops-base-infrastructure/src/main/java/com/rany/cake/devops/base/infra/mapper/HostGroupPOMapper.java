package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostGroupPO;

public interface HostGroupPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insert(HostGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insertSelective(HostGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    HostGroupPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeySelective(HostGroupPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_group
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKey(HostGroupPO record);
}