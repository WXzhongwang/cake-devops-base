package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.TerminalSessionInstancePO;

public interface TerminalSessionInstancePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Dec 18 20:57:06 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Dec 18 20:57:06 CST 2023
     */
    int insert(TerminalSessionInstancePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Dec 18 20:57:06 CST 2023
     */
    int insertSelective(TerminalSessionInstancePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Dec 18 20:57:06 CST 2023
     */
    TerminalSessionInstancePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Dec 18 20:57:06 CST 2023
     */
    int updateByPrimaryKeySelective(TerminalSessionInstancePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Dec 18 20:57:06 CST 2023
     */
    int updateByPrimaryKey(TerminalSessionInstancePO record);
}