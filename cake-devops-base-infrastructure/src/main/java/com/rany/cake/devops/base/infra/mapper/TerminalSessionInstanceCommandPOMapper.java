package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.TerminalSessionInstanceCommandPO;
import com.rany.cake.devops.base.infra.po.TerminalSessionInstanceCommandPOWithBLOBs;

public interface TerminalSessionInstanceCommandPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int insert(TerminalSessionInstanceCommandPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int insertSelective(TerminalSessionInstanceCommandPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    TerminalSessionInstanceCommandPOWithBLOBs selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int updateByPrimaryKeySelective(TerminalSessionInstanceCommandPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int updateByPrimaryKeyWithBLOBs(TerminalSessionInstanceCommandPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Wed Mar 13 22:06:53 CST 2024
     */
    int updateByPrimaryKey(TerminalSessionInstanceCommandPO record);
}