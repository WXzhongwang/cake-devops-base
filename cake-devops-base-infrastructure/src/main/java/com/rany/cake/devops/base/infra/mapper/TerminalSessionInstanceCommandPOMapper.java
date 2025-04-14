package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.TerminalSessionInstanceCommandPO;
import com.rany.cake.devops.base.infra.po.TerminalSessionInstanceCommandPOWithBLOBs;

public interface TerminalSessionInstanceCommandPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insert(TerminalSessionInstanceCommandPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insertSelective(TerminalSessionInstanceCommandPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    TerminalSessionInstanceCommandPOWithBLOBs selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeySelective(TerminalSessionInstanceCommandPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeyWithBLOBs(TerminalSessionInstanceCommandPOWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session_instance_command
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKey(TerminalSessionInstanceCommandPO record);
}