package com.rany.cake.devops.base.dao.po;

import java.util.Date;

public class AppPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.id
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.app_name
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String appName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.unique_no
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String uniqueNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.description
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.repo
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String repo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.default_branch
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String defaultBranch;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.language
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String language;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.develop_mode
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String developMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.owner
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private Long owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.health_check
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String healthCheck;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.status
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.is_deleted
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.gmt_create
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.gmt_modified
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.business_unit
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String businessUnit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.bu
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String bu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.department
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    private String department;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.id
     *
     * @return the value of app.id
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.id
     *
     * @param id the value for app.id
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.app_name
     *
     * @return the value of app.app_name
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getAppName() {
        return appName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.app_name
     *
     * @param appName the value for app.app_name
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.unique_no
     *
     * @return the value of app.unique_no
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getUniqueNo() {
        return uniqueNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.unique_no
     *
     * @param uniqueNo the value for app.unique_no
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setUniqueNo(String uniqueNo) {
        this.uniqueNo = uniqueNo == null ? null : uniqueNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.description
     *
     * @return the value of app.description
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.description
     *
     * @param description the value for app.description
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.repo
     *
     * @return the value of app.repo
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getRepo() {
        return repo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.repo
     *
     * @param repo the value for app.repo
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setRepo(String repo) {
        this.repo = repo == null ? null : repo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.default_branch
     *
     * @return the value of app.default_branch
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getDefaultBranch() {
        return defaultBranch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.default_branch
     *
     * @param defaultBranch the value for app.default_branch
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch == null ? null : defaultBranch.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.language
     *
     * @return the value of app.language
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getLanguage() {
        return language;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.language
     *
     * @param language the value for app.language
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.develop_mode
     *
     * @return the value of app.develop_mode
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getDevelopMode() {
        return developMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.develop_mode
     *
     * @param developMode the value for app.develop_mode
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setDevelopMode(String developMode) {
        this.developMode = developMode == null ? null : developMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.owner
     *
     * @return the value of app.owner
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public Long getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.owner
     *
     * @param owner the value for app.owner
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setOwner(Long owner) {
        this.owner = owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.health_check
     *
     * @return the value of app.health_check
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getHealthCheck() {
        return healthCheck;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.health_check
     *
     * @param healthCheck the value for app.health_check
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setHealthCheck(String healthCheck) {
        this.healthCheck = healthCheck == null ? null : healthCheck.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.status
     *
     * @return the value of app.status
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.status
     *
     * @param status the value for app.status
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.is_deleted
     *
     * @return the value of app.is_deleted
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.is_deleted
     *
     * @param isDeleted the value for app.is_deleted
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.gmt_create
     *
     * @return the value of app.gmt_create
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.gmt_create
     *
     * @param gmtCreate the value for app.gmt_create
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.gmt_modified
     *
     * @return the value of app.gmt_modified
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.gmt_modified
     *
     * @param gmtModified the value for app.gmt_modified
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.business_unit
     *
     * @return the value of app.business_unit
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getBusinessUnit() {
        return businessUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.business_unit
     *
     * @param businessUnit the value for app.business_unit
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit == null ? null : businessUnit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.bu
     *
     * @return the value of app.bu
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getBu() {
        return bu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.bu
     *
     * @param bu the value for app.bu
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setBu(String bu) {
        this.bu = bu == null ? null : bu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.department
     *
     * @return the value of app.department
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.department
     *
     * @param department the value for app.department
     *
     * @mbggenerated Thu Feb 02 20:26:01 CST 2023
     */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }
}