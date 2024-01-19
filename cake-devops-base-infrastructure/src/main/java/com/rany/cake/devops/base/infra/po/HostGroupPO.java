package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostGroupPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_group.id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_group.host_group_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String hostGroupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_group.name
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_group.parent_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_group.sort
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_group.is_deleted
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_group.gmt_create
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_group.gmt_modified
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_group.creator
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_group.modifier
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_group.id
     *
     * @return the value of host_group.id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_group.id
     *
     * @param id the value for host_group.id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_group.host_group_id
     *
     * @return the value of host_group.host_group_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getHostGroupId() {
        return hostGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_group.host_group_id
     *
     * @param hostGroupId the value for host_group.host_group_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setHostGroupId(String hostGroupId) {
        this.hostGroupId = hostGroupId == null ? null : hostGroupId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_group.name
     *
     * @return the value of host_group.name
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_group.name
     *
     * @param name the value for host_group.name
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_group.parent_id
     *
     * @return the value of host_group.parent_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_group.parent_id
     *
     * @param parentId the value for host_group.parent_id
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_group.sort
     *
     * @return the value of host_group.sort
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_group.sort
     *
     * @param sort the value for host_group.sort
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_group.is_deleted
     *
     * @return the value of host_group.is_deleted
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_group.is_deleted
     *
     * @param isDeleted the value for host_group.is_deleted
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_group.gmt_create
     *
     * @return the value of host_group.gmt_create
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_group.gmt_create
     *
     * @param gmtCreate the value for host_group.gmt_create
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_group.gmt_modified
     *
     * @return the value of host_group.gmt_modified
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_group.gmt_modified
     *
     * @param gmtModified the value for host_group.gmt_modified
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_group.creator
     *
     * @return the value of host_group.creator
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_group.creator
     *
     * @param creator the value for host_group.creator
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_group.modifier
     *
     * @return the value of host_group.modifier
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_group.modifier
     *
     * @param modifier the value for host_group.modifier
     *
     * @mbggenerated Fri Jan 19 08:27:35 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}