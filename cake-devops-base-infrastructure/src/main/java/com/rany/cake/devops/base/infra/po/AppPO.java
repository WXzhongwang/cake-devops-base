package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class AppPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.id
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.app_id
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.app_name
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String appName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.description
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.repo
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String repo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.default_branch
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String defaultBranch;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.language
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String language;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.develop_mode
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String developMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.owner
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private Long owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.health_check
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String healthCheck;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.status
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.is_deleted
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.gmt_create
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.gmt_modified
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.department_abbreviation
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String departmentAbbreviation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.department
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String department;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.creator
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.modifier
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.webhook
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    private String webhook;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.id
     *
     * @return the value of app.id
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.app_id
     *
     * @return the value of app.app_id
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.app_id
     *
     * @param appId the value for app.app_id
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.app_name
     *
     * @return the value of app.app_name
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.description
     *
     * @return the value of app.description
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.department_abbreviation
     *
     * @return the value of app.department_abbreviation
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public String getDepartmentAbbreviation() {
        return departmentAbbreviation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.department_abbreviation
     *
     * @param departmentAbbreviation the value for app.department_abbreviation
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public void setDepartmentAbbreviation(String departmentAbbreviation) {
        this.departmentAbbreviation = departmentAbbreviation == null ? null : departmentAbbreviation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.department
     *
     * @return the value of app.department
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
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
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.creator
     *
     * @return the value of app.creator
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.creator
     *
     * @param creator the value for app.creator
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.modifier
     *
     * @return the value of app.modifier
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.modifier
     *
     * @param modifier the value for app.modifier
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.webhook
     *
     * @return the value of app.webhook
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public String getWebhook() {
        return webhook;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.webhook
     *
     * @param webhook the value for app.webhook
     *
     * @mbggenerated Sat Apr 06 11:34:02 CST 2024
     */
    public void setWebhook(String webhook) {
        this.webhook = webhook == null ? null : webhook.trim();
    }
}