package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostTerminalLogPO;

public interface HostTerminalLogPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_log
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_log
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int insert(HostTerminalLogPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_log
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int insertSelective(HostTerminalLogPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_log
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    HostTerminalLogPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_log
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int updateByPrimaryKeySelective(HostTerminalLogPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_log
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    int updateByPrimaryKey(HostTerminalLogPO record);
}