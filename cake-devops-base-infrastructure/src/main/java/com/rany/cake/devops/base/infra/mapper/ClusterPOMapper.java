package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ClusterPO;

public interface ClusterPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    int insert(ClusterPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    int insertSelective(ClusterPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    ClusterPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    int updateByPrimaryKeySelective(ClusterPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cluster
     *
     * @mbggenerated Fri Nov 10 22:09:54 CST 2023
     */
    int updateByPrimaryKey(ClusterPO record);
}