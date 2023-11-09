package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.NamespacePO;

public interface NamespacePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Thu Nov 09 08:54:30 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Thu Nov 09 08:54:30 CST 2023
     */
    int insert(NamespacePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Thu Nov 09 08:54:30 CST 2023
     */
    int insertSelective(NamespacePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Thu Nov 09 08:54:30 CST 2023
     */
    NamespacePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Thu Nov 09 08:54:30 CST 2023
     */
    int updateByPrimaryKeySelective(NamespacePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Thu Nov 09 08:54:30 CST 2023
     */
    int updateByPrimaryKey(NamespacePO record);
}