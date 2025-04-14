package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ReleasePO;

public interface ReleasePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insert(ReleasePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insertSelective(ReleasePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    ReleasePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeySelective(ReleasePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKey(ReleasePO record);
}