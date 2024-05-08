package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class HostTerminalConfigPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.host_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.terminal_type
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String terminalType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.background_color
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String backgroundColor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.font_color
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String fontColor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.font_size
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Integer fontSize;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.font_family
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String fontFamily;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.enable_web_link
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Integer enableWebLink;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.is_deleted
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.gmt_create
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.gmt_modified
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.creator
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column host_terminal_config.modifier
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.id
     *
     * @return the value of host_terminal_config.id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.id
     *
     * @param id the value for host_terminal_config.id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.host_id
     *
     * @return the value of host_terminal_config.host_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.host_id
     *
     * @param hostId the value for host_terminal_config.host_id
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.terminal_type
     *
     * @return the value of host_terminal_config.terminal_type
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getTerminalType() {
        return terminalType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.terminal_type
     *
     * @param terminalType the value for host_terminal_config.terminal_type
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType == null ? null : terminalType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.background_color
     *
     * @return the value of host_terminal_config.background_color
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.background_color
     *
     * @param backgroundColor the value for host_terminal_config.background_color
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor == null ? null : backgroundColor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.font_color
     *
     * @return the value of host_terminal_config.font_color
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getFontColor() {
        return fontColor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.font_color
     *
     * @param fontColor the value for host_terminal_config.font_color
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setFontColor(String fontColor) {
        this.fontColor = fontColor == null ? null : fontColor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.font_size
     *
     * @return the value of host_terminal_config.font_size
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Integer getFontSize() {
        return fontSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.font_size
     *
     * @param fontSize the value for host_terminal_config.font_size
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.font_family
     *
     * @return the value of host_terminal_config.font_family
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.font_family
     *
     * @param fontFamily the value for host_terminal_config.font_family
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily == null ? null : fontFamily.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.enable_web_link
     *
     * @return the value of host_terminal_config.enable_web_link
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Integer getEnableWebLink() {
        return enableWebLink;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.enable_web_link
     *
     * @param enableWebLink the value for host_terminal_config.enable_web_link
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setEnableWebLink(Integer enableWebLink) {
        this.enableWebLink = enableWebLink;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.is_deleted
     *
     * @return the value of host_terminal_config.is_deleted
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.is_deleted
     *
     * @param isDeleted the value for host_terminal_config.is_deleted
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.gmt_create
     *
     * @return the value of host_terminal_config.gmt_create
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.gmt_create
     *
     * @param gmtCreate the value for host_terminal_config.gmt_create
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.gmt_modified
     *
     * @return the value of host_terminal_config.gmt_modified
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.gmt_modified
     *
     * @param gmtModified the value for host_terminal_config.gmt_modified
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.creator
     *
     * @return the value of host_terminal_config.creator
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.creator
     *
     * @param creator the value for host_terminal_config.creator
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column host_terminal_config.modifier
     *
     * @return the value of host_terminal_config.modifier
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column host_terminal_config.modifier
     *
     * @param modifier the value for host_terminal_config.modifier
     *
     * @mbggenerated Mon May 06 22:14:05 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}