package com.rany.cake.devops.base.web.controller;


import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.account.CreateServerKeyCommand;
import com.rany.cake.devops.base.api.command.account.DeleteServerKeyCommand;
import com.rany.cake.devops.base.api.command.account.ModifyServerKeyCommand;
import com.rany.cake.devops.base.api.dto.ServerKeyDTO;
import com.rany.cake.devops.base.api.query.ServerKeyBasicQuery;
import com.rany.cake.devops.base.api.query.ServerKeyPageQuery;
import com.rany.cake.devops.base.api.service.ServerKeyService;
import com.rany.cake.devops.base.service.base.PathBuilders;
import com.rany.cake.devops.base.service.base.ValueMix;
import com.rany.cake.devops.base.service.handler.host.ServerKeyComponent;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.web.request.CreateServerKeyRequest;
import com.rany.cake.devops.base.web.request.UpdateServerKeyRequest;
import com.rany.cake.toolkit.lang.codec.Base64s;
import com.rany.cake.toolkit.lang.io.FileWriters;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.utils.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/devops/server-key")
public class ServerKeyController {

    @Resource
    private ServerKeyService serverKeyService;
    @Resource
    private ServerKeyComponent serverKeyComponent;

    @PostMapping("/create")
    public PojoResult<Long> create(@RequestBody CreateServerKeyRequest request) {
        String file = PathBuilders.getSecretKeyPath();
        CreateServerKeyCommand command = request.getCommand();
        String path = serverKeyComponent.getKeyPath(file);
        String password = command.getPassphrase();
        Files1.touch(path);
        byte[] keyFileData = Base64s.decode(Strings.bytes(request.getFileBase64()));
        FileWriters.writeFast(path, keyFileData);
        command.setKeyPath(file);
        serverKeyComponent.checkLoadKey(path, password);
        return PojoResult.succeed(serverKeyService.createServerKey(request.getCommand()));
    }

    @GetMapping("/get")
    public PojoResult<ServerKeyDTO> get(@RequestParam("id") Long serverAccountId) {
        ServerKeyBasicQuery serverKeyBasicQuery = new ServerKeyBasicQuery();
        serverKeyBasicQuery.setServerKeyId(serverAccountId);
        return PojoResult.succeed(serverKeyService.getServerKey(serverKeyBasicQuery));
    }

    @PostMapping("/update")
    public PojoResult<Boolean> update(@RequestBody UpdateServerKeyRequest request) {
        ModifyServerKeyCommand command = request.getCommand();
        String password = command.getPassphrase();
        String fileBase64 = request.getFileBase64();
        // 修改文件
        final boolean updateFile = !Strings.isBlank(fileBase64);
        if (updateFile) {
            // 修改秘钥文件 将新秘钥保存到本地
            String keyFile = PathBuilders.getSecretKeyPath();
            String keyPath = serverKeyComponent.getKeyPath(keyFile);
            Files1.touch(keyPath);
            byte[] keyFileData = Base64s.decode(Strings.bytes(fileBase64));
            FileWriters.writeFast(keyPath, keyFileData);
            command.setKeyPath(keyFile);
        }
        // 修改密码
        if (Strings.isBlank(password)) {
            command.setPassphrase(Const.EMPTY);
        } else {
            command.setPassphrase(ValueMix.encrypt(password));
        }

        ServerKeyBasicQuery serverKeyBasicQuery = new ServerKeyBasicQuery();
        serverKeyBasicQuery.setServerKeyId(command.getServerKeyId());
        ServerKeyDTO serverKeyDTO = serverKeyService.getServerKey(serverKeyBasicQuery);
        // 检查秘钥
        String checkPath = updateFile ? command.getKeyPath() : serverKeyDTO.getKeyPath();
        serverKeyComponent.checkLoadKey(serverKeyComponent.getKeyPath(checkPath), password);
        return PojoResult.succeed(serverKeyService.modifyServerKey(command));
    }

    @PostMapping("/delete")
    public PojoResult<Boolean> delete(@RequestBody DeleteServerKeyCommand command) {
        return PojoResult.succeed(serverKeyService.deleteServerKey(command));
    }

    @PostMapping("/page")
    public PageResult<ServerKeyDTO> page(@RequestBody ServerKeyPageQuery serverKeyPageQuery) {
        return PageResult.succeed(serverKeyService.pageServerKey(serverKeyPageQuery));
    }
}
