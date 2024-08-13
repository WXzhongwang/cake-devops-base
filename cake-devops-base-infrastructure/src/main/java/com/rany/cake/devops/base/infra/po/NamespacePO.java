package com.rany.cake.devops.base.infra.po;

import java.math.BigDecimal;
import java.util.Date;

public class NamespacePO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.namespace_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String namespaceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.cluster_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String clusterId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.name
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.request_cpu
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private BigDecimal requestCpu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.request_memory
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private BigDecimal requestMemory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.limit_cpu
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private BigDecimal limitCpu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.limit_memory
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private BigDecimal limitMemory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.max_pods
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private Integer maxPods;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.max_gpu
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private BigDecimal maxGpu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.max_services
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private Integer maxServices;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.status
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.is_deleted
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.gmt_create
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.gmt_modified
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.creator
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.modifier
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.id
     *
     * @return the value of namespace.id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.id
     *
     * @param id the value for namespace.id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.namespace_id
     *
     * @return the value of namespace.namespace_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getNamespaceId() {
        return namespaceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.namespace_id
     *
     * @param namespaceId the value for namespace.namespace_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId == null ? null : namespaceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.cluster_id
     *
     * @return the value of namespace.cluster_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getClusterId() {
        return clusterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.cluster_id
     *
     * @param clusterId the value for namespace.cluster_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setClusterId(String clusterId) {
        this.clusterId = clusterId == null ? null : clusterId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.name
     *
     * @return the value of namespace.name
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.name
     *
     * @param name the value for namespace.name
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.request_cpu
     *
     * @return the value of namespace.request_cpu
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public BigDecimal getRequestCpu() {
        return requestCpu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.request_cpu
     *
     * @param requestCpu the value for namespace.request_cpu
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setRequestCpu(BigDecimal requestCpu) {
        this.requestCpu = requestCpu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.request_memory
     *
     * @return the value of namespace.request_memory
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public BigDecimal getRequestMemory() {
        return requestMemory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.request_memory
     *
     * @param requestMemory the value for namespace.request_memory
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setRequestMemory(BigDecimal requestMemory) {
        this.requestMemory = requestMemory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.limit_cpu
     *
     * @return the value of namespace.limit_cpu
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public BigDecimal getLimitCpu() {
        return limitCpu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.limit_cpu
     *
     * @param limitCpu the value for namespace.limit_cpu
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setLimitCpu(BigDecimal limitCpu) {
        this.limitCpu = limitCpu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.limit_memory
     *
     * @return the value of namespace.limit_memory
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public BigDecimal getLimitMemory() {
        return limitMemory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.limit_memory
     *
     * @param limitMemory the value for namespace.limit_memory
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setLimitMemory(BigDecimal limitMemory) {
        this.limitMemory = limitMemory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.max_pods
     *
     * @return the value of namespace.max_pods
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public Integer getMaxPods() {
        return maxPods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.max_pods
     *
     * @param maxPods the value for namespace.max_pods
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setMaxPods(Integer maxPods) {
        this.maxPods = maxPods;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.max_gpu
     *
     * @return the value of namespace.max_gpu
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public BigDecimal getMaxGpu() {
        return maxGpu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.max_gpu
     *
     * @param maxGpu the value for namespace.max_gpu
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setMaxGpu(BigDecimal maxGpu) {
        this.maxGpu = maxGpu;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.max_services
     *
     * @return the value of namespace.max_services
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public Integer getMaxServices() {
        return maxServices;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.max_services
     *
     * @param maxServices the value for namespace.max_services
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setMaxServices(Integer maxServices) {
        this.maxServices = maxServices;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.status
     *
     * @return the value of namespace.status
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.status
     *
     * @param status the value for namespace.status
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.is_deleted
     *
     * @return the value of namespace.is_deleted
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.is_deleted
     *
     * @param isDeleted the value for namespace.is_deleted
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.gmt_create
     *
     * @return the value of namespace.gmt_create
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.gmt_create
     *
     * @param gmtCreate the value for namespace.gmt_create
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.gmt_modified
     *
     * @return the value of namespace.gmt_modified
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.gmt_modified
     *
     * @param gmtModified the value for namespace.gmt_modified
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.creator
     *
     * @return the value of namespace.creator
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.creator
     *
     * @param creator the value for namespace.creator
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.modifier
     *
     * @return the value of namespace.modifier
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.modifier
     *
     * @param modifier the value for namespace.modifier
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}