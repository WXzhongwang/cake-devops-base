package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.TerminalSessionInstancePO;

public interface TerminalSessionInstancePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insert(TerminalSessionInstancePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insertSelective(TerminalSessionInstancePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    TerminalSessionInstancePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeySelective(TerminalSessionInstancePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKey(TerminalSessionInstancePO record);
}