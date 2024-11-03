package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class AppPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.id
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.app_id
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.app_name
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String appName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.description
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.repo
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String repo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.default_branch
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String defaultBranch;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.language
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String language;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.develop_mode
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String developMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.owner
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private Long owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.health_check
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String healthCheck;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.status
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.is_deleted
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.gmt_create
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.gmt_modified
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.department_abbreviation
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String departmentAbbreviation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.department
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String department;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.creator
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.modifier
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.webhook
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String webhook;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.code_platform
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String codePlatform;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.connection_string
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String connectionString;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app.token
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    private String token;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.id
     *
     * @return the value of app.id
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
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
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    public void setWebhook(String webhook) {
        this.webhook = webhook == null ? null : webhook.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.code_platform
     *
     * @return the value of app.code_platform
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    public String getCodePlatform() {
        return codePlatform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.code_platform
     *
     * @param codePlatform the value for app.code_platform
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    public void setCodePlatform(String codePlatform) {
        this.codePlatform = codePlatform == null ? null : codePlatform.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.connection_string
     *
     * @return the value of app.connection_string
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.connection_string
     *
     * @param connectionString the value for app.connection_string
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString == null ? null : connectionString.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app.token
     *
     * @return the value of app.token
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app.token
     *
     * @param token the value for app.token
     *
     * @mbggenerated Sun Nov 03 10:48:26 CST 2024
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
}