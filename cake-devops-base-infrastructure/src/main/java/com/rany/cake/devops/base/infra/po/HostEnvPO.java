package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostEnvPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.host_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.attr_key
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String attrKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.attr_value
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String attrValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.is_deleted
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.gmt_create
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.gmt_modified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.creator
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.modifier
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.description
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.id
     *
     * @return the value of host_env.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_env.id
     *
     * @param id the value for host_env.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.host_id
     *
     * @return the value of host_env.host_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_env.host_id
     *
     * @param hostId the value for host_env.host_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.attr_key
     *
     * @return the value of host_env.attr_key
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getAttrKey() {
        return attrKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_env.attr_key
     *
     * @param attrKey the value for host_env.attr_key
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey == null ? null : attrKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.attr_value
     *
     * @return the value of host_env.attr_value
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_env.attr_value
     *
     * @param attrValue the value for host_env.attr_value
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.is_deleted
     *
     * @return the value of host_env.is_deleted
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_env.is_deleted
     *
     * @param isDeleted the value for host_env.is_deleted
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.gmt_create
     *
     * @return the value of host_env.gmt_create
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_env.gmt_create
     *
     * @param gmtCreate the value for host_env.gmt_create
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.gmt_modified
     *
     * @return the value of host_env.gmt_modified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_env.gmt_modified
     *
     * @param gmtModified the value for host_env.gmt_modified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.creator
     *
     * @return the value of host_env.creator
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_env.creator
     *
     * @param creator the value for host_env.creator
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.modifier
     *
     * @return the value of host_env.modifier
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_env.modifier
     *
     * @param modifier the value for host_env.modifier
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.description
     *
     * @return the value of host_env.description
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_env.description
     *
     * @param description the value for host_env.description
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}