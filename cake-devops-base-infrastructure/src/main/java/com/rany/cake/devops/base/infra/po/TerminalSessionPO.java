package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class TerminalSessionPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.id
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.session_id
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private String sessionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.account_id
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private Long accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.remote_addr
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private String remoteAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.session_closed
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private Boolean sessionClosed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.close_time
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private Date closeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.server_hostname
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private String serverHostname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.server_addr
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private String serverAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.session_type
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private String sessionType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.is_deleted
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.gmt_create
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.gmt_modified
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.creator
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session.modifier
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.id
     *
     * @return the value of terminal_session.id
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.id
     *
     * @param id the value for terminal_session.id
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.session_id
     *
     * @return the value of terminal_session.session_id
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.session_id
     *
     * @param sessionId the value for terminal_session.session_id
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.account_id
     *
     * @return the value of terminal_session.account_id
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.account_id
     *
     * @param accountId the value for terminal_session.account_id
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.remote_addr
     *
     * @return the value of terminal_session.remote_addr
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.remote_addr
     *
     * @param remoteAddr the value for terminal_session.remote_addr
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr == null ? null : remoteAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.session_closed
     *
     * @return the value of terminal_session.session_closed
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public Boolean getSessionClosed() {
        return sessionClosed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.session_closed
     *
     * @param sessionClosed the value for terminal_session.session_closed
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setSessionClosed(Boolean sessionClosed) {
        this.sessionClosed = sessionClosed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.close_time
     *
     * @return the value of terminal_session.close_time
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public Date getCloseTime() {
        return closeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.close_time
     *
     * @param closeTime the value for terminal_session.close_time
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.server_hostname
     *
     * @return the value of terminal_session.server_hostname
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public String getServerHostname() {
        return serverHostname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.server_hostname
     *
     * @param serverHostname the value for terminal_session.server_hostname
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setServerHostname(String serverHostname) {
        this.serverHostname = serverHostname == null ? null : serverHostname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.server_addr
     *
     * @return the value of terminal_session.server_addr
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public String getServerAddr() {
        return serverAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.server_addr
     *
     * @param serverAddr the value for terminal_session.server_addr
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr == null ? null : serverAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.session_type
     *
     * @return the value of terminal_session.session_type
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public String getSessionType() {
        return sessionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.session_type
     *
     * @param sessionType the value for terminal_session.session_type
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setSessionType(String sessionType) {
        this.sessionType = sessionType == null ? null : sessionType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.is_deleted
     *
     * @return the value of terminal_session.is_deleted
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.is_deleted
     *
     * @param isDeleted the value for terminal_session.is_deleted
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.gmt_create
     *
     * @return the value of terminal_session.gmt_create
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.gmt_create
     *
     * @param gmtCreate the value for terminal_session.gmt_create
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.gmt_modified
     *
     * @return the value of terminal_session.gmt_modified
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.gmt_modified
     *
     * @param gmtModified the value for terminal_session.gmt_modified
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.creator
     *
     * @return the value of terminal_session.creator
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.creator
     *
     * @param creator the value for terminal_session.creator
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session.modifier
     *
     * @return the value of terminal_session.modifier
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session.modifier
     *
     * @param modifier the value for terminal_session.modifier
     *
     * @mbggenerated Tue Jan 16 22:02:50 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}