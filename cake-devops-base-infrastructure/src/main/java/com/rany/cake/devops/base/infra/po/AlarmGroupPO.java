package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class AlarmGroupPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group.id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group.group_name
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String groupName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group.group_description
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String groupDescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group.is_deleted
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group.gmt_create
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group.gmt_modified
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group.creator
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column alarm_group.modifier
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group.id
     *
     * @return the value of alarm_group.id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group.id
     *
     * @param id the value for alarm_group.id
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group.group_name
     *
     * @return the value of alarm_group.group_name
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group.group_name
     *
     * @param groupName the value for alarm_group.group_name
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group.group_description
     *
     * @return the value of alarm_group.group_description
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group.group_description
     *
     * @param groupDescription the value for alarm_group.group_description
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription == null ? null : groupDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group.is_deleted
     *
     * @return the value of alarm_group.is_deleted
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group.is_deleted
     *
     * @param isDeleted the value for alarm_group.is_deleted
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group.gmt_create
     *
     * @return the value of alarm_group.gmt_create
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group.gmt_create
     *
     * @param gmtCreate the value for alarm_group.gmt_create
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group.gmt_modified
     *
     * @return the value of alarm_group.gmt_modified
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group.gmt_modified
     *
     * @param gmtModified the value for alarm_group.gmt_modified
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group.creator
     *
     * @return the value of alarm_group.creator
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group.creator
     *
     * @param creator the value for alarm_group.creator
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column alarm_group.modifier
     *
     * @return the value of alarm_group.modifier
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column alarm_group.modifier
     *
     * @param modifier the value for alarm_group.modifier
     *
     * @mbggenerated Sat Nov 09 21:28:44 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}