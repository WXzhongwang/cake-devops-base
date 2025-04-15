package com.rany.cake.devops.base.web.controller;

import com.cake.framework.common.response.ListResult;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.command.system.ConfigIpListCommand;
import com.rany.cake.devops.base.api.command.system.SystemFileCleanCommand;
import com.rany.cake.devops.base.api.command.system.SystemOptionCommand;
import com.rany.cake.devops.base.api.common.enums.EventType;
import com.rany.cake.devops.base.api.dto.system.*;
import com.rany.cake.devops.base.api.service.SystemService;
import com.rany.cake.devops.base.domain.base.PropertiesConst;
import com.rany.cake.devops.base.service.aspect.annotation.EventLog;
import com.rany.cake.devops.base.service.utils.Servlets;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.Valid;
import com.rany.cake.devops.base.util.system.SystemCleanType;
import com.rany.cake.devops.base.util.system.SystemConfigKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统设置 api
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/2/15 22:07
 */
@Api(tags = "系统设置")
@RestController
@RequestMapping("/api/devops/system")
public class SystemController {

    @Resource
    private SystemService systemService;

    @GetMapping("/ip-info")
    @ApiOperation(value = "获取ip信息")
    public PojoResult<IpListConfigDTO> getIpInfo(HttpServletRequest request) {
        return PojoResult.succeed(systemService.getIpInfo(Servlets.getRemoteAddr(request)));
    }

    @PostMapping("/config-ip")
    @ApiOperation(value = "配置ip过滤器列表")
    @EventLog(EventType.CONFIG_IP_LIST)
    public PojoResult<Boolean> configIpList(@RequestBody ConfigIpListCommand request) {
        systemService.configIpFilterList(request);
        return PojoResult.succeed(true);
    }

    @PostMapping("/clean-system-file")
    @ApiOperation(value = "清理系统文件")
    @EventLog(EventType.CLEAN_SYSTEM_FILE)
    public PojoResult<Boolean> cleanSystemFile(@RequestBody SystemFileCleanCommand request) {
        SystemCleanType cleanType = Valid.notNull(SystemCleanType.of(request.getCleanType()));
        systemService.cleanSystemFile(cleanType);
        return PojoResult.succeed(true);
    }

    @GetMapping("/get-system-analysis")
    @ApiOperation(value = "获取系统分析信息")
    public PojoResult<SystemAnalysisDTO> getSystemAnalysis() {
        return PojoResult.succeed(systemService.getSystemAnalysis());
    }

    @GetMapping("/re-analysis")
    @ApiOperation(value = "重新进行系统统计分析")
    @EventLog(EventType.RE_ANALYSIS_SYSTEM)
    public PojoResult<SystemAnalysisDTO> reAnalysisSystem() {
        systemService.analysisSystemSpace();
        return PojoResult.succeed(systemService.getSystemAnalysis());
    }

    @PostMapping("/update-system-option")
    @ApiOperation(value = "修改系统配置项")
    @EventLog(EventType.UPDATE_SYSTEM_OPTION)
    public PojoResult<Boolean> updateSystemOption(@RequestBody SystemOptionCommand request) {
        SystemConfigKey key = Valid.notNull(SystemConfigKey.of(request.getOption()));
        String value = key.getValue(Valid.notBlank(request.getValue()));
        systemService.updateSystemOption(key.getEnv(), value);
        return PojoResult.succeed(true);
    }

    @GetMapping("/get-system-options")
    @ApiOperation(value = "获取系统配置项")
    public PojoResult<SystemOptionDTO> getSystemOptions() {
        return PojoResult.succeed(systemService.getSystemOptions());
    }

    @GetMapping("/get-thread-metrics")
    @ApiOperation(value = "获取线程池指标")
    public ListResult<ThreadPoolMetricsDTO> getThreadMetrics() {
        return ListResult.succeed(systemService.getThreadPoolMetrics());
    }

    @GetMapping("/about")
    @ApiOperation(value = "获取应用信息")
    public SystemAboutDTO getSystemAbout() {
        return SystemAboutDTO.builder()
                .kitVersion(Const.VERSION)
                .version(PropertiesConst.CAKE_OPS_VERSION)
                .author(Const.AUTHOR)
                .authorCn(Const.AUTHOR_CN)
                .build();
    }
}
