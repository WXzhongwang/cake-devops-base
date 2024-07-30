package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostMonitorPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.id
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.host_id
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.monitor_status
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private Integer monitorStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.monitor_url
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private String monitorUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.access_token
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private String accessToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.agent_version
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private String agentVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.is_deleted
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.gmt_create
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.gmt_modified
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.creator
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_monitor.modifier
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.id
     *
     * @return the value of host_monitor.id
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.id
     *
     * @param id the value for host_monitor.id
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.host_id
     *
     * @return the value of host_monitor.host_id
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.host_id
     *
     * @param hostId the value for host_monitor.host_id
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.monitor_status
     *
     * @return the value of host_monitor.monitor_status
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public Integer getMonitorStatus() {
        return monitorStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.monitor_status
     *
     * @param monitorStatus the value for host_monitor.monitor_status
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setMonitorStatus(Integer monitorStatus) {
        this.monitorStatus = monitorStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.monitor_url
     *
     * @return the value of host_monitor.monitor_url
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public String getMonitorUrl() {
        return monitorUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.monitor_url
     *
     * @param monitorUrl the value for host_monitor.monitor_url
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setMonitorUrl(String monitorUrl) {
        this.monitorUrl = monitorUrl == null ? null : monitorUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.access_token
     *
     * @return the value of host_monitor.access_token
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.access_token
     *
     * @param accessToken the value for host_monitor.access_token
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.agent_version
     *
     * @return the value of host_monitor.agent_version
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public String getAgentVersion() {
        return agentVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.agent_version
     *
     * @param agentVersion the value for host_monitor.agent_version
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setAgentVersion(String agentVersion) {
        this.agentVersion = agentVersion == null ? null : agentVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.is_deleted
     *
     * @return the value of host_monitor.is_deleted
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.is_deleted
     *
     * @param isDeleted the value for host_monitor.is_deleted
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.gmt_create
     *
     * @return the value of host_monitor.gmt_create
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.gmt_create
     *
     * @param gmtCreate the value for host_monitor.gmt_create
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.gmt_modified
     *
     * @return the value of host_monitor.gmt_modified
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.gmt_modified
     *
     * @param gmtModified the value for host_monitor.gmt_modified
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.creator
     *
     * @return the value of host_monitor.creator
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.creator
     *
     * @param creator the value for host_monitor.creator
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_monitor.modifier
     *
     * @return the value of host_monitor.modifier
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_monitor.modifier
     *
     * @param modifier the value for host_monitor.modifier
     *
     * @mbggenerated Tue Jul 30 21:43:56 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}