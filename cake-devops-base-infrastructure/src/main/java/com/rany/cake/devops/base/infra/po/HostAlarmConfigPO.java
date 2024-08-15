package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostAlarmConfigPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.id
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.host_id
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.alarm_type
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private Integer alarmType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.alarm_threshold
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private Double alarmThreshold;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.trigger_threshold
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private Integer triggerThreshold;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.notify_silence
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private Integer notifySilence;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.is_deleted
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.gmt_create
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.gmt_modified
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.creator
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_config.modifier
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.id
     *
     * @return the value of host_alarm_config.id
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.id
     *
     * @param id the value for host_alarm_config.id
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.host_id
     *
     * @return the value of host_alarm_config.host_id
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.host_id
     *
     * @param hostId the value for host_alarm_config.host_id
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.alarm_type
     *
     * @return the value of host_alarm_config.alarm_type
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public Integer getAlarmType() {
        return alarmType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.alarm_type
     *
     * @param alarmType the value for host_alarm_config.alarm_type
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.alarm_threshold
     *
     * @return the value of host_alarm_config.alarm_threshold
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public Double getAlarmThreshold() {
        return alarmThreshold;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.alarm_threshold
     *
     * @param alarmThreshold the value for host_alarm_config.alarm_threshold
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setAlarmThreshold(Double alarmThreshold) {
        this.alarmThreshold = alarmThreshold;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.trigger_threshold
     *
     * @return the value of host_alarm_config.trigger_threshold
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public Integer getTriggerThreshold() {
        return triggerThreshold;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.trigger_threshold
     *
     * @param triggerThreshold the value for host_alarm_config.trigger_threshold
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setTriggerThreshold(Integer triggerThreshold) {
        this.triggerThreshold = triggerThreshold;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.notify_silence
     *
     * @return the value of host_alarm_config.notify_silence
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public Integer getNotifySilence() {
        return notifySilence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.notify_silence
     *
     * @param notifySilence the value for host_alarm_config.notify_silence
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setNotifySilence(Integer notifySilence) {
        this.notifySilence = notifySilence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.is_deleted
     *
     * @return the value of host_alarm_config.is_deleted
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.is_deleted
     *
     * @param isDeleted the value for host_alarm_config.is_deleted
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.gmt_create
     *
     * @return the value of host_alarm_config.gmt_create
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.gmt_create
     *
     * @param gmtCreate the value for host_alarm_config.gmt_create
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.gmt_modified
     *
     * @return the value of host_alarm_config.gmt_modified
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.gmt_modified
     *
     * @param gmtModified the value for host_alarm_config.gmt_modified
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.creator
     *
     * @return the value of host_alarm_config.creator
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.creator
     *
     * @param creator the value for host_alarm_config.creator
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_config.modifier
     *
     * @return the value of host_alarm_config.modifier
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_config.modifier
     *
     * @param modifier the value for host_alarm_config.modifier
     *
     * @mbggenerated Thu Aug 15 21:28:25 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}