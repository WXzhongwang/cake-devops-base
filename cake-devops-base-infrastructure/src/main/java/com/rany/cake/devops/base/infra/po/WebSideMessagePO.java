package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class WebSideMessagePO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.message_classify
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Byte messageClassify;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.message_type
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Integer messageType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.read_status
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Byte readStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.to_user_id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Long toUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.to_user_name
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private String toUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.rel_id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Long relId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.is_deleted
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.gmt_create
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.gmt_modified
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column web_side_message.send_message
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    private String sendMessage;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.id
     *
     * @return the value of web_side_message.id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.id
     *
     * @param id the value for web_side_message.id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.message_classify
     *
     * @return the value of web_side_message.message_classify
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Byte getMessageClassify() {
        return messageClassify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.message_classify
     *
     * @param messageClassify the value for web_side_message.message_classify
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setMessageClassify(Byte messageClassify) {
        this.messageClassify = messageClassify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.message_type
     *
     * @return the value of web_side_message.message_type
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Integer getMessageType() {
        return messageType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.message_type
     *
     * @param messageType the value for web_side_message.message_type
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.read_status
     *
     * @return the value of web_side_message.read_status
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Byte getReadStatus() {
        return readStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.read_status
     *
     * @param readStatus the value for web_side_message.read_status
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setReadStatus(Byte readStatus) {
        this.readStatus = readStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.to_user_id
     *
     * @return the value of web_side_message.to_user_id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Long getToUserId() {
        return toUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.to_user_id
     *
     * @param toUserId the value for web_side_message.to_user_id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.to_user_name
     *
     * @return the value of web_side_message.to_user_name
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public String getToUserName() {
        return toUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.to_user_name
     *
     * @param toUserName the value for web_side_message.to_user_name
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName == null ? null : toUserName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.rel_id
     *
     * @return the value of web_side_message.rel_id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Long getRelId() {
        return relId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.rel_id
     *
     * @param relId the value for web_side_message.rel_id
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setRelId(Long relId) {
        this.relId = relId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.is_deleted
     *
     * @return the value of web_side_message.is_deleted
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.is_deleted
     *
     * @param isDeleted the value for web_side_message.is_deleted
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.gmt_create
     *
     * @return the value of web_side_message.gmt_create
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.gmt_create
     *
     * @param gmtCreate the value for web_side_message.gmt_create
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.gmt_modified
     *
     * @return the value of web_side_message.gmt_modified
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.gmt_modified
     *
     * @param gmtModified the value for web_side_message.gmt_modified
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column web_side_message.send_message
     *
     * @return the value of web_side_message.send_message
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public String getSendMessage() {
        return sendMessage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column web_side_message.send_message
     *
     * @param sendMessage the value for web_side_message.send_message
     *
     * @mbggenerated Thu Apr 11 22:57:42 CST 2024
     */
    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage == null ? null : sendMessage.trim();
    }
}