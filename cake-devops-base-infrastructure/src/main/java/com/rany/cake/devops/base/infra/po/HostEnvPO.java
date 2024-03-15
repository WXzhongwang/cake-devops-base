package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostEnvPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.id
     *
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.host_id
     *
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.attr_key
     *
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
     */
    private String attrKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.attr_value
     *
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
     */
    private String attrValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.is_deleted
     *
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.gmt_create
     *
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.gmt_modified
     *
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.creator
     *
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_env.modifier
     *
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_env.id
     *
     * @return the value of host_env.id
     *
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
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
     * @mbggenerated Fri Mar 15 20:26:10 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}