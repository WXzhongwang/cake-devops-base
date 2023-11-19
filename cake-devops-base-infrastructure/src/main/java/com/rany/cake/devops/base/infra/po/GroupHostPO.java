package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class GroupHostPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.group_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Long groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.host_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Long hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.is_deleted
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.gmt_create
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.gmt_modified
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.creator
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.modifier
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.id
     *
     * @return the value of group_host.id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_host.id
     *
     * @param id the value for group_host.id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.group_id
     *
     * @return the value of group_host.group_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_host.group_id
     *
     * @param groupId the value for group_host.group_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.host_id
     *
     * @return the value of group_host.host_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Long getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_host.host_id
     *
     * @param hostId the value for group_host.host_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.is_deleted
     *
     * @return the value of group_host.is_deleted
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_host.is_deleted
     *
     * @param isDeleted the value for group_host.is_deleted
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.gmt_create
     *
     * @return the value of group_host.gmt_create
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_host.gmt_create
     *
     * @param gmtCreate the value for group_host.gmt_create
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.gmt_modified
     *
     * @return the value of group_host.gmt_modified
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_host.gmt_modified
     *
     * @param gmtModified the value for group_host.gmt_modified
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.creator
     *
     * @return the value of group_host.creator
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_host.creator
     *
     * @param creator the value for group_host.creator
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.modifier
     *
     * @return the value of group_host.modifier
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_host.modifier
     *
     * @param modifier the value for group_host.modifier
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}