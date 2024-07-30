package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.WebSideMessagePO;

public interface WebSideMessagePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int insert(WebSideMessagePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int insertSelective(WebSideMessagePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    WebSideMessagePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int updateByPrimaryKeySelective(WebSideMessagePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int updateByPrimaryKeyWithBLOBs(WebSideMessagePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table web_side_message
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int updateByPrimaryKey(WebSideMessagePO record);
}