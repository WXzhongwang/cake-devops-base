package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.host_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.name
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.host_name
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String hostName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.server_addr
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String serverAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.port
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Integer port;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.auth_type
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Integer authType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.username
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.pwd
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String pwd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.proxy_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Long proxyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.key_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Long keyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.status
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.is_deleted
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.gmt_create
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.gmt_modified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.description
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.verified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String verified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.creator
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host.modifier
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.id
     *
     * @return the value of host.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.host_id
     *
     * @return the value of host.host_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.host_id
     *
     * @param hostId the value for host.host_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.name
     *
     * @return the value of host.name
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setHostName(String hostName) {
        this.hostName = hostName == null ? null : hostName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.server_addr
     *
     * @return the value of host.server_addr
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getServerAddr() {
        return serverAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.server_addr
     *
     * @param serverAddr the value for host.server_addr
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr == null ? null : serverAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.port
     *
     * @return the value of host.port
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.auth_type
     *
     * @return the value of host.auth_type
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Integer getAuthType() {
        return authType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.auth_type
     *
     * @param authType the value for host.auth_type
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.username
     *
     * @return the value of host.username
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.pwd
     *
     * @return the value of host.pwd
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.pwd
     *
     * @param pwd the value for host.pwd
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.proxy_id
     *
     * @return the value of host.proxy_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Long getProxyId() {
        return proxyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.proxy_id
     *
     * @param proxyId the value for host.proxy_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setProxyId(Long proxyId) {
        this.proxyId = proxyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.key_id
     *
     * @return the value of host.key_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Long getKeyId() {
        return keyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.key_id
     *
     * @param keyId the value for host.key_id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.status
     *
     * @return the value of host.status
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.description
     *
     * @return the value of host.description
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host.description
     *
     * @param description the value for host.description
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host.verified
     *
     * @return the value of host.verified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
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
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}