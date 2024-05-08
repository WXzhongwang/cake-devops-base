package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostTerminalLogPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.host_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.user_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.username
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.access_token
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String accessToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.connected_time
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Date connectedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.disconnected_time
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Date disconnectedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.close_code
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Integer closeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.screen_path
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String screenPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.is_deleted
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.gmt_create
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.gmt_modified
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.creator
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_log.modifier
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.id
     *
     * @return the value of host_terminal_log.id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.id
     *
     * @param id the value for host_terminal_log.id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.host_id
     *
     * @return the value of host_terminal_log.host_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.host_id
     *
     * @param hostId the value for host_terminal_log.host_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.user_id
     *
     * @return the value of host_terminal_log.user_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.user_id
     *
     * @param userId the value for host_terminal_log.user_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.username
     *
     * @return the value of host_terminal_log.username
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.username
     *
     * @param username the value for host_terminal_log.username
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.access_token
     *
     * @return the value of host_terminal_log.access_token
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.access_token
     *
     * @param accessToken the value for host_terminal_log.access_token
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.connected_time
     *
     * @return the value of host_terminal_log.connected_time
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Date getConnectedTime() {
        return connectedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.connected_time
     *
     * @param connectedTime the value for host_terminal_log.connected_time
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setConnectedTime(Date connectedTime) {
        this.connectedTime = connectedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.disconnected_time
     *
     * @return the value of host_terminal_log.disconnected_time
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Date getDisconnectedTime() {
        return disconnectedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.disconnected_time
     *
     * @param disconnectedTime the value for host_terminal_log.disconnected_time
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setDisconnectedTime(Date disconnectedTime) {
        this.disconnectedTime = disconnectedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.close_code
     *
     * @return the value of host_terminal_log.close_code
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Integer getCloseCode() {
        return closeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.close_code
     *
     * @param closeCode the value for host_terminal_log.close_code
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setCloseCode(Integer closeCode) {
        this.closeCode = closeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.screen_path
     *
     * @return the value of host_terminal_log.screen_path
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getScreenPath() {
        return screenPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.screen_path
     *
     * @param screenPath the value for host_terminal_log.screen_path
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setScreenPath(String screenPath) {
        this.screenPath = screenPath == null ? null : screenPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.is_deleted
     *
     * @return the value of host_terminal_log.is_deleted
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.is_deleted
     *
     * @param isDeleted the value for host_terminal_log.is_deleted
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.gmt_create
     *
     * @return the value of host_terminal_log.gmt_create
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.gmt_create
     *
     * @param gmtCreate the value for host_terminal_log.gmt_create
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.gmt_modified
     *
     * @return the value of host_terminal_log.gmt_modified
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.gmt_modified
     *
     * @param gmtModified the value for host_terminal_log.gmt_modified
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.creator
     *
     * @return the value of host_terminal_log.creator
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.creator
     *
     * @param creator the value for host_terminal_log.creator
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_log.modifier
     *
     * @return the value of host_terminal_log.modifier
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_log.modifier
     *
     * @param modifier the value for host_terminal_log.modifier
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}