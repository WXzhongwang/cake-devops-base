package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.FileTransferLogPO;

public interface FileTransferLogPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_transfer_log
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_transfer_log
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    int insert(FileTransferLogPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_transfer_log
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    int insertSelective(FileTransferLogPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_transfer_log
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    FileTransferLogPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_transfer_log
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    int updateByPrimaryKeySelective(FileTransferLogPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_transfer_log
     *
     * @mbggenerated Sun Oct 27 09:57:03 CST 2024
     */
    int updateByPrimaryKey(FileTransferLogPO record);
}