package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ClusterPO;

public interface ClusterPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    int insert(ClusterPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    int insertSelective(ClusterPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    ClusterPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    int updateByPrimaryKeySelective(ClusterPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    int updateByPrimaryKey(ClusterPO record);
}