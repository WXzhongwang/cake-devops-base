package com.rany.cake.devops.base.infra.po;

import java.util.Date;

public class ServerAccountPO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.id
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.server_account_id
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String serverAccountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.host_id
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String hostId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.auth_mode
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String authMode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.username
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.display_name
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String displayName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.account_type
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String accountType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.protocol
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String protocol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.active
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private Byte active;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.credential
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String credential;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.public_key
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String publicKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.passphrase
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String passphrase;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.is_deleted
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.gmt_create
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.gmt_modified
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.creator
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column server_account.modifier
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    private String modifier;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.id
     *
     * @return the value of server_account.id
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.id
     *
     * @param id the value for server_account.id
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.server_account_id
     *
     * @return the value of server_account.server_account_id
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getServerAccountId() {
        return serverAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.server_account_id
     *
     * @param serverAccountId the value for server_account.server_account_id
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setServerAccountId(String serverAccountId) {
        this.serverAccountId = serverAccountId == null ? null : serverAccountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.host_id
     *
     * @return the value of server_account.host_id
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getHostId() {
        return hostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.host_id
     *
     * @param hostId the value for server_account.host_id
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setHostId(String hostId) {
        this.hostId = hostId == null ? null : hostId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.auth_mode
     *
     * @return the value of server_account.auth_mode
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getAuthMode() {
        return authMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.auth_mode
     *
     * @param authMode the value for server_account.auth_mode
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setAuthMode(String authMode) {
        this.authMode = authMode == null ? null : authMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.username
     *
     * @return the value of server_account.username
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.username
     *
     * @param username the value for server_account.username
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.display_name
     *
     * @return the value of server_account.display_name
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.display_name
     *
     * @param displayName the value for server_account.display_name
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.account_type
     *
     * @return the value of server_account.account_type
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.account_type
     *
     * @param accountType the value for server_account.account_type
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.protocol
     *
     * @return the value of server_account.protocol
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.protocol
     *
     * @param protocol the value for server_account.protocol
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol == null ? null : protocol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.active
     *
     * @return the value of server_account.active
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public Byte getActive() {
        return active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.active
     *
     * @param active the value for server_account.active
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setActive(Byte active) {
        this.active = active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.credential
     *
     * @return the value of server_account.credential
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getCredential() {
        return credential;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.credential
     *
     * @param credential the value for server_account.credential
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setCredential(String credential) {
        this.credential = credential == null ? null : credential.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.public_key
     *
     * @return the value of server_account.public_key
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.public_key
     *
     * @param publicKey the value for server_account.public_key
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey == null ? null : publicKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.passphrase
     *
     * @return the value of server_account.passphrase
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getPassphrase() {
        return passphrase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.passphrase
     *
     * @param passphrase the value for server_account.passphrase
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase == null ? null : passphrase.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.is_deleted
     *
     * @return the value of server_account.is_deleted
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.is_deleted
     *
     * @param isDeleted the value for server_account.is_deleted
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.gmt_create
     *
     * @return the value of server_account.gmt_create
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.gmt_create
     *
     * @param gmtCreate the value for server_account.gmt_create
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.gmt_modified
     *
     * @return the value of server_account.gmt_modified
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.gmt_modified
     *
     * @param gmtModified the value for server_account.gmt_modified
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.creator
     *
     * @return the value of server_account.creator
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.creator
     *
     * @param creator the value for server_account.creator
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column server_account.modifier
     *
     * @return the value of server_account.modifier
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column server_account.modifier
     *
     * @param modifier the value for server_account.modifier
     *
     * @mbggenerated Fri Jan 26 21:17:24 CST 2024
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}