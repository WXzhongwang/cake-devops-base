package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ScriptTemplatePO;

public interface ScriptTemplatePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table script_template
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table script_template
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    int insert(ScriptTemplatePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table script_template
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    int insertSelective(ScriptTemplatePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table script_template
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    ScriptTemplatePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table script_template
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    int updateByPrimaryKeySelective(ScriptTemplatePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table script_template
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    int updateByPrimaryKey(ScriptTemplatePO record);
}