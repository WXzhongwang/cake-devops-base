package com.rany.cake.devops.base.infra.po;

import java.math.BigDecimal;
import java.util.Date;

public class NamespacePO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.id
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.cluster_id
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private Long clusterId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.name
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.request_cpu
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private BigDecimal requestCpu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.request_memory
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private BigDecimal requestMemory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.limit_cpu
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private BigDecimal limitCpu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.limit_memory
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private BigDecimal limitMemory;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.max_pods
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private Integer maxPods;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.max_gpu
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private BigDecimal maxGpu;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.max_services
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private Integer maxServices;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.status
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.is_deleted
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.gmt_create
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column namespace.gmt_modified
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.id
     *
     * @return the value of namespace.id
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.cluster_id
     *
     * @return the value of namespace.cluster_id
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    public Long getClusterId() {
        return clusterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column namespace.cluster_id
     *
     * @param clusterId the value for namespace.cluster_id
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column namespace.name
     *
     * @return the value of namespace.name
     *
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
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
     * @mbggenerated Wed Nov 01 21:42:10 CST 2023
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}