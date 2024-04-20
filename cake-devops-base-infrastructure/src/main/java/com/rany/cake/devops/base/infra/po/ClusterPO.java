package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class ClusterPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.id
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.cluster_id
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String clusterId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.name
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.tags
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String tags;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.version
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String version;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.cluster_type
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String clusterType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.status
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.is_deleted
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.gmt_create
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.gmt_modified
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.creator
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.modifier
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.connection_string
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String connectionString;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cluster.token
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    private String token;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.id
     *
     * @return the value of cluster.id
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.id
     *
     * @param id the value for cluster.id
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.cluster_id
     *
     * @return the value of cluster.cluster_id
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getClusterId() {
        return clusterId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.cluster_id
     *
     * @param clusterId the value for cluster.cluster_id
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setClusterId(String clusterId) {
        this.clusterId = clusterId == null ? null : clusterId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.name
     *
     * @return the value of cluster.name
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.name
     *
     * @param name the value for cluster.name
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.tags
     *
     * @return the value of cluster.tags
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.tags
     *
     * @param tags the value for cluster.tags
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.version
     *
     * @return the value of cluster.version
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.version
     *
     * @param version the value for cluster.version
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.cluster_type
     *
     * @return the value of cluster.cluster_type
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getClusterType() {
        return clusterType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.cluster_type
     *
     * @param clusterType the value for cluster.cluster_type
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setClusterType(String clusterType) {
        this.clusterType = clusterType == null ? null : clusterType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.status
     *
     * @return the value of cluster.status
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.status
     *
     * @param status the value for cluster.status
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.is_deleted
     *
     * @return the value of cluster.is_deleted
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.is_deleted
     *
     * @param isDeleted the value for cluster.is_deleted
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.gmt_create
     *
     * @return the value of cluster.gmt_create
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.gmt_create
     *
     * @param gmtCreate the value for cluster.gmt_create
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.gmt_modified
     *
     * @return the value of cluster.gmt_modified
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.gmt_modified
     *
     * @param gmtModified the value for cluster.gmt_modified
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.creator
     *
     * @return the value of cluster.creator
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.creator
     *
     * @param creator the value for cluster.creator
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.modifier
     *
     * @return the value of cluster.modifier
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.modifier
     *
     * @param modifier the value for cluster.modifier
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.connection_string
     *
     * @return the value of cluster.connection_string
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.connection_string
     *
     * @param connectionString the value for cluster.connection_string
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString == null ? null : connectionString.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cluster.token
     *
     * @return the value of cluster.token
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cluster.token
     *
     * @param token the value for cluster.token
     *
     * @mbggenerated Sat Apr 20 19:51:56 CST 2024
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
}