package com.rany.cake.devops.base.service.handler.sftp;


import com.rany.cake.devops.base.api.dto.SftpSessionTokenDTO;
import com.rany.cake.devops.base.api.dto.user.UserDTO;
import com.rany.cake.devops.base.service.base.WebSockets;
import com.rany.cake.devops.base.service.ws.PassportService;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.ws.WsCloseCode;
import com.rany.cake.toolkit.lang.utils.Lists;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * sftp 传输通知处理器
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/6/27 11:25
 */
@Component
@Slf4j
public class FileTransferNotifyHandler implements WebSocketHandler {

    @Resource
    private TransferProcessorManager transferProcessorManager;

    @Resource
    private PassportService passportService;

    @Resource
    private SftpInternalService sftpInternalService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String id = session.getId();
        String token = WebSockets.getToken(session);
        try {
            SftpSessionTokenDTO tokenInfo = sftpInternalService.getTokenInfo(token);
            String userId = tokenInfo.getUserId();
            String machineId = tokenInfo.getHostId();
            List<String> machineIdList = tokenInfo.getHostIdList();
            if (machineIdList == null) {
                machineIdList = Lists.newList();
            }
            if (machineId != null) {
                machineIdList.add(machineId);
            }
            session.getAttributes().put(WebSockets.UID, userId);
            session.getAttributes().put(WebSockets.MID, machineIdList);
            log.info("sftp-Notify 建立连接成功 id: {}, token: {}, userId: {}, machineId: {}, machineIdList: {}", id, token, userId, machineId, machineIdList);
        } catch (Exception e) {
            log.error("sftp-Notify 建立连接失败-未查询到token信息 id: {}, token: {}", id, token, e);
            WebSockets.close(session, WsCloseCode.FORGE_TOKEN);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleMessage(WebSocketSession session, @NotNull WebSocketMessage<?> message) {
        String id = session.getId();
        Map<String, Object> attributes = session.getAttributes();
        if (attributes.get(WebSockets.AUTHED) != null) {
            return;
        }
        if (!(message instanceof TextMessage)) {
            return;
        }
        // 获取body
        String authToken = ((TextMessage) message).getPayload();
        if (Strings.isEmpty(authToken)) {
            log.info("sftp-Notify 认证失败-body为空 id: {}", id);
            WebSockets.close(session, WsCloseCode.INCORRECT_TOKEN);
            return;
        }
        // 获取认证用户
        UserDTO user = passportService.getUserByToken(authToken, null);
        if (user == null) {
            log.info("sftp-Notify 认证失败-未查询到用户 id: {}, authToken: {}", id, authToken);
            WebSockets.close(session, WsCloseCode.INCORRECT_TOKEN);
            return;
        }
        // 检查认证用户是否匹配
        String userId = user.getUserId();
        String tokenUserId = (String) attributes.get(WebSockets.UID);
        final boolean valid = userId.equals(tokenUserId);
        if (!valid) {
            log.info("sftp-Notify 认证失败-用户不匹配 id: {}, userId: {}, tokenUserId: {}", id, userId, tokenUserId);
            WebSockets.close(session, WsCloseCode.VALID);
            return;
        }
        attributes.put(WebSockets.AUTHED, Const.ENABLE);
        // 注册会话
        List<String> machineIdList = (List<String>) attributes.get(WebSockets.MID);
        Lists.forEach(machineIdList, i -> {
            log.info("sftp-Notify 认证成功 id: {}, userId: {}, machineId: {}", id, userId, i);
            transferProcessorManager.registerSessionNotify(id, session, userId, i);
        });
    }

    @Override
    public void handleTransportError(WebSocketSession session, @NotNull Throwable exception) {
        log.error("sftp-Notify 操作异常拦截 id: {}", session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String id = session.getId();
        transferProcessorManager.closeSessionNotify(id);
        log.info("sftp-Notify 关闭连接 id: {}, code: {}, reason: {}", id, status.getCode(), status.getReason());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
