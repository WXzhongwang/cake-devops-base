package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class ScriptTemplatePO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column script_template.id
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column script_template.template_name
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    private String templateName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column script_template.template_value
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    private String templateValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column script_template.description
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column script_template.is_deleted
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column script_template.gmt_create
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column script_template.gmt_modified
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column script_template.creator
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column script_template.modifier
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column script_template.id
     *
     * @return the value of script_template.id
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column script_template.id
     *
     * @param id the value for script_template.id
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column script_template.template_name
     *
     * @return the value of script_template.template_name
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column script_template.template_name
     *
     * @param templateName the value for script_template.template_name
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column script_template.template_value
     *
     * @return the value of script_template.template_value
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public String getTemplateValue() {
        return templateValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column script_template.template_value
     *
     * @param templateValue the value for script_template.template_value
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public void setTemplateValue(String templateValue) {
        this.templateValue = templateValue == null ? null : templateValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column script_template.description
     *
     * @return the value of script_template.description
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column script_template.description
     *
     * @param description the value for script_template.description
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column script_template.is_deleted
     *
     * @return the value of script_template.is_deleted
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column script_template.is_deleted
     *
     * @param isDeleted the value for script_template.is_deleted
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column script_template.gmt_create
     *
     * @return the value of script_template.gmt_create
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column script_template.gmt_create
     *
     * @param gmtCreate the value for script_template.gmt_create
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column script_template.gmt_modified
     *
     * @return the value of script_template.gmt_modified
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column script_template.gmt_modified
     *
     * @param gmtModified the value for script_template.gmt_modified
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column script_template.creator
     *
     * @return the value of script_template.creator
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column script_template.creator
     *
     * @param creator the value for script_template.creator
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column script_template.modifier
     *
     * @return the value of script_template.modifier
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column script_template.modifier
     *
     * @param modifier the value for script_template.modifier
     *
     * @mbggenerated Thu Apr 04 16:56:10 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}