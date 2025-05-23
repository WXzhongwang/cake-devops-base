package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class ServerProxyPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.proxy_host
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String proxyHost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.proxy_port
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Integer proxyPort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.proxy_username
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String proxyUsername;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.proxy_password
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String proxyPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.proxy_type
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Integer proxyType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.description
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.is_deleted
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.gmt_create
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.gmt_modified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.creator
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_proxy.modifier
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.id
     *
     * @return the value of server_proxy.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.id
     *
     * @param id the value for server_proxy.id
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.proxy_host
     *
     * @return the value of server_proxy.proxy_host
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.proxy_host
     *
     * @param proxyHost the value for server_proxy.proxy_host
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost == null ? null : proxyHost.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.proxy_port
     *
     * @return the value of server_proxy.proxy_port
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Integer getProxyPort() {
        return proxyPort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.proxy_port
     *
     * @param proxyPort the value for server_proxy.proxy_port
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.proxy_username
     *
     * @return the value of server_proxy.proxy_username
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getProxyUsername() {
        return proxyUsername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.proxy_username
     *
     * @param proxyUsername the value for server_proxy.proxy_username
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername == null ? null : proxyUsername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.proxy_password
     *
     * @return the value of server_proxy.proxy_password
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.proxy_password
     *
     * @param proxyPassword the value for server_proxy.proxy_password
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword == null ? null : proxyPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.proxy_type
     *
     * @return the value of server_proxy.proxy_type
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Integer getProxyType() {
        return proxyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.proxy_type
     *
     * @param proxyType the value for server_proxy.proxy_type
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setProxyType(Integer proxyType) {
        this.proxyType = proxyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.description
     *
     * @return the value of server_proxy.description
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.description
     *
     * @param description the value for server_proxy.description
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.is_deleted
     *
     * @return the value of server_proxy.is_deleted
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.is_deleted
     *
     * @param isDeleted the value for server_proxy.is_deleted
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.gmt_create
     *
     * @return the value of server_proxy.gmt_create
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.gmt_create
     *
     * @param gmtCreate the value for server_proxy.gmt_create
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.gmt_modified
     *
     * @return the value of server_proxy.gmt_modified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.gmt_modified
     *
     * @param gmtModified the value for server_proxy.gmt_modified
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.creator
     *
     * @return the value of server_proxy.creator
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.creator
     *
     * @param creator the value for server_proxy.creator
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_proxy.modifier
     *
     * @return the value of server_proxy.modifier
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_proxy.modifier
     *
     * @param modifier the value for server_proxy.modifier
     *
     * @mbggenerated Sat Apr 26 15:56:18 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}