package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ApprovalPO;

public interface ApprovalPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Thu Nov 09 22:31:54 CST 2023
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Thu Nov 09 22:31:54 CST 2023
     */
    int insert(ApprovalPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Thu Nov 09 22:31:54 CST 2023
     */
    int insertSelective(ApprovalPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Thu Nov 09 22:31:54 CST 2023
     */
    ApprovalPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Thu Nov 09 22:31:54 CST 2023
     */
    int updateByPrimaryKeySelective(ApprovalPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Thu Nov 09 22:31:54 CST 2023
     */
    int updateByPrimaryKeyWithBLOBs(ApprovalPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Thu Nov 09 22:31:54 CST 2023
     */
    int updateByPrimaryKey(ApprovalPO record);
}