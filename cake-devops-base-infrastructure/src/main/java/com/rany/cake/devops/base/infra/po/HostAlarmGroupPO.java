package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostAlarmGroupPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_group.id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_group.host_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_group.alarm_group_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private Long alarmGroupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_group.is_deleted
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_group.gmt_create
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_group.gmt_modified
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_group.creator
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_alarm_group.modifier
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_group.id
     *
     * @return the value of host_alarm_group.id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_group.id
     *
     * @param id the value for host_alarm_group.id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_group.host_id
     *
     * @return the value of host_alarm_group.host_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_group.host_id
     *
     * @param hostId the value for host_alarm_group.host_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_group.alarm_group_id
     *
     * @return the value of host_alarm_group.alarm_group_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public Long getAlarmGroupId() {
        return alarmGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_group.alarm_group_id
     *
     * @param alarmGroupId the value for host_alarm_group.alarm_group_id
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setAlarmGroupId(Long alarmGroupId) {
        this.alarmGroupId = alarmGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_group.is_deleted
     *
     * @return the value of host_alarm_group.is_deleted
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_group.is_deleted
     *
     * @param isDeleted the value for host_alarm_group.is_deleted
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_group.gmt_create
     *
     * @return the value of host_alarm_group.gmt_create
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_group.gmt_create
     *
     * @param gmtCreate the value for host_alarm_group.gmt_create
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_group.gmt_modified
     *
     * @return the value of host_alarm_group.gmt_modified
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_group.gmt_modified
     *
     * @param gmtModified the value for host_alarm_group.gmt_modified
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_group.creator
     *
     * @return the value of host_alarm_group.creator
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_group.creator
     *
     * @param creator the value for host_alarm_group.creator
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_alarm_group.modifier
     *
     * @return the value of host_alarm_group.modifier
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_alarm_group.modifier
     *
     * @param modifier the value for host_alarm_group.modifier
     *
     * @mbggenerated Sat Apr 06 20:46:45 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}