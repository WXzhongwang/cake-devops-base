package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ServerProxyPO;

public interface ServerProxyPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_proxy
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_proxy
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    int insert(ServerProxyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_proxy
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    int insertSelective(ServerProxyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_proxy
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    ServerProxyPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_proxy
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    int updateByPrimaryKeySelective(ServerProxyPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table server_proxy
     *
     * @mbggenerated Wed Aug 21 21:58:03 CST 2024
     */
    int updateByPrimaryKey(ServerProxyPO record);
}