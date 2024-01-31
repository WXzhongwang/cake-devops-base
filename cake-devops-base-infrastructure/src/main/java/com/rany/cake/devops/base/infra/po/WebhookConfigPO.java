package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class WebhookConfigPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook_config.id
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook_config.webhook_name
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    private String webhookName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook_config.webhook_url
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    private String webhookUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook_config.webhook_type
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    private Integer webhookType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook_config.webhook_config
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    private String webhookConfig;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook_config.is_deleted
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook_config.gmt_time
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    private Date gmtTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column webhook_config.gmt_modified
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook_config.id
     *
     * @return the value of webhook_config.id
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook_config.id
     *
     * @param id the value for webhook_config.id
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook_config.webhook_name
     *
     * @return the value of webhook_config.webhook_name
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public String getWebhookName() {
        return webhookName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook_config.webhook_name
     *
     * @param webhookName the value for webhook_config.webhook_name
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public void setWebhookName(String webhookName) {
        this.webhookName = webhookName == null ? null : webhookName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook_config.webhook_url
     *
     * @return the value of webhook_config.webhook_url
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public String getWebhookUrl() {
        return webhookUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook_config.webhook_url
     *
     * @param webhookUrl the value for webhook_config.webhook_url
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl == null ? null : webhookUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook_config.webhook_type
     *
     * @return the value of webhook_config.webhook_type
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public Integer getWebhookType() {
        return webhookType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook_config.webhook_type
     *
     * @param webhookType the value for webhook_config.webhook_type
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public void setWebhookType(Integer webhookType) {
        this.webhookType = webhookType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook_config.webhook_config
     *
     * @return the value of webhook_config.webhook_config
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public String getWebhookConfig() {
        return webhookConfig;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook_config.webhook_config
     *
     * @param webhookConfig the value for webhook_config.webhook_config
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public void setWebhookConfig(String webhookConfig) {
        this.webhookConfig = webhookConfig == null ? null : webhookConfig.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook_config.is_deleted
     *
     * @return the value of webhook_config.is_deleted
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook_config.is_deleted
     *
     * @param isDeleted the value for webhook_config.is_deleted
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook_config.gmt_time
     *
     * @return the value of webhook_config.gmt_time
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public Date getGmtTime() {
        return gmtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook_config.gmt_time
     *
     * @param gmtTime the value for webhook_config.gmt_time
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public void setGmtTime(Date gmtTime) {
        this.gmtTime = gmtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column webhook_config.gmt_modified
     *
     * @return the value of webhook_config.gmt_modified
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column webhook_config.gmt_modified
     *
     * @param gmtModified the value for webhook_config.gmt_modified
     *
     * @mbggenerated Wed Jan 31 21:59:39 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}