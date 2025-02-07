package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostTerminalConfigPO;

public interface HostTerminalConfigPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int insert(HostTerminalConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int insertSelective(HostTerminalConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    HostTerminalConfigPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int updateByPrimaryKeySelective(HostTerminalConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    int updateByPrimaryKey(HostTerminalConfigPO record);
}