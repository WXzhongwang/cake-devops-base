package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ReleasePO;

public interface ReleasePOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    int insert(ReleasePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    int insertSelective(ReleasePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    ReleasePO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    int updateByPrimaryKeySelective(ReleasePO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table release_no
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    int updateByPrimaryKey(ReleasePO record);
}