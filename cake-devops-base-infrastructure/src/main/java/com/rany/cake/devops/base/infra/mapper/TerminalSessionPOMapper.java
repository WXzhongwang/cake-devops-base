package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.TerminalSessionPO;

public interface TerminalSessionPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sun Feb 04 19:57:16 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sun Feb 04 19:57:16 CST 2024
     */
    int insert(TerminalSessionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sun Feb 04 19:57:16 CST 2024
     */
    int insertSelective(TerminalSessionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sun Feb 04 19:57:16 CST 2024
     */
    TerminalSessionPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sun Feb 04 19:57:16 CST 2024
     */
    int updateByPrimaryKeySelective(TerminalSessionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sun Feb 04 19:57:16 CST 2024
     */
    int updateByPrimaryKey(TerminalSessionPO record);
}