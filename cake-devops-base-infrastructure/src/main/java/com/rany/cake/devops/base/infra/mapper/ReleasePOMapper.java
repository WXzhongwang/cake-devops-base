package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ReleasePO;

public interface ReleasePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release
     *
     * @mbggenerated Wed Nov 15 23:04:25 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release
     *
     * @mbggenerated Wed Nov 15 23:04:25 CST 2023
     */
    int insert(ReleasePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release
     *
     * @mbggenerated Wed Nov 15 23:04:25 CST 2023
     */
    int insertSelective(ReleasePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release
     *
     * @mbggenerated Wed Nov 15 23:04:25 CST 2023
     */
    ReleasePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release
     *
     * @mbggenerated Wed Nov 15 23:04:25 CST 2023
     */
    int updateByPrimaryKeySelective(ReleasePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release
     *
     * @mbggenerated Wed Nov 15 23:04:25 CST 2023
     */
    int updateByPrimaryKey(ReleasePO record);
}