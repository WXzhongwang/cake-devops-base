package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.message.CreateWebSideMessageCommand;
import com.rany.cake.devops.base.api.command.message.DeleteReadWebSideMessageCommand;
import com.rany.cake.devops.base.api.command.message.ReadWebSideMessageCommand;
import com.rany.cake.devops.base.api.dto.WebSideMessageDTO;
import com.rany.cake.devops.base.api.query.WebSideMessagePageQuery;
import com.rany.cake.devops.base.api.service.WebSideMessageService;
import com.rany.cake.devops.base.domain.entity.WebSideMessage;
import com.rany.cake.devops.base.domain.repository.WebSideMessageRepository;
import com.rany.cake.devops.base.domain.repository.param.WebSideMessagePageQueryParam;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.WebSideMessageDataAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@ShenyuService("/web-side-message/**")
@Slf4j
@AllArgsConstructor
public class WebSideMessageRemoteService implements WebSideMessageService {

    private final WebSideMessageRepository webSideMessageRepository;
    private final WebSideMessageDataAdapter webSideMessageDataAdapter;

    @Override
    public PojoResult<Integer> getUnreadCount(Long userId) {
        Integer unreadCount = webSideMessageRepository.getUnreadCount(userId);
        return PojoResult.succeed(unreadCount);
    }

    @Override
    public PojoResult<Integer> setAllRead(Long userId) {
        return PojoResult.succeed(webSideMessageRepository.setAllRead(userId));
    }

    @Override
    public PojoResult<Integer> readMessage(ReadWebSideMessageCommand command) {
        return PojoResult.succeed(webSideMessageRepository.readMessage(command.getMessageIdList()));
    }

    @Override
    public PojoResult<Integer> deleteAllRead(Long userId) {
        return PojoResult.succeed(webSideMessageRepository.deleteAllRead(userId));
    }

    @Override
    public PojoResult<Integer> deleteMessage(DeleteReadWebSideMessageCommand command) {
        return PojoResult.succeed(webSideMessageRepository.deleteMessage(command.getMessageIdList()));
    }

    @Override
    public PageResult<WebSideMessageDTO> queryWebSideMessage(WebSideMessagePageQuery query) {
        WebSideMessagePageQueryParam webSideMessagePageQueryParam = webSideMessageDataAdapter.convertParam(query);
        Page<WebSideMessage> page = webSideMessageRepository.queryWebSideMessage(webSideMessagePageQueryParam);
        Collection<WebSideMessage> items = page.getItems();
        List<WebSideMessageDTO> webSideMessageDTOList =
                webSideMessageDataAdapter.sourceToTarget(new ArrayList<>(items));
        return PageResult.succeed(PageUtils.build(page, webSideMessageDTOList));
    }

    @Override
    public PojoResult<Boolean> saveWebSideMessage(CreateWebSideMessageCommand command) {
        WebSideMessage webSideMessage = new WebSideMessage();
        webSideMessageRepository.save(webSideMessage);
        return PojoResult.succeed(Boolean.TRUE);
    }
}
