package com.rany.cake.devops.base.service.app;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.api.command.terminal.SaveTerminalLogCommand;
import com.rany.cake.devops.base.api.command.terminal.UpdateTerminalLogCommand;
import com.rany.cake.devops.base.api.command.terminal.UpdateTerminalSettingCommand;
import com.rany.cake.devops.base.api.dto.HostTerminalConfigDTO;
import com.rany.cake.devops.base.api.dto.HostTerminalLogDTO;
import com.rany.cake.devops.base.api.query.terminal.TerminalAccessDTO;
import com.rany.cake.devops.base.api.query.terminal.TerminalAccessLogPageQuery;
import com.rany.cake.devops.base.api.service.HostTerminalConfigService;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.entity.HostTerminalConfig;
import com.rany.cake.devops.base.domain.entity.HostTerminalLog;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostTerminalConfigRepository;
import com.rany.cake.devops.base.domain.repository.HostTerminalLogRepository;
import com.rany.cake.devops.base.domain.repository.param.TerminalLogPageQueryParam;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.infra.aop.PageUtils;
import com.rany.cake.devops.base.service.adapter.HostTerminalConfigAdapter;
import com.rany.cake.devops.base.service.adapter.HostTerminalLogAdapter;
import com.rany.cake.devops.base.service.base.Constants;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.KeyConst;
import com.rany.cake.devops.base.util.terminal.TerminalConst;
import com.rany.cake.toolkit.lang.id.UUIds;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.net.remote.TerminalType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.shenyu.client.apache.dubbo.annotation.ShenyuService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@ShenyuService("/host/terminal/**")
@Slf4j
@AllArgsConstructor
public class HostTerminalConfigRemoteService implements HostTerminalConfigService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private HostTerminalLogRepository hostTerminalLogRepository;
    @Resource
    private HostTerminalConfigAdapter hostTerminalConfigAdapter;
    @Resource
    private HostTerminalLogAdapter hostTerminalLogAdapter;
    @Resource
    private HostTerminalConfigRepository hostTerminalConfigRepository;

    @Resource
    private HostDomainService hostDomainService;

    @Override
    public TerminalAccessDTO getAccessConfig(String hostId, Long userId) {
        // 获取机器信息
        Host machine = hostDomainService.getHost(new HostId(hostId));
        String token = UUIds.random32();
        // 获取终端配置
        HostTerminalConfigDTO config = this.getHostTerminalConfig(hostId);
        // 设置数据
        TerminalAccessDTO access = new TerminalAccessDTO();
        access.setId(config.getId());
        access.setAccessToken(token);
        access.setHost(machine.getServerAddr());
        access.setPort(machine.getPort());
        access.setHostName(machine.getHostName());
        access.setHost(hostId);
        access.setUsername(machine.getUsername());
        access.setTerminalType(config.getTerminalType());
        access.setBackgroundColor(config.getBackgroundColor());
        access.setFontSize(config.getFontSize());
        access.setFontFamily(config.getFontFamily());
        access.setFontColor(config.getFontColor());
        access.setEnableWebLink(config.getEnableWebLink());
        // 设置缓存
        String cacheKey = Strings.format(KeyConst.TERMINAL_ACCESS_TOKEN, token);
        redisTemplate.opsForValue().set(cacheKey, userId + "_" + hostId,
                KeyConst.TERMINAL_ACCESS_TOKEN_EXPIRE, TimeUnit.SECONDS);
        log.info("用户获取terminal uid: {} hostId: {} token: {}", userId, hostId, token);
        // 设置日志参数
        return access;
    }

    @Override
    public HostTerminalConfigDTO getHostTerminalConfig(String hostId) {
        HostTerminalConfig config = hostTerminalConfigRepository.getConfig(hostId);
        if (config == null) {
            // 初始化
            HostTerminalConfig insert = new HostTerminalConfig();
            insert.setHostId(hostId);
            insert.setTerminalType(TerminalType.XTERM.getType());
            insert.setBackgroundColor(TerminalConst.BACKGROUND_COLOR);
            insert.setFontColor(TerminalConst.FONT_COLOR);
            insert.setFontSize(TerminalConst.FONT_SIZE);
            insert.setFontFamily(TerminalConst.FONT_FAMILY);
            insert.setEnableWebLink(Const.DISABLE);
            insert.init(Constants.SYSTEM);
            hostTerminalConfigRepository.save(insert);
            config = insert;
        }
        return hostTerminalConfigAdapter.sourceToTarget(config);
    }

    @Override
    public void updateSetting(UpdateTerminalSettingCommand command) {
        HostTerminalConfig config = hostTerminalConfigRepository.getConfig(command.getHostId());
        config.setTerminalType(command.getTerminalType());
        config.setBackgroundColor(command.getBackgroundColor());
        config.setFontColor(command.getFontColor());
        config.setFontSize(command.getFontSize());
        config.setFontFamily(command.getFontFamily());
        config.setEnableWebLink(command.getEnableWebLink());
        config.modify(command.getUser());
        hostTerminalConfigRepository.update(config);
    }

    @Override
    public Long addTerminalLog(SaveTerminalLogCommand entity) {
        HostTerminalLog hostTerminalLog = new HostTerminalLog();
        hostTerminalLog.setUserId(entity.getUser());
        hostTerminalLog.setAccessToken(entity.getAccessToken());
        hostTerminalLog.setHostId(entity.getHostId());
        hostTerminalLog.setConnectedTime(entity.getConnectedTime());
        hostTerminalLog.setScreenPath(entity.getScreenPath());
        hostTerminalLog.init(entity.getUser());
        hostTerminalLogRepository.save(hostTerminalLog);
        return hostTerminalLog.getId();
    }

    @Override
    public Integer updateAccessLog(UpdateTerminalLogCommand entity) {
        HostTerminalLog hostTerminalLog = hostTerminalLogRepository.getConfig(entity.getAccessToken());
        hostTerminalLog.setHostId(entity.getHostId());
        hostTerminalLog.setDisconnectedTime(entity.getDisconnectedTime());
        hostTerminalLog.setCloseCode(entity.getCloseCode());
        hostTerminalLog.modify(entity.getUser());
        return hostTerminalLogRepository.update(hostTerminalLog);
    }

    @Override
    public Page<HostTerminalLogDTO> listAccessLog(TerminalAccessLogPageQuery request) {
        TerminalLogPageQueryParam terminalLogPageQueryParam = hostTerminalLogAdapter.convertToParam(request);
        Page<HostTerminalLog> page = hostTerminalLogRepository.pageQueryTerminalLog(terminalLogPageQueryParam);
        List<HostTerminalLog> logs = new ArrayList<>(page.getItems());
        List<HostTerminalLogDTO> logDTOList = hostTerminalLogAdapter.sourceToTarget(logs);
        return PageUtils.build(page, logDTOList);
    }

    @Override
    public Integer deleteTerminalLog(List<Long> idList) {
        // 删除
        return hostTerminalLogRepository.deleteByIds(idList);
    }

    @Override
    public Integer deleteTerminalByMachineIdList(List<String> hostIdList) {
        return null;
    }

    @Override
    public String getTerminalScreenFilePath(Long id) {
        return null;
    }
}
