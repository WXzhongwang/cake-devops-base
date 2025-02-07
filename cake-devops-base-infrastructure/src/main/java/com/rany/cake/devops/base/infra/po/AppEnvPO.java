package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class AppEnvPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.id
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.cluster_id
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String clusterId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.app_id
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.domains
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String domains;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.env_name
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String envName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.env
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String env;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.replicas
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private Integer replicas;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.cpu
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String cpu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.max_cpu
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String maxCpu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.memory
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String memory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.max_memory
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String maxMemory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.auto_scaling
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String autoScaling;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.need_approval
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String needApproval;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.status
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.is_deleted
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.gmt_create
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.gmt_modified
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.creator
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.modifier
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.env_id
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String envId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.deploy_status
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String deployStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.progress
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String progress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.config_map
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String configMap;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.env_vars
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String envVars;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.ingress
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String ingress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.service
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String service;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.deployment
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String deployment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.custom_build_script
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String customBuildScript;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column app_env.secret_map
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String secretMap;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.id
     *
     * @return the value of app_env.id
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
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
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setProgress(String progress) {
        this.progress = progress == null ? null : progress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.config_map
     *
     * @return the value of app_env.config_map
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getConfigMap() {
        return configMap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.config_map
     *
     * @param configMap the value for app_env.config_map
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setConfigMap(String configMap) {
        this.configMap = configMap == null ? null : configMap.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.env_vars
     *
     * @return the value of app_env.env_vars
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getEnvVars() {
        return envVars;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.env_vars
     *
     * @param envVars the value for app_env.env_vars
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setEnvVars(String envVars) {
        this.envVars = envVars == null ? null : envVars.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.ingress
     *
     * @return the value of app_env.ingress
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getIngress() {
        return ingress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.ingress
     *
     * @param ingress the value for app_env.ingress
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setIngress(String ingress) {
        this.ingress = ingress == null ? null : ingress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.service
     *
     * @return the value of app_env.service
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getService() {
        return service;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.service
     *
     * @param service the value for app_env.service
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setService(String service) {
        this.service = service == null ? null : service.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.deployment
     *
     * @return the value of app_env.deployment
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getDeployment() {
        return deployment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.deployment
     *
     * @param deployment the value for app_env.deployment
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setDeployment(String deployment) {
        this.deployment = deployment == null ? null : deployment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.custom_build_script
     *
     * @return the value of app_env.custom_build_script
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getCustomBuildScript() {
        return customBuildScript;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.custom_build_script
     *
     * @param customBuildScript the value for app_env.custom_build_script
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setCustomBuildScript(String customBuildScript) {
        this.customBuildScript = customBuildScript == null ? null : customBuildScript.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column app_env.secret_map
     *
     * @return the value of app_env.secret_map
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getSecretMap() {
        return secretMap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column app_env.secret_map
     *
     * @param secretMap the value for app_env.secret_map
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setSecretMap(String secretMap) {
        this.secretMap = secretMap == null ? null : secretMap.trim();
    }
}