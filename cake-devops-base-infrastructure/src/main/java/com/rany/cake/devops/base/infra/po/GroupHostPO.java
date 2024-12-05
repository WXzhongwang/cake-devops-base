package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class GroupHostPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.id
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.group_id
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    private String groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.host_id
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.is_deleted
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.gmt_create
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.gmt_modified
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.creator
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_host.modifier
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.id
     *
     * @return the value of group_host.id
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_host.group_id
     *
     * @param groupId the value for group_host.group_id
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.host_id
     *
     * @return the value of group_host.host_id
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_host.host_id
     *
     * @param hostId the value for group_host.host_id
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_host.is_deleted
     *
     * @return the value of group_host.is_deleted
     *
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
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
     * @mbggenerated Thu Dec 05 20:17:53 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}