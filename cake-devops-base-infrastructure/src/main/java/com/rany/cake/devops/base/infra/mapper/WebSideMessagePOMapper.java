package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.WebSideMessagePO;

public interface WebSideMessagePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insert(WebSideMessagePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insertSelective(WebSideMessagePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    WebSideMessagePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeySelective(WebSideMessagePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeyWithBLOBs(WebSideMessagePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKey(WebSideMessagePO record);
}