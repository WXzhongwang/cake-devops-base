package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class DeployHistoryPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.gmt_create
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.gmt_modified
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.env_id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String envId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.app_id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.start_time
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.end_time
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.is_deleted
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.deploy_status
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String deployStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.image_path
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String imagePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.deployment_name
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String deploymentName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.content
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.creator
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.modifier
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.release_id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String releaseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column deploy_history.pipe_key
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String pipeKey;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.id
     *
     * @return the value of deploy_history.id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.id
     *
     * @param id the value for deploy_history.id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.gmt_create
     *
     * @return the value of deploy_history.gmt_create
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.gmt_create
     *
     * @param gmtCreate the value for deploy_history.gmt_create
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.gmt_modified
     *
     * @return the value of deploy_history.gmt_modified
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.gmt_modified
     *
     * @param gmtModified the value for deploy_history.gmt_modified
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.env_id
     *
     * @return the value of deploy_history.env_id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getEnvId() {
        return envId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.env_id
     *
     * @param envId the value for deploy_history.env_id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setEnvId(String envId) {
        this.envId = envId == null ? null : envId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.app_id
     *
     * @return the value of deploy_history.app_id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.app_id
     *
     * @param appId the value for deploy_history.app_id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.start_time
     *
     * @return the value of deploy_history.start_time
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.start_time
     *
     * @param startTime the value for deploy_history.start_time
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.end_time
     *
     * @return the value of deploy_history.end_time
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.end_time
     *
     * @param endTime the value for deploy_history.end_time
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.is_deleted
     *
     * @return the value of deploy_history.is_deleted
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.is_deleted
     *
     * @param isDeleted the value for deploy_history.is_deleted
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.deploy_status
     *
     * @return the value of deploy_history.deploy_status
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getDeployStatus() {
        return deployStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.deploy_status
     *
     * @param deployStatus the value for deploy_history.deploy_status
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setDeployStatus(String deployStatus) {
        this.deployStatus = deployStatus == null ? null : deployStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.image_path
     *
     * @return the value of deploy_history.image_path
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.image_path
     *
     * @param imagePath the value for deploy_history.image_path
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.deployment_name
     *
     * @return the value of deploy_history.deployment_name
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getDeploymentName() {
        return deploymentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.deployment_name
     *
     * @param deploymentName the value for deploy_history.deployment_name
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setDeploymentName(String deploymentName) {
        this.deploymentName = deploymentName == null ? null : deploymentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.content
     *
     * @return the value of deploy_history.content
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.content
     *
     * @param content the value for deploy_history.content
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.creator
     *
     * @return the value of deploy_history.creator
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.creator
     *
     * @param creator the value for deploy_history.creator
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.modifier
     *
     * @return the value of deploy_history.modifier
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.modifier
     *
     * @param modifier the value for deploy_history.modifier
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.release_id
     *
     * @return the value of deploy_history.release_id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getReleaseId() {
        return releaseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.release_id
     *
     * @param releaseId the value for deploy_history.release_id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId == null ? null : releaseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column deploy_history.pipe_key
     *
     * @return the value of deploy_history.pipe_key
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getPipeKey() {
        return pipeKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column deploy_history.pipe_key
     *
     * @param pipeKey the value for deploy_history.pipe_key
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setPipeKey(String pipeKey) {
        this.pipeKey = pipeKey == null ? null : pipeKey.trim();
    }
}