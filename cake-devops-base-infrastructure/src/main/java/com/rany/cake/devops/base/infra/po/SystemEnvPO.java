package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class SystemEnvPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_env.id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_env.attr_key
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String attrKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_env.system_env
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Integer systemEnv;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_env.description
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_env.is_deleted
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_env.gmt_create
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_env.gmt_modified
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_env.creator
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_env.modifier
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_env.attr_value
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    private String attrValue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_env.id
     *
     * @return the value of system_env.id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_env.id
     *
     * @param id the value for system_env.id
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_env.attr_key
     *
     * @return the value of system_env.attr_key
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getAttrKey() {
        return attrKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_env.attr_key
     *
     * @param attrKey the value for system_env.attr_key
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey == null ? null : attrKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_env.system_env
     *
     * @return the value of system_env.system_env
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Integer getSystemEnv() {
        return systemEnv;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_env.system_env
     *
     * @param systemEnv the value for system_env.system_env
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setSystemEnv(Integer systemEnv) {
        this.systemEnv = systemEnv;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_env.description
     *
     * @return the value of system_env.description
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_env.description
     *
     * @param description the value for system_env.description
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_env.is_deleted
     *
     * @return the value of system_env.is_deleted
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_env.is_deleted
     *
     * @param isDeleted the value for system_env.is_deleted
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_env.gmt_create
     *
     * @return the value of system_env.gmt_create
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_env.gmt_create
     *
     * @param gmtCreate the value for system_env.gmt_create
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_env.gmt_modified
     *
     * @return the value of system_env.gmt_modified
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_env.gmt_modified
     *
     * @param gmtModified the value for system_env.gmt_modified
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_env.creator
     *
     * @return the value of system_env.creator
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_env.creator
     *
     * @param creator the value for system_env.creator
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_env.modifier
     *
     * @return the value of system_env.modifier
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_env.modifier
     *
     * @param modifier the value for system_env.modifier
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_env.attr_value
     *
     * @return the value of system_env.attr_value
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public String getAttrValue() {
        return attrValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_env.attr_value
     *
     * @param attrValue the value for system_env.attr_value
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue == null ? null : attrValue.trim();
    }
}