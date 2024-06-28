package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.Page;
import com.cake.framework.common.response.PageResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.terminal.BatchDeleteTerminalLogCommand;
import com.rany.cake.devops.base.api.command.terminal.UpdateTerminalSettingCommand;
import com.rany.cake.devops.base.api.dto.HostTerminalConfigDTO;
import com.rany.cake.devops.base.api.dto.HostTerminalLogDTO;
import com.rany.cake.devops.base.api.query.terminal.TerminalAccessDTO;
import com.rany.cake.devops.base.api.query.terminal.TerminalAccessLogPageQuery;
import com.rany.cake.devops.base.api.service.HostTerminalConfigService;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.MessageConst;
import com.rany.cake.devops.base.util.ResultCode;
import com.rany.cake.devops.base.util.Valid;
import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.dingtalk.starter.annotation.CurrentUser;
import com.rany.cake.toolkit.lang.codec.Base64s;
import com.rany.cake.toolkit.lang.io.FileReaders;
import com.rany.cake.toolkit.lang.utils.Exceptions;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;
import com.rany.cake.toolkit.net.remote.TerminalType;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/devops/host/terminal")
public class HostTerminalController {

    @Resource
    private HostTerminalConfigService hostTerminalConfigService;

    @GetMapping("/access")
    @ApiOperation(value = "获取终端accessToken")
    public PojoResult<TerminalAccessDTO> getTerminalAccessToken(
            @CurrentUser SsoUser ssoUser,
            @RequestParam("hostId") String hostId) {
        Valid.notNull(hostId);
        TerminalAccessDTO accessConfig = hostTerminalConfigService.getAccessConfig(hostId, ssoUser.getUserId());
        return PojoResult.succeed(accessConfig);
    }

    @GetMapping("/support/pty")
    @ApiOperation(value = "获取支持的终端类型")
    public PojoResult<String[]> getSupportedPty() {
        return PojoResult.succeed(Arrays.stream(TerminalType.values())
                .map(TerminalType::getType)
                .toArray(String[]::new));
    }


    @GetMapping("/get-config")
    @ApiOperation(value = "获取终端配置")
    public PojoResult<HostTerminalConfigDTO> getSetting(@RequestParam("hostId") String hostId) {
        HostTerminalConfigDTO hostTerminalConfig = hostTerminalConfigService.getHostTerminalConfig(hostId);
        return PojoResult.succeed(hostTerminalConfig);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改终端配置")
    public PojoResult<Void> updateSetting(@RequestBody UpdateTerminalSettingCommand request) {
        Valid.notNull(request.getHostId());
        String terminalType = request.getTerminalType();
        if (!Strings.isBlank(terminalType)) {
            Valid.notNull(TerminalType.of(terminalType), MessageConst.INVALID_PTY);
        }
        if (request.getEnableWebLink() != null) {
            Valid.in(request.getEnableWebLink(), Const.ENABLE, Const.DISABLE);
        }
        hostTerminalConfigService.updateSetting(request);
        return PojoResult.succeed();
    }

    @PostMapping("/log/list")
    public PageResult<HostTerminalLogDTO> accessLogList(@RequestBody TerminalAccessLogPageQuery query) {
        Page<HostTerminalLogDTO> hostTerminalLogDTOPage = hostTerminalConfigService.listAccessLog(query);
        return PageResult.succeed(hostTerminalLogDTOPage);
    }


    @PostMapping("/log/delete")
    @ApiOperation(value = "删除终端日志")
    public PojoResult<Integer> deleteLog(@RequestBody BatchDeleteTerminalLogCommand request) {
        List<Long> idList = Valid.notEmpty(request.getIds());
        return PojoResult.succeed(hostTerminalConfigService.deleteTerminalLog(idList));
    }

    @GetMapping("/log/screen")
    public String getLogScreen(@RequestParam("id") Long id) {
        String path = hostTerminalConfigService.getTerminalScreenFilePath(id);
        Path file = Paths.get(path);
        if (!Files.exists(file)) {
            throw Exceptions.httpWrapper(HttpWrapper.of(ResultCode.FILE_MISSING));
        }
        return Base64s.encodeToString(FileReaders.readAllBytesFast(path));
    }
}
