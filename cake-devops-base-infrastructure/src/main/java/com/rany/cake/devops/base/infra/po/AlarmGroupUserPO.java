package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class AlarmGroupUserPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_user.id
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_user.group_id
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    private Long groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_user.account_id
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    private Long accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_user.username
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_user.is_deleted
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_user.gmt_create
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_user.gmt_modified
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_user.id
     *
     * @return the value of alarm_group_user.id
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_user.id
     *
     * @param id the value for alarm_group_user.id
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_user.group_id
     *
     * @return the value of alarm_group_user.group_id
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_user.group_id
     *
     * @param groupId the value for alarm_group_user.group_id
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_user.account_id
     *
     * @return the value of alarm_group_user.account_id
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_user.account_id
     *
     * @param accountId the value for alarm_group_user.account_id
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_user.username
     *
     * @return the value of alarm_group_user.username
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_user.username
     *
     * @param username the value for alarm_group_user.username
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_user.is_deleted
     *
     * @return the value of alarm_group_user.is_deleted
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_user.is_deleted
     *
     * @param isDeleted the value for alarm_group_user.is_deleted
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_user.gmt_create
     *
     * @return the value of alarm_group_user.gmt_create
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_user.gmt_create
     *
     * @param gmtCreate the value for alarm_group_user.gmt_create
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_user.gmt_modified
     *
     * @return the value of alarm_group_user.gmt_modified
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_user.gmt_modified
     *
     * @param gmtModified the value for alarm_group_user.gmt_modified
     *
     * @mbggenerated Mon Mar 04 22:57:53 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}