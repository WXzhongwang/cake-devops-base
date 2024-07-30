package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.HostTerminalConfigPO;

public interface HostTerminalConfigPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int insert(HostTerminalConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int insertSelective(HostTerminalConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    HostTerminalConfigPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int updateByPrimaryKeySelective(HostTerminalConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table host_terminal_config
     *
     * @mbggenerated Tue Jul 30 15:33:23 CST 2024
     */
    int updateByPrimaryKey(HostTerminalConfigPO record);
}