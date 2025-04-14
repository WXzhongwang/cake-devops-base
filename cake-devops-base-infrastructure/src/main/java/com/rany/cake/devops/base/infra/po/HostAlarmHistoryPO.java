package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostAlarmHistoryPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_history.id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_history.host_id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_history.alarm_type
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Integer alarmType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_history.alarm_value
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Double alarmValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_history.alarm_time
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Date alarmTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_history.is_deleted
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_history.gmt_create
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_history.gmt_modified
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_history.id
     *
     * @return the value of host_alarm_history.id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_history.id
     *
     * @param id the value for host_alarm_history.id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_history.host_id
     *
     * @return the value of host_alarm_history.host_id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_history.host_id
     *
     * @param hostId the value for host_alarm_history.host_id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_history.alarm_type
     *
     * @return the value of host_alarm_history.alarm_type
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Integer getAlarmType() {
        return alarmType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_history.alarm_type
     *
     * @param alarmType the value for host_alarm_history.alarm_type
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_history.alarm_value
     *
     * @return the value of host_alarm_history.alarm_value
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Double getAlarmValue() {
        return alarmValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_history.alarm_value
     *
     * @param alarmValue the value for host_alarm_history.alarm_value
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setAlarmValue(Double alarmValue) {
        this.alarmValue = alarmValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_history.alarm_time
     *
     * @return the value of host_alarm_history.alarm_time
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Date getAlarmTime() {
        return alarmTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_history.alarm_time
     *
     * @param alarmTime the value for host_alarm_history.alarm_time
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_history.is_deleted
     *
     * @return the value of host_alarm_history.is_deleted
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_history.is_deleted
     *
     * @param isDeleted the value for host_alarm_history.is_deleted
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_history.gmt_create
     *
     * @return the value of host_alarm_history.gmt_create
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_history.gmt_create
     *
     * @param gmtCreate the value for host_alarm_history.gmt_create
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_history.gmt_modified
     *
     * @return the value of host_alarm_history.gmt_modified
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_history.gmt_modified
     *
     * @param gmtModified the value for host_alarm_history.gmt_modified
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}