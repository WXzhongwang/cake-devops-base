package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.TerminalSessionPO;

public interface TerminalSessionPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    int insert(TerminalSessionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    int insertSelective(TerminalSessionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    TerminalSessionPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    int updateByPrimaryKeySelective(TerminalSessionPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table terminal_session
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    int updateByPrimaryKey(TerminalSessionPO record);
}