package com.rany.cake.devops.base.api.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.message.CreateWebSideMessageCommand;
import com.rany.cake.devops.base.api.command.message.DeleteReadWebSideMessageCommand;
import com.rany.cake.devops.base.api.command.message.ReadWebSideMessageCommand;
import com.rany.cake.devops.base.api.dto.WebSideMessageDTO;
import com.rany.cake.devops.base.api.query.message.WebSideMessagePageQuery;

/**
 * 站内信
 *
 * @author zhongshengwang
 * @description 站内信
 * @date 2023/1/15 17:33
 * @email 18668485565163.com
 */
public interface WebSideMessageService {

    /**
     * 获取未读数量
     *
     * @return 未读数量
     */
    Integer getUnreadCount(Long userId);

    /**
     * 设置全部已读
     *
     * @return effect
     */
    Integer setAllRead(Long userId);

    /**
     * 已读站内信
     *
     * @param command command
     * @return effect
     */
    Integer readMessage(ReadWebSideMessageCommand command);

    /**
     * 删除全部已读站内信
     *
     * @return effect
     */
    Integer deleteAllRead(Long userId);

    /**
     * 删除站内信
     *
     * @param command idList
     * @return effect
     */
    Integer deleteMessage(DeleteReadWebSideMessageCommand command);

    /**
     * 按条件查询站内信
     *
     * @param query 查询站内信
     * @return 站内信列表
     */
    Page<WebSideMessageDTO> queryWebSideMessage(WebSideMessagePageQuery query);

    /**
     * 保存站内信
     *
     * @param command 保存站内信
     * @return success
     */
    Boolean saveWebSideMessage(CreateWebSideMessageCommand command);

}
