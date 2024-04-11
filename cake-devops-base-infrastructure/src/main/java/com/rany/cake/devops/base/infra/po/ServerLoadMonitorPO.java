package com.rany.cake.devops.base.infra.po;

import java.math.BigDecimal;
import java.util.Date;

public class ServerLoadMonitorPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.host_id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.cpu_rate
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private BigDecimal cpuRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.mem_rate
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private BigDecimal memRate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.one_min_load_avg
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private BigDecimal oneMinLoadAvg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.five_min_load_avg
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private BigDecimal fiveMinLoadAvg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.fif_min_load_avg
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private BigDecimal fifMinLoadAvg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.is_deleted
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.gmt_create
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.gmt_modified
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.creator
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_load_monitor.modifier
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.id
     *
     * @return the value of server_load_monitor.id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.id
     *
     * @param id the value for server_load_monitor.id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.host_id
     *
     * @return the value of server_load_monitor.host_id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.host_id
     *
     * @param hostId the value for server_load_monitor.host_id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.cpu_rate
     *
     * @return the value of server_load_monitor.cpu_rate
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public BigDecimal getCpuRate() {
        return cpuRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.cpu_rate
     *
     * @param cpuRate the value for server_load_monitor.cpu_rate
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setCpuRate(BigDecimal cpuRate) {
        this.cpuRate = cpuRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.mem_rate
     *
     * @return the value of server_load_monitor.mem_rate
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public BigDecimal getMemRate() {
        return memRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.mem_rate
     *
     * @param memRate the value for server_load_monitor.mem_rate
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setMemRate(BigDecimal memRate) {
        this.memRate = memRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.one_min_load_avg
     *
     * @return the value of server_load_monitor.one_min_load_avg
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public BigDecimal getOneMinLoadAvg() {
        return oneMinLoadAvg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.one_min_load_avg
     *
     * @param oneMinLoadAvg the value for server_load_monitor.one_min_load_avg
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setOneMinLoadAvg(BigDecimal oneMinLoadAvg) {
        this.oneMinLoadAvg = oneMinLoadAvg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.five_min_load_avg
     *
     * @return the value of server_load_monitor.five_min_load_avg
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public BigDecimal getFiveMinLoadAvg() {
        return fiveMinLoadAvg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.five_min_load_avg
     *
     * @param fiveMinLoadAvg the value for server_load_monitor.five_min_load_avg
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setFiveMinLoadAvg(BigDecimal fiveMinLoadAvg) {
        this.fiveMinLoadAvg = fiveMinLoadAvg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.fif_min_load_avg
     *
     * @return the value of server_load_monitor.fif_min_load_avg
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public BigDecimal getFifMinLoadAvg() {
        return fifMinLoadAvg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.fif_min_load_avg
     *
     * @param fifMinLoadAvg the value for server_load_monitor.fif_min_load_avg
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setFifMinLoadAvg(BigDecimal fifMinLoadAvg) {
        this.fifMinLoadAvg = fifMinLoadAvg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.is_deleted
     *
     * @return the value of server_load_monitor.is_deleted
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.is_deleted
     *
     * @param isDeleted the value for server_load_monitor.is_deleted
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.gmt_create
     *
     * @return the value of server_load_monitor.gmt_create
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.gmt_create
     *
     * @param gmtCreate the value for server_load_monitor.gmt_create
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.gmt_modified
     *
     * @return the value of server_load_monitor.gmt_modified
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.gmt_modified
     *
     * @param gmtModified the value for server_load_monitor.gmt_modified
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.creator
     *
     * @return the value of server_load_monitor.creator
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.creator
     *
     * @param creator the value for server_load_monitor.creator
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_load_monitor.modifier
     *
     * @return the value of server_load_monitor.modifier
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_load_monitor.modifier
     *
     * @param modifier the value for server_load_monitor.modifier
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}