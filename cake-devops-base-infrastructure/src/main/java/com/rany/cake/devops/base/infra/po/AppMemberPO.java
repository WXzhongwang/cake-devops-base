package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class AppMemberPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.member_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String memberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.account_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.app_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.roles
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String roles;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.status
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.is_deleted
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.gmt_create
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.gmt_modified
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.creator
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_member.modifier
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.id
     *
     * @return the value of app_member.id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
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
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.member_id
     *
     * @return the value of app_member.member_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.member_id
     *
     * @param memberId the value for app_member.member_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.account_id
     *
     * @return the value of app_member.account_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.account_id
     *
     * @param accountId the value for app_member.account_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.app_id
     *
     * @return the value of app_member.app_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.app_id
     *
     * @param appId the value for app_member.app_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.roles
     *
     * @return the value of app_member.roles
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
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
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
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
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
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
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
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
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
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
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
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
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
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
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
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
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
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
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.creator
     *
     * @return the value of app_member.creator
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.creator
     *
     * @param creator the value for app_member.creator
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_member.modifier
     *
     * @return the value of app_member.modifier
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_member.modifier
     *
     * @param modifier the value for app_member.modifier
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}