package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.message.CreateWebSideMessageCommand;
import com.rany.cake.devops.base.api.command.message.DeleteReadWebSideMessageCommand;
import com.rany.cake.devops.base.api.command.message.ReadWebSideMessageCommand;
import com.rany.cake.devops.base.api.dto.WebSideMessageDTO;
import com.rany.cake.devops.base.api.query.message.WebSideMessagePageQuery;
import com.rany.cake.devops.base.api.service.WebSideMessageService;
import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.dingtalk.sdk.utils.SsoUtil;
import com.rany.cake.dingtalk.starter.annotation.CurrentUser;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * webhook 配置
 */
@RestController
@RequestMapping("/api/devops/web-side-message")
public class WebSideMessageController {

    @Resource
    private WebSideMessageService webSideMessageService;


    /**
     * 获取未读数量
     *
     * @return 未读数量
     */
    @GetMapping("/get-unread-count")
    public PojoResult<Integer> getUnreadCount(HttpServletRequest request) {
        SsoUser currentUser = SsoUtil.getCurrentUser(request);
        Long userId = Long.valueOf(currentUser.getUserId());
        return PojoResult.succeed(webSideMessageService.getUnreadCount(userId));
    }

    /**
     * 设置全部已读
     *
     * @return effect
     */
    @GetMapping("/set-all-read")
    public PojoResult<Integer> setAllRead(HttpServletRequest request) {
        SsoUser currentUser = SsoUtil.getCurrentUser(request);
        Long userId = Long.valueOf(currentUser.getUserId());
        return PojoResult.succeed(webSideMessageService.setAllRead(userId));
    }

    /**
     * 已读站内信
     *
     * @param command command
     * @return effect
     */
    @PostMapping("/read")
    public PojoResult<Integer> readMessage(@RequestBody ReadWebSideMessageCommand command) {
        return PojoResult.succeed(webSideMessageService.readMessage(command));
    }

    /**
     * 删除全部已读站内信
     *
     * @return effect
     */
    @PostMapping("/delete-all-read")
    public PojoResult<Integer> deleteAllRead(HttpServletRequest request) {
        SsoUser currentUser = SsoUtil.getCurrentUser(request);
        Long userId = Long.valueOf(currentUser.getUserId());
        return PojoResult.succeed(webSideMessageService.deleteAllRead(userId));
    }

    /**
     * 删除站内信
     *
     * @param command idList
     * @return effect
     */
    @PostMapping("/delete")
    public PojoResult<Integer> deleteMessage(@RequestBody DeleteReadWebSideMessageCommand command) {
        return PojoResult.succeed(webSideMessageService.deleteMessage(command));
    }

    /**
     * 按条件查询站内信
     *
     * @param query 查询站内信
     * @return 站内信列表
     */
    @PostMapping("/query")
    public PageResult<WebSideMessageDTO> queryWebSideMessage(@RequestBody WebSideMessagePageQuery query,
                                                             @CurrentUser SsoUser ssoUser) {
        query.setUserId(Long.valueOf(ssoUser.getUserId()));
        return PageResult.succeed(webSideMessageService.queryWebSideMessage(query));
    }

    /**
     * 保存站内信
     *
     * @param command 保存站内信
     * @return success
     */
    @PostMapping("/save")
    public PojoResult<Boolean> saveWebSideMessage(@RequestBody CreateWebSideMessageCommand command) {
        return PojoResult.succeed(webSideMessageService.saveWebSideMessage(command));
    }
}
