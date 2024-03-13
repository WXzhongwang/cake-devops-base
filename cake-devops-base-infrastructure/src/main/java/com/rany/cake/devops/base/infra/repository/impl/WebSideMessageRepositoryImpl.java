package com.rany.cake.devops.base.infra.repository.impl;

import com.cake.framework.common.response.Page;
import com.github.pagehelper.PageInfo;
import com.rany.cake.devops.base.domain.entity.WebSideMessage;
import com.rany.cake.devops.base.domain.repository.WebSideMessageRepository;
import com.rany.cake.devops.base.domain.repository.param.WebSideMessagePageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.infra.aop.PagingQuery;
import com.rany.cake.devops.base.infra.convertor.WebSideMessageDataConvertor;
import com.rany.cake.devops.base.infra.dao.WebSideMessageDao;
import com.rany.cake.devops.base.infra.mapper.WebSideMessagePOMapper;
import com.rany.cake.devops.base.infra.po.WebSideMessagePO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class WebSideMessageRepositoryImpl implements WebSideMessageRepository {
    private final WebSideMessagePOMapper webSideMessagePOMapper;
    private final WebSideMessageDao webSideMessageDao;
    private final WebSideMessageDataConvertor webSideMessageDataConvertor;

    @Override
    public Integer getUnreadCount(Long userId) {
        return webSideMessageDao.getUnreadCount(userId);
    }

    @Override
    public Integer setAllRead(Long userId) {
        return webSideMessageDao.setAllRead(userId);
    }

    @Override
    public Integer readMessage(List<Long> idList) {
        return webSideMessageDao.readMessage(idList);
    }

    @Override
    public Integer deleteAllRead(Long userId) {
        return webSideMessageDao.deleteAllRead(userId);
    }

    @Override
    public Integer deleteMessage(List<Long> idList) {
        return webSideMessageDao.deleteMessage(idList);
    }

    @Override
    @PagingQuery
    public Page<WebSideMessage> queryWebSideMessage(WebSideMessagePageQueryParam webSideMessagePageQueryParam) {
        List<WebSideMessagePO> webSideMessageList = webSideMessageDao.queryWebSideMessage(webSideMessagePageQueryParam);
        PageInfo<WebSideMessagePO> pageInfo = new PageInfo<>(webSideMessageList);
        List<WebSideMessage> webSideMessages = webSideMessageDataConvertor.targetToSource(webSideMessageList);
        return PageUtils.build(pageInfo, webSideMessages);
    }

    @Override
    public void save(WebSideMessage webSideMessage) {
        webSideMessageDao.save(webSideMessage);
    }
}
