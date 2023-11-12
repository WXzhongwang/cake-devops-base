package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.id
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.name
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.host_name
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private String hostName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.port
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private Integer port;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.username
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.status
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.is_deleted
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.gmt_create
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.gmt_modified
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.desc
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private String desc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.verified
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private String verified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.creator
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.modifier
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.pkey
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    private String pkey;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.id
     *
     * @return the value of host.id
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.id
     *
     * @param id the value for host.id
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.name
     *
     * @return the value of host.name
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.name
     *
     * @param name the value for host.name
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.host_name
     *
     * @return the value of host.host_name
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.host_name
     *
     * @param hostName the value for host.host_name
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setHostName(String hostName) {
        this.hostName = hostName == null ? null : hostName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.port
     *
     * @return the value of host.port
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public Integer getPort() {
        return port;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.port
     *
     * @param port the value for host.port
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.username
     *
     * @return the value of host.username
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.username
     *
     * @param username the value for host.username
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.status
     *
     * @return the value of host.status
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.status
     *
     * @param status the value for host.status
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.is_deleted
     *
     * @return the value of host.is_deleted
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.is_deleted
     *
     * @param isDeleted the value for host.is_deleted
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.gmt_create
     *
     * @return the value of host.gmt_create
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.gmt_create
     *
     * @param gmtCreate the value for host.gmt_create
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.gmt_modified
     *
     * @return the value of host.gmt_modified
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.gmt_modified
     *
     * @param gmtModified the value for host.gmt_modified
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.desc
     *
     * @return the value of host.desc
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.desc
     *
     * @param desc the value for host.desc
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.verified
     *
     * @return the value of host.verified
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public String getVerified() {
        return verified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.verified
     *
     * @param verified the value for host.verified
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setVerified(String verified) {
        this.verified = verified == null ? null : verified.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.creator
     *
     * @return the value of host.creator
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.creator
     *
     * @param creator the value for host.creator
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.modifier
     *
     * @return the value of host.modifier
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.modifier
     *
     * @param modifier the value for host.modifier
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.pkey
     *
     * @return the value of host.pkey
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public String getPkey() {
        return pkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.pkey
     *
     * @param pkey the value for host.pkey
     *
     * @mbggenerated Sun Nov 12 15:42:12 CST 2023
     */
    public void setPkey(String pkey) {
        this.pkey = pkey == null ? null : pkey.trim();
    }
}