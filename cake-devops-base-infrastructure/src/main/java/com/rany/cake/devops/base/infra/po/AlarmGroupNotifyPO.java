package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class AlarmGroupNotifyPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_notify.id
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_notify.group_id
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    private Long groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_notify.notify_id
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    private Long notifyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_notify.notify_type
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    private Integer notifyType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_notify.is_deleted
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_notify.gmt_create
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_notify.gmt_modified
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_notify.creator
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group_notify.modifier
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_notify.id
     *
     * @return the value of alarm_group_notify.id
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_notify.id
     *
     * @param id the value for alarm_group_notify.id
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_notify.group_id
     *
     * @return the value of alarm_group_notify.group_id
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_notify.group_id
     *
     * @param groupId the value for alarm_group_notify.group_id
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_notify.notify_id
     *
     * @return the value of alarm_group_notify.notify_id
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public Long getNotifyId() {
        return notifyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_notify.notify_id
     *
     * @param notifyId the value for alarm_group_notify.notify_id
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_notify.notify_type
     *
     * @return the value of alarm_group_notify.notify_type
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public Integer getNotifyType() {
        return notifyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_notify.notify_type
     *
     * @param notifyType the value for alarm_group_notify.notify_type
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_notify.is_deleted
     *
     * @return the value of alarm_group_notify.is_deleted
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_notify.is_deleted
     *
     * @param isDeleted the value for alarm_group_notify.is_deleted
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_notify.gmt_create
     *
     * @return the value of alarm_group_notify.gmt_create
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_notify.gmt_create
     *
     * @param gmtCreate the value for alarm_group_notify.gmt_create
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_notify.gmt_modified
     *
     * @return the value of alarm_group_notify.gmt_modified
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_notify.gmt_modified
     *
     * @param gmtModified the value for alarm_group_notify.gmt_modified
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_notify.creator
     *
     * @return the value of alarm_group_notify.creator
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_notify.creator
     *
     * @param creator the value for alarm_group_notify.creator
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group_notify.modifier
     *
     * @return the value of alarm_group_notify.modifier
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group_notify.modifier
     *
     * @param modifier the value for alarm_group_notify.modifier
     *
     * @mbggenerated Wed Jul 24 08:17:41 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}