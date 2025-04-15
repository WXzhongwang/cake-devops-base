package com.rany.cake.devops.base.service.app;

import com.alibaba.fastjson.JSON;
import com.rany.cake.devops.base.api.command.system.ConfigIpListCommand;
import com.rany.cake.devops.base.api.dto.system.*;
import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.api.service.SystemService;
import com.rany.cake.devops.base.domain.entity.SystemEnv;
import com.rany.cake.devops.base.domain.repository.SystemEnvRepository;
import com.rany.cake.devops.base.service.adapter.SystemEnvDataAdapter;
import com.rany.cake.devops.base.service.handler.FileCleaner;
import com.rany.cake.devops.base.service.interceptor.IpFilterInterceptor;
import com.rany.cake.devops.base.service.utils.Utils;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.MessageConst;
import com.rany.cake.devops.base.util.Valid;
import com.rany.cake.devops.base.util.enums.EnableType;
import com.rany.cake.devops.base.util.system.SystemCleanType;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.devops.base.util.system.ThreadPoolMetricsType;
import com.rany.cake.toolkit.lang.Threads;
import com.rany.cake.toolkit.lang.convert.Converts;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.time.Dates;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/4/15 18:06
 * @slogon 找到银弹
 */
@Slf4j
@Service
@AllArgsConstructor
public class SystemServiceImpl implements SystemService {
    private final SystemEnvRepository systemEnvRepository;
    private final SystemEnvDataAdapter systemEnvDataAdapter;
    private final IpFilterInterceptor ipFilterInterceptor;

    @Override
    public IpListConfigDTO getIpInfo(String ip) {
        IpListConfigDTO ipConfig = new IpListConfigDTO();
        // 查询黑名单
        ipConfig.setBlackIpList(SystemEnvAttr.BLACK_IP_LIST.getValue());
        // 查询白名单
        ipConfig.setWhiteIpList(SystemEnvAttr.WHITE_IP_LIST.getValue());
        // 查询是否启用过滤
        ipConfig.setEnableIpFilter(EnableType.of(SystemEnvAttr.ENABLE_IP_FILTER.getValue()).getValue());
        // 查询是否启用IP白名单
        ipConfig.setEnableWhiteIpList(EnableType.of(SystemEnvAttr.ENABLE_WHITE_IP_LIST.getValue()).getValue());
        // ip
        ipConfig.setCurrentIp(ip);
        // ip 位置
        ipConfig.setIpLocation(Utils.getIpLocation(ip));
        return ipConfig;
    }

    /**
     * 校验 ip 过滤列表
     *
     * @param ipList ipList
     */
    private void validIpConfig(String ipList) {
        if (Strings.isBlank(ipList)) {
            return;
        }
        String[] lines = ipList.split(Const.LF);
        for (String ip : lines) {
            if (Strings.isBlank(ip)) {
                continue;
            }
            Valid.isTrue(Utils.validIpLine(ip), Strings.format("{} " + MessageConst.INVALID_CONFIG, ip));
        }
    }

    @Override
    public void configIpFilterList(ConfigIpListCommand request) {
        // 检查名单
        String blackIpList = request.getBlackIpList();
        String whiteIpList = request.getWhiteIpList();
        this.validIpConfig(blackIpList);
        this.validIpConfig(whiteIpList);
        // 设置黑名单
        SystemEnv blackEnv = systemEnvRepository.findByName(SystemEnvAttr.BLACK_IP_LIST.getKey());
        blackEnv.setAttrValue(blackIpList);
        blackEnv.setGmtModified(Dates.date());
        blackEnv.setModifier(request.getUser());
        systemEnvRepository.update(blackEnv);
        SystemEnvAttr.BLACK_IP_LIST.setValue(blackIpList);

        // 设置白名单
        SystemEnv whiteEnv = systemEnvRepository.findByName(SystemEnvAttr.WHITE_IP_LIST.getKey());
        whiteEnv.setAttrValue(whiteIpList);
        whiteEnv.setGmtModified(Dates.date());
        whiteEnv.setModifier(request.getUser());
        systemEnvRepository.update(whiteEnv);
        SystemEnvAttr.WHITE_IP_LIST.setValue(whiteIpList);

        // 更改启用状态
        EnableType enableIpFilter = EnableType.of(request.getEnableIpFilter());
        SystemEnv filterEnv = systemEnvRepository.findByName(SystemEnvAttr.ENABLE_IP_FILTER.getKey());
        filterEnv.setAttrValue(enableIpFilter.getLabel());
        filterEnv.setGmtModified(Dates.date());
        filterEnv.setModifier(request.getUser());
        systemEnvRepository.update(filterEnv);
        SystemEnvAttr.ENABLE_IP_FILTER.setValue(enableIpFilter.getLabel());

        // 更改启用列表
        EnableType enableWhiteIp = EnableType.of(request.getEnableWhiteIpList());
        SystemEnv enableWhiteIpEnv = systemEnvRepository.findByName(SystemEnvAttr.ENABLE_WHITE_IP_LIST.getKey());
        enableWhiteIpEnv.setGmtModified(Dates.date());
        enableWhiteIpEnv.setModifier(request.getUser());
        enableWhiteIpEnv.setAttrValue(enableWhiteIp.getLabel());
        systemEnvRepository.update(enableWhiteIpEnv);
        SystemEnvAttr.ENABLE_WHITE_IP_LIST.setValue(enableWhiteIp.getLabel());

        // 设置 ip 过滤器
        Boolean enableIpFilterValue = enableIpFilter.getValue();
        Boolean enableWhiteIpValue = enableWhiteIp.getValue();
        ipFilterInterceptor.set(enableIpFilterValue, enableWhiteIpValue, enableWhiteIpValue ? whiteIpList : blackIpList);
    }

    @Override
    public void cleanSystemFile(SystemCleanType cleanType) {
        // 清理
        Threads.start(() -> FileCleaner.cleanDir(cleanType));
    }

    @Override
    public SystemSpaceAnalysisDTO analysisSystemSpace() {
        SystemSpaceAnalysisDTO systemSpace = new SystemSpaceAnalysisDTO();
        // 临时文件
        File tempPath = new File(SystemEnvAttr.TEMP_PATH.getValue());
        List<File> tempFiles = Files1.listFiles(tempPath, true);
        long tempFilesBytes = tempFiles.stream().mapToLong(File::length).sum();
        systemSpace.setTempFileCount(tempFiles.size());
        systemSpace.setTempFileSize(Files1.getSize(tempFilesBytes));
        tempFiles.clear();

        // 日志文件
        File logPath = new File(SystemEnvAttr.LOG_PATH.getValue());
        List<File> logFiles = Files1.listFiles(logPath, true);
        long logFilesBytes = logFiles.stream().mapToLong(File::length).sum();
        systemSpace.setLogFileCount(logFiles.size());
        systemSpace.setLogFileSize(Files1.getSize(logFilesBytes));
        logFiles.clear();

        // 交换区文件
        File swapPath = new File(SystemEnvAttr.SWAP_PATH.getValue());
        List<File> swapFiles = Files1.listFiles(swapPath, true);
        long swapFilesBytes = swapFiles.stream().mapToLong(File::length).sum();
        systemSpace.setSwapFileCount(swapFiles.size());
        systemSpace.setSwapFileSize(Files1.getSize(swapFilesBytes));
        swapFiles.clear();

        // 构建产物
        File buildPath = new File(SystemEnvAttr.DIST_PATH.getValue() + Const.BUILD_DIR);
        List<File> buildFiles = Files1.listFiles(buildPath, true);
        long buildFilesBytes = buildFiles.stream().filter(File::isFile).mapToLong(File::length).sum();
        int distVersions = Files1.listDirs(buildPath).size();
        systemSpace.setDistVersionCount(distVersions);
        systemSpace.setDistFileSize(Files1.getSize(buildFilesBytes));
        buildFiles.clear();


        // 录屏文件
        File screenPath = new File(SystemEnvAttr.SCREEN_PATH.getValue());
        List<File> screenFiles = Files1.listFiles(screenPath, true);
        long screenFilesBytes = screenFiles.stream().mapToLong(File::length).sum();
        systemSpace.setScreenFileCount(screenFiles.size());
        systemSpace.setScreenFileSize(Files1.getSize(screenFilesBytes));
        screenFiles.clear();
        log.info("分析占用磁盘空间完成 {}", JSON.toJSONString(systemSpace));
        return systemSpace;
    }


    @Override
    public SystemAnalysisDTO getSystemAnalysis() {
        SystemSpaceAnalysisDTO systemSpace = this.analysisSystemSpace();
        SystemAnalysisDTO vo = Converts.to(systemSpace, SystemAnalysisDTO.class);
        // 文件清理
        vo.setFileCleanThreshold(Integer.valueOf(SystemEnvAttr.FILE_CLEAN_THRESHOLD.getValue()));
        vo.setAutoCleanFile(EnableType.of(SystemEnvAttr.ENABLE_AUTO_CLEAN_FILE.getValue()).getValue());
        return vo;
    }

    @Override
    public SystemOptionDTO getSystemOptions() {
        SystemOptionDTO options = new SystemOptionDTO();
        options.setAutoCleanFile(EnableType.of(SystemEnvAttr.ENABLE_AUTO_CLEAN_FILE.getValue()).getValue());
        options.setFileCleanThreshold(Integer.valueOf(SystemEnvAttr.FILE_CLEAN_THRESHOLD.getValue()));
        options.setAllowMultipleLogin(EnableType.of(SystemEnvAttr.ALLOW_MULTIPLE_LOGIN.getValue()).getValue());
        options.setLoginFailureLock(EnableType.of(SystemEnvAttr.LOGIN_FAILURE_LOCK.getValue()).getValue());
        options.setLoginIpBind(EnableType.of(SystemEnvAttr.LOGIN_IP_BIND.getValue()).getValue());
        options.setLoginTokenAutoRenew(EnableType.of(SystemEnvAttr.LOGIN_TOKEN_AUTO_RENEW.getValue()).getValue());
        options.setLoginTokenExpire(Integer.valueOf(SystemEnvAttr.LOGIN_TOKEN_EXPIRE.getValue()));
        options.setLoginFailureLockThreshold(Integer.valueOf(SystemEnvAttr.LOGIN_FAILURE_LOCK_THRESHOLD.getValue()));
        options.setLoginTokenAutoRenewThreshold(Integer.valueOf(SystemEnvAttr.LOGIN_TOKEN_AUTO_RENEW_THRESHOLD.getValue()));
        options.setResumeEnableSchedulerTask(EnableType.of(SystemEnvAttr.RESUME_ENABLE_SCHEDULER_TASK.getValue()).getValue());
        options.setTerminalActivePushHeartbeat(EnableType.of(SystemEnvAttr.TERMINAL_ACTIVE_PUSH_HEARTBEAT.getValue()).getValue());
        options.setSftpUploadThreshold(Integer.valueOf(SystemEnvAttr.SFTP_UPLOAD_THRESHOLD.getValue()));
        options.setStatisticsCacheExpire(Integer.valueOf(SystemEnvAttr.STATISTICS_CACHE_EXPIRE.getValue()));
        return options;
    }

    @Override
    public List<ThreadPoolMetricsDTO> getThreadPoolMetrics() {
        return Arrays.stream(ThreadPoolMetricsType.values())
                .map(this::getThreadPoolMetrics)
                .collect(Collectors.toList());
    }


    @Override
    public void updateSystemOption(SystemEnvAttr env, String value) {
        String key = env.getKey();
        String beforeValue = env.getValue();
        // 更新系统配置
        SystemEnv current = systemEnvRepository.findByName(env.getKey());
        if (current == null) {
            throw new DevOpsException(DevOpsErrorMessage.SYSTEM_ENV_NOT_FOUND);
        }
        if (Objects.equals(current.getAttrValue(), value)) {
            log.warn("系统配置未更新, key: {}, beforeValue:{}", key, beforeValue);
            return;
        }
        current.setAttrValue(value);
        current.setGmtModified(Dates.date());
        systemEnvRepository.update(current);
        env.setValue(value);
    }

    /**
     * 获取线程池指标
     *
     * @param metricsType 指标类型
     * @return 指标
     */
    private ThreadPoolMetricsDTO getThreadPoolMetrics(ThreadPoolMetricsType metricsType) {
        ThreadPoolMetricsDTO metrics = new ThreadPoolMetricsDTO();
        metrics.setType(metricsType.getType());
        ThreadPoolExecutor executor = metricsType.getExecutor();
        metrics.setActiveThreadCount(executor.getActiveCount());
        metrics.setTotalTaskCount(executor.getTaskCount());
        metrics.setCompletedTaskCount(executor.getCompletedTaskCount());
        metrics.setQueueSize(executor.getQueue().size());
        return metrics;
    }

}
