package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class ReleasePO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.app_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Long appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.release_no
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String releaseNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.approval_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Long approvalId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.release_date
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Date releaseDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.release_branch
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String releaseBranch;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.release_commit_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String releaseCommitId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.release_version
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String releaseVersion;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.env_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Long envId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.release_status
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String releaseStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.rollback
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String rollback;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.rollback_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Long rollbackId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.is_deleted
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.gmt_create
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.gmt_modified
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.creator
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column release.modifier
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.id
     *
     * @return the value of release.id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.id
     *
     * @param id the value for release.id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.app_id
     *
     * @return the value of release.app_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.app_id
     *
     * @param appId the value for release.app_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.release_no
     *
     * @return the value of release.release_no
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getReleaseNo() {
        return releaseNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.release_no
     *
     * @param releaseNo the value for release.release_no
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setReleaseNo(String releaseNo) {
        this.releaseNo = releaseNo == null ? null : releaseNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.approval_id
     *
     * @return the value of release.approval_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Long getApprovalId() {
        return approvalId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.approval_id
     *
     * @param approvalId the value for release.approval_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setApprovalId(Long approvalId) {
        this.approvalId = approvalId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.release_date
     *
     * @return the value of release.release_date
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.release_date
     *
     * @param releaseDate the value for release.release_date
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.release_branch
     *
     * @return the value of release.release_branch
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getReleaseBranch() {
        return releaseBranch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.release_branch
     *
     * @param releaseBranch the value for release.release_branch
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setReleaseBranch(String releaseBranch) {
        this.releaseBranch = releaseBranch == null ? null : releaseBranch.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.release_commit_id
     *
     * @return the value of release.release_commit_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getReleaseCommitId() {
        return releaseCommitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.release_commit_id
     *
     * @param releaseCommitId the value for release.release_commit_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setReleaseCommitId(String releaseCommitId) {
        this.releaseCommitId = releaseCommitId == null ? null : releaseCommitId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.release_version
     *
     * @return the value of release.release_version
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getReleaseVersion() {
        return releaseVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.release_version
     *
     * @param releaseVersion the value for release.release_version
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion == null ? null : releaseVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.env_id
     *
     * @return the value of release.env_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Long getEnvId() {
        return envId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.env_id
     *
     * @param envId the value for release.env_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setEnvId(Long envId) {
        this.envId = envId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.release_status
     *
     * @return the value of release.release_status
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getReleaseStatus() {
        return releaseStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.release_status
     *
     * @param releaseStatus the value for release.release_status
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setReleaseStatus(String releaseStatus) {
        this.releaseStatus = releaseStatus == null ? null : releaseStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.rollback
     *
     * @return the value of release.rollback
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getRollback() {
        return rollback;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.rollback
     *
     * @param rollback the value for release.rollback
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setRollback(String rollback) {
        this.rollback = rollback == null ? null : rollback.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.rollback_id
     *
     * @return the value of release.rollback_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Long getRollbackId() {
        return rollbackId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.rollback_id
     *
     * @param rollbackId the value for release.rollback_id
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setRollbackId(Long rollbackId) {
        this.rollbackId = rollbackId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.is_deleted
     *
     * @return the value of release.is_deleted
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.is_deleted
     *
     * @param isDeleted the value for release.is_deleted
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.gmt_create
     *
     * @return the value of release.gmt_create
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.gmt_create
     *
     * @param gmtCreate the value for release.gmt_create
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.gmt_modified
     *
     * @return the value of release.gmt_modified
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.gmt_modified
     *
     * @param gmtModified the value for release.gmt_modified
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.creator
     *
     * @return the value of release.creator
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.creator
     *
     * @param creator the value for release.creator
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column release.modifier
     *
     * @return the value of release.modifier
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column release.modifier
     *
     * @param modifier the value for release.modifier
     *
     * @mbggenerated Sun Nov 19 19:00:37 CST 2023
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}