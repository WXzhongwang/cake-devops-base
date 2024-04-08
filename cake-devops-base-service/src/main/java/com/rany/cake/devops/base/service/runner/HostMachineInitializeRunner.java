package com.rany.cake.devops.base.service.runner;

import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.base.MonitorConst;
import com.rany.cake.devops.base.domain.base.SnowflakeIdWorker;
import com.rany.cake.devops.base.domain.entity.HostMonitor;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostRepository;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.base.ValueMix;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.enums.CommonStatusEnum;
import com.rany.cake.devops.base.util.enums.DeleteStatusEnum;
import com.rany.cake.devops.base.util.enums.MachineAuthType;
import com.rany.cake.devops.base.util.enums.MonitorStatus;
import com.rany.cake.toolkit.lang.net.IPs;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.lang.utils.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 宿主机初始化
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/3/29 18:54
 */
@Component
@Order(1600)
@Slf4j
public class HostMachineInitializeRunner implements CommandLineRunner {

    private static final String DEFAULT_PASSWORD = "admin123456";

    @Resource
    private HostRepository hostRepository;
    @Resource
    private HostDomainService hostDomainService;

    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public void run(String... args) {
        log.info("初始化宿主机配置-开始");
        this.initMachine();
        log.info("初始化宿主机配置-结束");
    }

    /**
     * 初始化宿主机
     */
    private void initMachine() {
        Host local = hostRepository.selectByPrimaryKey(Const.HOST_MACHINE_ID);
        if (local == null) {
            // 插入机器
            local = new Host(new HostId(String.valueOf(snowflakeIdWorker.nextId())), "宿主机",
                    SystemUtils.HOST_NAME, IPs.IP, 22);
            local.setDesc("宿主机");
            local.setUsername(SystemUtils.USER_NAME);
            local.setPwd(ValueMix.encrypt(DEFAULT_PASSWORD));
            local.setAuthType(MachineAuthType.PASSWORD.getType());
            local.setStatus(CommonStatusEnum.ENABLE.getValue());
            local.init("0");


            HostMonitor monitor = new HostMonitor();
            monitor.setHostId(local.getHostId().getHostId());
            monitor.setMonitorStatus(MonitorStatus.NOT_START.getStatus());
            monitor.setMonitorUrl(Strings.format(MonitorConst.DEFAULT_URL_FORMAT, local.getServerAddr()));
            monitor.setAccessToken(MonitorConst.DEFAULT_ACCESS_TOKEN);
            monitor.setIsDeleted(DeleteStatusEnum.NO.getValue());
            monitor.setAccessToken(MonitorConst.DEFAULT_ACCESS_TOKEN);
            monitor.init("0");

            hostDomainService.save(local, null, monitor);
        }
    }

}
