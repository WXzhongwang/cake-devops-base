package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.message.CreateWebSideMessageCommand;
import com.rany.cake.devops.base.api.command.message.DeleteReadWebSideMessageCommand;
import com.rany.cake.devops.base.api.command.message.ReadWebSideMessageCommand;
import com.rany.cake.devops.base.api.dto.WebSideMessageDTO;
import com.rany.cake.devops.base.api.query.message.WebSideMessagePageQuery;
import com.rany.cake.devops.base.api.service.WebSideMessageService;
import com.rany.cake.devops.base.domain.entity.WebSideMessage;
import com.rany.cake.devops.base.domain.repository.WebSideMessageRepository;
import com.rany.cake.devops.base.domain.repository.param.WebSideMessagePageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.WebSideMessageDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhongshengwang
 */
@Service
@Slf4j
@AllArgsConstructor
public class WebSideMessageServiceImpl implements WebSideMessageService {

    private final WebSideMessageRepository webSideMessageRepository;
    private final WebSideMessageDataAdapter webSideMessageDataAdapter;

    @Override
    public Integer getUnreadCount(Long userId) {
        return webSideMessageRepository.getUnreadCount(userId);
    }

    @Override
    public Integer setAllRead(Long userId) {
        return webSideMessageRepository.setAllRead(userId);
    }

    @Override
    public Integer readMessage(ReadWebSideMessageCommand command) {
        return webSideMessageRepository.readMessage(command.getMessageIdList());
    }

    @Override
    public Integer deleteAllRead(Long userId) {
        return webSideMessageRepository.deleteAllRead(userId);
    }

    @Override
    public Integer deleteMessage(DeleteReadWebSideMessageCommand command) {
        return webSideMessageRepository.deleteMessage(command.getMessageIdList());
    }

    @Override
    public Page<WebSideMessageDTO> queryWebSideMessage(WebSideMessagePageQuery query) {
        WebSideMessagePageQueryParam webSideMessagePageQueryParam = webSideMessageDataAdapter.convertParam(query);
        Page<WebSideMessage> page = webSideMessageRepository.queryWebSideMessage(webSideMessagePageQueryParam);
        Collection<WebSideMessage> items = page.getItems();
        List<WebSideMessageDTO> webSideMessageDTOList =
                webSideMessageDataAdapter.sourceToTarget(new ArrayList<>(items));
        return PageUtils.build(page, webSideMessageDTOList);
    }

    @Override
    public Boolean saveWebSideMessage(CreateWebSideMessageCommand command) {
        WebSideMessage webSideMessage = new WebSideMessage();
        webSideMessageRepository.save(webSideMessage);
        return Boolean.TRUE;
    }
}
