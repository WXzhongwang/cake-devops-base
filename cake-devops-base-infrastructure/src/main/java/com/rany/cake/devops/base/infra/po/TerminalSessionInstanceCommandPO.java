package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class TerminalSessionInstanceCommandPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.id
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.terminal_session_instance_id
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    private String terminalSessionInstanceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.prompt
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    private String prompt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.is_formatted
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    private String isFormatted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.is_deleted
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.gmt_create
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.gmt_modified
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.creator
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_session_instance_command.modifier
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.id
     *
     * @return the value of terminal_session_instance_command.id
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.id
     *
     * @param id the value for terminal_session_instance_command.id
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.terminal_session_instance_id
     *
     * @return the value of terminal_session_instance_command.terminal_session_instance_id
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public String getTerminalSessionInstanceId() {
        return terminalSessionInstanceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.terminal_session_instance_id
     *
     * @param terminalSessionInstanceId the value for terminal_session_instance_command.terminal_session_instance_id
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public void setTerminalSessionInstanceId(String terminalSessionInstanceId) {
        this.terminalSessionInstanceId = terminalSessionInstanceId == null ? null : terminalSessionInstanceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.prompt
     *
     * @return the value of terminal_session_instance_command.prompt
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.prompt
     *
     * @param prompt the value for terminal_session_instance_command.prompt
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt == null ? null : prompt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.is_formatted
     *
     * @return the value of terminal_session_instance_command.is_formatted
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public String getIsFormatted() {
        return isFormatted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.is_formatted
     *
     * @param isFormatted the value for terminal_session_instance_command.is_formatted
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public void setIsFormatted(String isFormatted) {
        this.isFormatted = isFormatted == null ? null : isFormatted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.is_deleted
     *
     * @return the value of terminal_session_instance_command.is_deleted
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.is_deleted
     *
     * @param isDeleted the value for terminal_session_instance_command.is_deleted
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.gmt_create
     *
     * @return the value of terminal_session_instance_command.gmt_create
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.gmt_create
     *
     * @param gmtCreate the value for terminal_session_instance_command.gmt_create
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.gmt_modified
     *
     * @return the value of terminal_session_instance_command.gmt_modified
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.gmt_modified
     *
     * @param gmtModified the value for terminal_session_instance_command.gmt_modified
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.creator
     *
     * @return the value of terminal_session_instance_command.creator
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.creator
     *
     * @param creator the value for terminal_session_instance_command.creator
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_session_instance_command.modifier
     *
     * @return the value of terminal_session_instance_command.modifier
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_session_instance_command.modifier
     *
     * @param modifier the value for terminal_session_instance_command.modifier
     *
     * @mbggenerated Wed Dec 06 21:49:23 CST 2023
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}