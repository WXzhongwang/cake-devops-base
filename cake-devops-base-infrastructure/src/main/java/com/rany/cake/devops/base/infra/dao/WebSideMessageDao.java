package com.rany.cake.devops.base.infra.dao;

import com.rany.cake.devops.base.domain.entity.WebSideMessage;
import com.rany.cake.devops.base.domain.repository.param.WebSideMessagePageQueryParam;
import com.rany.cake.devops.base.infra.po.WebSideMessagePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebSideMessageDao {

    /**
     * 获取未读数量
     *
     * @return 未读数量
     */
    Integer getUnreadCount(@Param("userId") Long userId);

    /**
     * 设置全部已读
     *
     * @return effect
     */
    Integer setAllRead(@Param("userId") Long userId);

    /**
     * 已读站内信
     *
     * @param idList idList
     * @return effect
     */
    Integer readMessage(@Param("idList") List<Long> idList);

    /**
     * 删除全部已读站内信
     *
     * @return effect
     */
    Integer deleteAllRead(@Param("userId") Long userId);

    /**
     * 删除站内信
     *
     * @param idList idList
     * @return effect
     */
    Integer deleteMessage(@Param("idList") List<Long> idList);

    List<WebSideMessagePO> queryWebSideMessage(WebSideMessagePageQueryParam webSideMessagePageQueryParam);

    int save(WebSideMessage webSideMessage);

}
