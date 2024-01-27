package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class ApprovalPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.id
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.approval_id
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private String approvalId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.doc_address
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private String docAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.change_date
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private Date changeDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.approval_status
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private String approvalStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.comment
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private String comment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.is_deleted
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.gmt_create
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.gmt_modified
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.creator
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column approval.modifier
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.id
     *
     * @return the value of approval.id
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.id
     *
     * @param id the value for approval.id
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.approval_id
     *
     * @return the value of approval.approval_id
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public String getApprovalId() {
        return approvalId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.approval_id
     *
     * @param approvalId the value for approval.approval_id
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId == null ? null : approvalId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.doc_address
     *
     * @return the value of approval.doc_address
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public String getDocAddress() {
        return docAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.doc_address
     *
     * @param docAddress the value for approval.doc_address
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setDocAddress(String docAddress) {
        this.docAddress = docAddress == null ? null : docAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.change_date
     *
     * @return the value of approval.change_date
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.change_date
     *
     * @param changeDate the value for approval.change_date
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.approval_status
     *
     * @return the value of approval.approval_status
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public String getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.approval_status
     *
     * @param approvalStatus the value for approval.approval_status
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus == null ? null : approvalStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.comment
     *
     * @return the value of approval.comment
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.comment
     *
     * @param comment the value for approval.comment
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.is_deleted
     *
     * @return the value of approval.is_deleted
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.is_deleted
     *
     * @param isDeleted the value for approval.is_deleted
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.gmt_create
     *
     * @return the value of approval.gmt_create
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.gmt_create
     *
     * @param gmtCreate the value for approval.gmt_create
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.gmt_modified
     *
     * @return the value of approval.gmt_modified
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.gmt_modified
     *
     * @param gmtModified the value for approval.gmt_modified
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.creator
     *
     * @return the value of approval.creator
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.creator
     *
     * @param creator the value for approval.creator
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column approval.modifier
     *
     * @return the value of approval.modifier
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column approval.modifier
     *
     * @param modifier the value for approval.modifier
     *
     * @mbggenerated Sat Jan 27 13:31:28 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}