package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class ReleasePO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.release_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String releaseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.app_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.release_no
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String releaseNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.approval_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String approvalId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.release_date
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private Date releaseDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.release_branch
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String releaseBranch;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.release_commit_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String releaseCommitId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.release_version
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String releaseVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.env_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String envId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.release_status
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String releaseStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.rollback
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String rollback;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.rollback_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String rollbackId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.is_deleted
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.gmt_create
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.gmt_modified
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.creator
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release_no.modifier
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.id
     *
     * @return the value of release_no.id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.id
     *
     * @param id the value for release_no.id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.release_id
     *
     * @return the value of release_no.release_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getReleaseId() {
        return releaseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.release_id
     *
     * @param releaseId the value for release_no.release_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setReleaseId(String releaseId) {
        this.releaseId = releaseId == null ? null : releaseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.app_id
     *
     * @return the value of release_no.app_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.app_id
     *
     * @param appId the value for release_no.app_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.release_no
     *
     * @return the value of release_no.release_no
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getReleaseNo() {
        return releaseNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.release_no
     *
     * @param releaseNo the value for release_no.release_no
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setReleaseNo(String releaseNo) {
        this.releaseNo = releaseNo == null ? null : releaseNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.approval_id
     *
     * @return the value of release_no.approval_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getApprovalId() {
        return approvalId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.approval_id
     *
     * @param approvalId the value for release_no.approval_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId == null ? null : approvalId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.release_date
     *
     * @return the value of release_no.release_date
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.release_date
     *
     * @param releaseDate the value for release_no.release_date
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.release_branch
     *
     * @return the value of release_no.release_branch
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getReleaseBranch() {
        return releaseBranch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.release_branch
     *
     * @param releaseBranch the value for release_no.release_branch
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setReleaseBranch(String releaseBranch) {
        this.releaseBranch = releaseBranch == null ? null : releaseBranch.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.release_commit_id
     *
     * @return the value of release_no.release_commit_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getReleaseCommitId() {
        return releaseCommitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.release_commit_id
     *
     * @param releaseCommitId the value for release_no.release_commit_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setReleaseCommitId(String releaseCommitId) {
        this.releaseCommitId = releaseCommitId == null ? null : releaseCommitId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.release_version
     *
     * @return the value of release_no.release_version
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getReleaseVersion() {
        return releaseVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.release_version
     *
     * @param releaseVersion the value for release_no.release_version
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion == null ? null : releaseVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.env_id
     *
     * @return the value of release_no.env_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getEnvId() {
        return envId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.env_id
     *
     * @param envId the value for release_no.env_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setEnvId(String envId) {
        this.envId = envId == null ? null : envId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.release_status
     *
     * @return the value of release_no.release_status
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getReleaseStatus() {
        return releaseStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.release_status
     *
     * @param releaseStatus the value for release_no.release_status
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setReleaseStatus(String releaseStatus) {
        this.releaseStatus = releaseStatus == null ? null : releaseStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.rollback
     *
     * @return the value of release_no.rollback
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getRollback() {
        return rollback;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.rollback
     *
     * @param rollback the value for release_no.rollback
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setRollback(String rollback) {
        this.rollback = rollback == null ? null : rollback.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.rollback_id
     *
     * @return the value of release_no.rollback_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getRollbackId() {
        return rollbackId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.rollback_id
     *
     * @param rollbackId the value for release_no.rollback_id
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setRollbackId(String rollbackId) {
        this.rollbackId = rollbackId == null ? null : rollbackId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.is_deleted
     *
     * @return the value of release_no.is_deleted
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.is_deleted
     *
     * @param isDeleted the value for release_no.is_deleted
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.gmt_create
     *
     * @return the value of release_no.gmt_create
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.gmt_create
     *
     * @param gmtCreate the value for release_no.gmt_create
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.gmt_modified
     *
     * @return the value of release_no.gmt_modified
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.gmt_modified
     *
     * @param gmtModified the value for release_no.gmt_modified
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.creator
     *
     * @return the value of release_no.creator
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.creator
     *
     * @param creator the value for release_no.creator
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release_no.modifier
     *
     * @return the value of release_no.modifier
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release_no.modifier
     *
     * @param modifier the value for release_no.modifier
     *
     * @mbggenerated Tue Aug 13 20:20:54 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}