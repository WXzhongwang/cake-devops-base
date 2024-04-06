package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class AppEnvPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.cluster_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String clusterId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.app_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.domains
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String domains;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.env_name
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String envName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.env
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String env;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.replicas
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private Integer replicas;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.cpu
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String cpu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.max_cpu
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String maxCpu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.memory
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String memory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.max_memory
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String maxMemory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.auto_scaling
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String autoScaling;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.need_approval
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String needApproval;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.status
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.is_deleted
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.gmt_create
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.gmt_modified
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.creator
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.modifier
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.env_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String envId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.deploy_status
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String deployStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.progress
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String progress;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.id
     *
     * @return the value of app_env.id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.id
     *
     * @param id the value for app_env.id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.cluster_id
     *
     * @return the value of app_env.cluster_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getClusterId() {
        return clusterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.cluster_id
     *
     * @param clusterId the value for app_env.cluster_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setClusterId(String clusterId) {
        this.clusterId = clusterId == null ? null : clusterId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.app_id
     *
     * @return the value of app_env.app_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.app_id
     *
     * @param appId the value for app_env.app_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.domains
     *
     * @return the value of app_env.domains
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getDomains() {
        return domains;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.domains
     *
     * @param domains the value for app_env.domains
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setDomains(String domains) {
        this.domains = domains == null ? null : domains.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.env_name
     *
     * @return the value of app_env.env_name
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getEnvName() {
        return envName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.env_name
     *
     * @param envName the value for app_env.env_name
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setEnvName(String envName) {
        this.envName = envName == null ? null : envName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.env
     *
     * @return the value of app_env.env
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getEnv() {
        return env;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.env
     *
     * @param env the value for app_env.env
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setEnv(String env) {
        this.env = env == null ? null : env.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.replicas
     *
     * @return the value of app_env.replicas
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public Integer getReplicas() {
        return replicas;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.replicas
     *
     * @param replicas the value for app_env.replicas
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setReplicas(Integer replicas) {
        this.replicas = replicas;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.cpu
     *
     * @return the value of app_env.cpu
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getCpu() {
        return cpu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.cpu
     *
     * @param cpu the value for app_env.cpu
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setCpu(String cpu) {
        this.cpu = cpu == null ? null : cpu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.max_cpu
     *
     * @return the value of app_env.max_cpu
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getMaxCpu() {
        return maxCpu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.max_cpu
     *
     * @param maxCpu the value for app_env.max_cpu
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setMaxCpu(String maxCpu) {
        this.maxCpu = maxCpu == null ? null : maxCpu.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.memory
     *
     * @return the value of app_env.memory
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getMemory() {
        return memory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.memory
     *
     * @param memory the value for app_env.memory
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setMemory(String memory) {
        this.memory = memory == null ? null : memory.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.max_memory
     *
     * @return the value of app_env.max_memory
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getMaxMemory() {
        return maxMemory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.max_memory
     *
     * @param maxMemory the value for app_env.max_memory
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setMaxMemory(String maxMemory) {
        this.maxMemory = maxMemory == null ? null : maxMemory.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.auto_scaling
     *
     * @return the value of app_env.auto_scaling
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getAutoScaling() {
        return autoScaling;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.auto_scaling
     *
     * @param autoScaling the value for app_env.auto_scaling
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setAutoScaling(String autoScaling) {
        this.autoScaling = autoScaling == null ? null : autoScaling.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.need_approval
     *
     * @return the value of app_env.need_approval
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getNeedApproval() {
        return needApproval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.need_approval
     *
     * @param needApproval the value for app_env.need_approval
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setNeedApproval(String needApproval) {
        this.needApproval = needApproval == null ? null : needApproval.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.status
     *
     * @return the value of app_env.status
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.status
     *
     * @param status the value for app_env.status
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.is_deleted
     *
     * @return the value of app_env.is_deleted
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.is_deleted
     *
     * @param isDeleted the value for app_env.is_deleted
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.gmt_create
     *
     * @return the value of app_env.gmt_create
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.gmt_create
     *
     * @param gmtCreate the value for app_env.gmt_create
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.gmt_modified
     *
     * @return the value of app_env.gmt_modified
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.gmt_modified
     *
     * @param gmtModified the value for app_env.gmt_modified
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.creator
     *
     * @return the value of app_env.creator
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.creator
     *
     * @param creator the value for app_env.creator
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.modifier
     *
     * @return the value of app_env.modifier
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.modifier
     *
     * @param modifier the value for app_env.modifier
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.env_id
     *
     * @return the value of app_env.env_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getEnvId() {
        return envId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.env_id
     *
     * @param envId the value for app_env.env_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setEnvId(String envId) {
        this.envId = envId == null ? null : envId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.deploy_status
     *
     * @return the value of app_env.deploy_status
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getDeployStatus() {
        return deployStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.deploy_status
     *
     * @param deployStatus the value for app_env.deploy_status
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setDeployStatus(String deployStatus) {
        this.deployStatus = deployStatus == null ? null : deployStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.progress
     *
     * @return the value of app_env.progress
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getProgress() {
        return progress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.progress
     *
     * @param progress the value for app_env.progress
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setProgress(String progress) {
        this.progress = progress == null ? null : progress.trim();
    }
}