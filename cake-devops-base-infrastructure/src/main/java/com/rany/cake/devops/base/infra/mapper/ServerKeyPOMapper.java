package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ServerKeyPO;

public interface ServerKeyPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_key
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_key
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int insert(ServerKeyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_key
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int insertSelective(ServerKeyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_key
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    ServerKeyPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_key
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int updateByPrimaryKeySelective(ServerKeyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_key
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int updateByPrimaryKey(ServerKeyPO record);
}