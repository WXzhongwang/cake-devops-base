package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class TerminalSessionInstancePO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.session_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String sessionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.instance_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String instanceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.duplicate_instance_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String duplicateInstanceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.instance_session_type
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String instanceSessionType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.login_user
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String loginUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.host_ip
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String hostIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.output_size
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Long outputSize;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.instance_closed
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Byte instanceClosed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.open_time
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Date openTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.close_time
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Date closeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.is_deleted
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.gmt_create
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.gmt_modified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.creator
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance.modifier
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.id
     *
     * @return the value of terminal_session_instance.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.id
     *
     * @param id the value for terminal_session_instance.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.session_id
     *
     * @return the value of terminal_session_instance.session_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.session_id
     *
     * @param sessionId the value for terminal_session_instance.session_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId == null ? null : sessionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.instance_id
     *
     * @return the value of terminal_session_instance.instance_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.instance_id
     *
     * @param instanceId the value for terminal_session_instance.instance_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId == null ? null : instanceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.duplicate_instance_id
     *
     * @return the value of terminal_session_instance.duplicate_instance_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getDuplicateInstanceId() {
        return duplicateInstanceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.duplicate_instance_id
     *
     * @param duplicateInstanceId the value for terminal_session_instance.duplicate_instance_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setDuplicateInstanceId(String duplicateInstanceId) {
        this.duplicateInstanceId = duplicateInstanceId == null ? null : duplicateInstanceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.instance_session_type
     *
     * @return the value of terminal_session_instance.instance_session_type
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getInstanceSessionType() {
        return instanceSessionType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.instance_session_type
     *
     * @param instanceSessionType the value for terminal_session_instance.instance_session_type
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setInstanceSessionType(String instanceSessionType) {
        this.instanceSessionType = instanceSessionType == null ? null : instanceSessionType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.login_user
     *
     * @return the value of terminal_session_instance.login_user
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getLoginUser() {
        return loginUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.login_user
     *
     * @param loginUser the value for terminal_session_instance.login_user
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser == null ? null : loginUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.host_ip
     *
     * @return the value of terminal_session_instance.host_ip
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getHostIp() {
        return hostIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.host_ip
     *
     * @param hostIp the value for terminal_session_instance.host_ip
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setHostIp(String hostIp) {
        this.hostIp = hostIp == null ? null : hostIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.output_size
     *
     * @return the value of terminal_session_instance.output_size
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Long getOutputSize() {
        return outputSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.output_size
     *
     * @param outputSize the value for terminal_session_instance.output_size
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setOutputSize(Long outputSize) {
        this.outputSize = outputSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.instance_closed
     *
     * @return the value of terminal_session_instance.instance_closed
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Byte getInstanceClosed() {
        return instanceClosed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.instance_closed
     *
     * @param instanceClosed the value for terminal_session_instance.instance_closed
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setInstanceClosed(Byte instanceClosed) {
        this.instanceClosed = instanceClosed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.open_time
     *
     * @return the value of terminal_session_instance.open_time
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Date getOpenTime() {
        return openTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.open_time
     *
     * @param openTime the value for terminal_session_instance.open_time
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.close_time
     *
     * @return the value of terminal_session_instance.close_time
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Date getCloseTime() {
        return closeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.close_time
     *
     * @param closeTime the value for terminal_session_instance.close_time
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.is_deleted
     *
     * @return the value of terminal_session_instance.is_deleted
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.is_deleted
     *
     * @param isDeleted the value for terminal_session_instance.is_deleted
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.gmt_create
     *
     * @return the value of terminal_session_instance.gmt_create
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.gmt_create
     *
     * @param gmtCreate the value for terminal_session_instance.gmt_create
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.gmt_modified
     *
     * @return the value of terminal_session_instance.gmt_modified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.gmt_modified
     *
     * @param gmtModified the value for terminal_session_instance.gmt_modified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.creator
     *
     * @return the value of terminal_session_instance.creator
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.creator
     *
     * @param creator the value for terminal_session_instance.creator
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance.modifier
     *
     * @return the value of terminal_session_instance.modifier
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance.modifier
     *
     * @param modifier the value for terminal_session_instance.modifier
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}