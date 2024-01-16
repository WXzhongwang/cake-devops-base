package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.NamespacePO;

public interface NamespacePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    int insert(NamespacePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    int insertSelective(NamespacePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    NamespacePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    int updateByPrimaryKeySelective(NamespacePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table namespace
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    int updateByPrimaryKey(NamespacePO record);
}