package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.GroupHostPO;

public interface GroupHostPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_host
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_host
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    int insert(GroupHostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_host
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    int insertSelective(GroupHostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_host
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    GroupHostPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_host
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    int updateByPrimaryKeySelective(GroupHostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_host
     *
     * @mbggenerated Mon Mar 11 22:08:23 CST 2024
     */
    int updateByPrimaryKey(GroupHostPO record);
}