package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class ServerKeyPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.id
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.display_name
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String displayName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.account_type
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String accountType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.protocol
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String protocol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.active
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String active;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.credential
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String credential;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.public_key
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String publicKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.passphrase
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String passphrase;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.is_deleted
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.gmt_create
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.gmt_modified
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.creator
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.modifier
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_key.key_path
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    private String keyPath;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.id
     *
     * @return the value of server_key.id
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.id
     *
     * @param id the value for server_key.id
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.display_name
     *
     * @return the value of server_key.display_name
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.display_name
     *
     * @param displayName the value for server_key.display_name
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.account_type
     *
     * @return the value of server_key.account_type
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.account_type
     *
     * @param accountType the value for server_key.account_type
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.protocol
     *
     * @return the value of server_key.protocol
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.protocol
     *
     * @param protocol the value for server_key.protocol
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol == null ? null : protocol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.active
     *
     * @return the value of server_key.active
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getActive() {
        return active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.active
     *
     * @param active the value for server_key.active
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.credential
     *
     * @return the value of server_key.credential
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getCredential() {
        return credential;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.credential
     *
     * @param credential the value for server_key.credential
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setCredential(String credential) {
        this.credential = credential == null ? null : credential.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.public_key
     *
     * @return the value of server_key.public_key
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.public_key
     *
     * @param publicKey the value for server_key.public_key
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey == null ? null : publicKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.passphrase
     *
     * @return the value of server_key.passphrase
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getPassphrase() {
        return passphrase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.passphrase
     *
     * @param passphrase the value for server_key.passphrase
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase == null ? null : passphrase.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.is_deleted
     *
     * @return the value of server_key.is_deleted
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.is_deleted
     *
     * @param isDeleted the value for server_key.is_deleted
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.gmt_create
     *
     * @return the value of server_key.gmt_create
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.gmt_create
     *
     * @param gmtCreate the value for server_key.gmt_create
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.gmt_modified
     *
     * @return the value of server_key.gmt_modified
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.gmt_modified
     *
     * @param gmtModified the value for server_key.gmt_modified
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.creator
     *
     * @return the value of server_key.creator
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.creator
     *
     * @param creator the value for server_key.creator
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.modifier
     *
     * @return the value of server_key.modifier
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.modifier
     *
     * @param modifier the value for server_key.modifier
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_key.key_path
     *
     * @return the value of server_key.key_path
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public String getKeyPath() {
        return keyPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_key.key_path
     *
     * @param keyPath the value for server_key.key_path
     *
     * @mbggenerated Thu Feb 06 18:04:30 CST 2025
     */
    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath == null ? null : keyPath.trim();
    }
}