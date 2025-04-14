package com.rany.cake.devops.base.infra.mapper;

import com.rany.cake.devops.base.infra.po.WebhookConfigPO;

public interface WebhookConfigPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webhook_config
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webhook_config
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insert(WebhookConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webhook_config
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int insertSelective(WebhookConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webhook_config
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    WebhookConfigPO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webhook_config
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKeySelective(WebhookConfigPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table webhook_config
     *
     * @mbggenerated Mon Apr 14 17:59:37 CST 2025
     */
    int updateByPrimaryKey(WebhookConfigPO record);
}