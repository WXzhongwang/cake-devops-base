package com.rany.cake.devops.base.domain.repository;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.entity.WebSideMessage;
import com.rany.cake.devops.base.domain.repository.param.WebSideMessagePageQueryParam;

import java.util.List;

public interface WebSideMessageRepository {


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
     * @param idList idList
     * @return effect
     */
    Integer readMessage(List<Long> idList);

    /**
     * 删除全部已读站内信
     *
     * @return effect
     */
    Integer deleteAllRead(Long userId);

    /**
     * 删除站内信
     *
     * @param idList idList
     * @return effect
     */
    Integer deleteMessage(List<Long> idList);

    /**
     * 按条件查询站内信
     *
     * @param webSideMessagePageQueryParam 查询站内信
     * @return 站内信列表
     */
    Page<WebSideMessage> queryWebSideMessage(WebSideMessagePageQueryParam webSideMessagePageQueryParam);

    /**
     * 保存站内信
     *
     * @param webSideMessage
     */
    void save(WebSideMessage webSideMessage);
}
