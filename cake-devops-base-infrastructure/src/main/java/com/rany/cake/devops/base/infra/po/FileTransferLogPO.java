package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class FileTransferLogPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.account_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Long accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.username
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.file_token
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String fileToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.transfer_type
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Byte transferType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.host_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.remote_file
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String remoteFile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.local_file
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String localFile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.current_size
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Long currentSize;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.file_size
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Long fileSize;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.now_progress
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Double nowProgress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.transfer_status
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Byte transferStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.creator
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.modifier
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.is_deleted
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.gmt_create
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_transfer_log.gmt_modified
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.id
     *
     * @return the value of file_transfer_log.id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.id
     *
     * @param id the value for file_transfer_log.id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.account_id
     *
     * @return the value of file_transfer_log.account_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.account_id
     *
     * @param accountId the value for file_transfer_log.account_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.username
     *
     * @return the value of file_transfer_log.username
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.username
     *
     * @param username the value for file_transfer_log.username
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.file_token
     *
     * @return the value of file_transfer_log.file_token
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getFileToken() {
        return fileToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.file_token
     *
     * @param fileToken the value for file_transfer_log.file_token
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setFileToken(String fileToken) {
        this.fileToken = fileToken == null ? null : fileToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.transfer_type
     *
     * @return the value of file_transfer_log.transfer_type
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Byte getTransferType() {
        return transferType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.transfer_type
     *
     * @param transferType the value for file_transfer_log.transfer_type
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setTransferType(Byte transferType) {
        this.transferType = transferType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.host_id
     *
     * @return the value of file_transfer_log.host_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.host_id
     *
     * @param hostId the value for file_transfer_log.host_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.remote_file
     *
     * @return the value of file_transfer_log.remote_file
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getRemoteFile() {
        return remoteFile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.remote_file
     *
     * @param remoteFile the value for file_transfer_log.remote_file
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setRemoteFile(String remoteFile) {
        this.remoteFile = remoteFile == null ? null : remoteFile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.local_file
     *
     * @return the value of file_transfer_log.local_file
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getLocalFile() {
        return localFile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.local_file
     *
     * @param localFile the value for file_transfer_log.local_file
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setLocalFile(String localFile) {
        this.localFile = localFile == null ? null : localFile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.current_size
     *
     * @return the value of file_transfer_log.current_size
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Long getCurrentSize() {
        return currentSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.current_size
     *
     * @param currentSize the value for file_transfer_log.current_size
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setCurrentSize(Long currentSize) {
        this.currentSize = currentSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.file_size
     *
     * @return the value of file_transfer_log.file_size
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.file_size
     *
     * @param fileSize the value for file_transfer_log.file_size
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.now_progress
     *
     * @return the value of file_transfer_log.now_progress
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Double getNowProgress() {
        return nowProgress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.now_progress
     *
     * @param nowProgress the value for file_transfer_log.now_progress
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setNowProgress(Double nowProgress) {
        this.nowProgress = nowProgress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.transfer_status
     *
     * @return the value of file_transfer_log.transfer_status
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Byte getTransferStatus() {
        return transferStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.transfer_status
     *
     * @param transferStatus the value for file_transfer_log.transfer_status
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setTransferStatus(Byte transferStatus) {
        this.transferStatus = transferStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.creator
     *
     * @return the value of file_transfer_log.creator
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.creator
     *
     * @param creator the value for file_transfer_log.creator
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.modifier
     *
     * @return the value of file_transfer_log.modifier
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.modifier
     *
     * @param modifier the value for file_transfer_log.modifier
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.is_deleted
     *
     * @return the value of file_transfer_log.is_deleted
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.is_deleted
     *
     * @param isDeleted the value for file_transfer_log.is_deleted
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.gmt_create
     *
     * @return the value of file_transfer_log.gmt_create
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.gmt_create
     *
     * @param gmtCreate the value for file_transfer_log.gmt_create
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_transfer_log.gmt_modified
     *
     * @return the value of file_transfer_log.gmt_modified
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_transfer_log.gmt_modified
     *
     * @param gmtModified the value for file_transfer_log.gmt_modified
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}