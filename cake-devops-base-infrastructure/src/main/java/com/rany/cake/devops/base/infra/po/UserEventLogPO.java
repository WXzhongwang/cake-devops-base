package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class UserEventLogPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.user_id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.username
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.event_classify
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Integer eventClassify;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.event_type
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Integer eventType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.exec_result
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Integer execResult;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.is_deleted
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.gmt_create
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.gmt_modified
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.creator
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_event_log.modifier
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.id
     *
     * @return the value of user_event_log.id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.id
     *
     * @param id the value for user_event_log.id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.user_id
     *
     * @return the value of user_event_log.user_id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.user_id
     *
     * @param userId the value for user_event_log.user_id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.username
     *
     * @return the value of user_event_log.username
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.username
     *
     * @param username the value for user_event_log.username
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.event_classify
     *
     * @return the value of user_event_log.event_classify
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Integer getEventClassify() {
        return eventClassify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.event_classify
     *
     * @param eventClassify the value for user_event_log.event_classify
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setEventClassify(Integer eventClassify) {
        this.eventClassify = eventClassify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.event_type
     *
     * @return the value of user_event_log.event_type
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Integer getEventType() {
        return eventType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.event_type
     *
     * @param eventType the value for user_event_log.event_type
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.exec_result
     *
     * @return the value of user_event_log.exec_result
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Integer getExecResult() {
        return execResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.exec_result
     *
     * @param execResult the value for user_event_log.exec_result
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setExecResult(Integer execResult) {
        this.execResult = execResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.is_deleted
     *
     * @return the value of user_event_log.is_deleted
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.is_deleted
     *
     * @param isDeleted the value for user_event_log.is_deleted
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.gmt_create
     *
     * @return the value of user_event_log.gmt_create
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.gmt_create
     *
     * @param gmtCreate the value for user_event_log.gmt_create
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.gmt_modified
     *
     * @return the value of user_event_log.gmt_modified
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.gmt_modified
     *
     * @param gmtModified the value for user_event_log.gmt_modified
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.creator
     *
     * @return the value of user_event_log.creator
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.creator
     *
     * @param creator the value for user_event_log.creator
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_event_log.modifier
     *
     * @return the value of user_event_log.modifier
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_event_log.modifier
     *
     * @param modifier the value for user_event_log.modifier
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}