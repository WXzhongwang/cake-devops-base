package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.ApprovalPO;

public interface ApprovalPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Sat Feb 03 15:37:50 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Sat Feb 03 15:37:50 CST 2024
     */
    int insert(ApprovalPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Sat Feb 03 15:37:50 CST 2024
     */
    int insertSelective(ApprovalPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Sat Feb 03 15:37:50 CST 2024
     */
    ApprovalPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Sat Feb 03 15:37:50 CST 2024
     */
    int updateByPrimaryKeySelective(ApprovalPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table approval
     *
     * @mbggenerated Sat Feb 03 15:37:50 CST 2024
     */
    int updateByPrimaryKey(ApprovalPO record);
}