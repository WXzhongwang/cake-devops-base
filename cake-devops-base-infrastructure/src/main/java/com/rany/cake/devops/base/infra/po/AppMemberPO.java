package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class AppMemberPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.id
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.account_id
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    private Long accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.app_id
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    private Long appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.roles
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    private String roles;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.status
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.is_deleted
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.gmt_create
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.gmt_modified
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.id
     *
     * @return the value of app_member.id
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.id
     *
     * @param id the value for app_member.id
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.account_id
     *
     * @return the value of app_member.account_id
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.account_id
     *
     * @param accountId the value for app_member.account_id
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.app_id
     *
     * @return the value of app_member.app_id
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.app_id
     *
     * @param appId the value for app_member.app_id
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.roles
     *
     * @return the value of app_member.roles
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public String getRoles() {
        return roles;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.roles
     *
     * @param roles the value for app_member.roles
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.status
     *
     * @return the value of app_member.status
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.status
     *
     * @param status the value for app_member.status
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.is_deleted
     *
     * @return the value of app_member.is_deleted
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.is_deleted
     *
     * @param isDeleted the value for app_member.is_deleted
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.gmt_create
     *
     * @return the value of app_member.gmt_create
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.gmt_create
     *
     * @param gmtCreate the value for app_member.gmt_create
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.gmt_modified
     *
     * @return the value of app_member.gmt_modified
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.gmt_modified
     *
     * @param gmtModified the value for app_member.gmt_modified
     *
     * @mbggenerated Fri Feb 03 22:36:34 CST 2023
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}