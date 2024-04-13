package com.rany.cake.devops.plugin.runner;

import com.alibaba.fastjson.TypeReference;
import com.rany.cake.devops.plugin.common.http.CakeOpsExposeHttpApi;
import com.rany.cake.devops.plugin.common.http.CakeOpsExposeHttpApiRequester;
import com.rany.cake.devops.plugin.common.http.HttpApiRequest;
import com.rany.cake.devops.plugin.common.http.vo.HostAlarmConfigWrapperDTO;
import com.rany.cake.devops.plugin.common.http.vo.MachineAlarmConfig;
import com.rany.cake.devops.plugin.constant.MachineAlarmType;
import com.rany.cake.devops.plugin.constant.PropertiesConst;
import com.rany.cake.devops.plugin.handler.AlarmChecker;
import com.rany.cake.toolkit.lang.utils.Lists;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 加载报警配置 runner
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2022/8/31 18:49
 */
@Slf4j
@Component
@Order(2000)
public class InitAlarmConfigRunner implements CommandLineRunner {

    @Resource
    private AlarmChecker alarmChecker;

    @Override
    public void run(String... args) {
        String machineId = PropertiesConst.HOST_ID;
        log.info("初始化报警配置-开始 hostId: {}", machineId);
        if (machineId == null) {
            return;
        }
        try {
            HttpApiRequest request = CakeOpsExposeHttpApiRequester.create(CakeOpsExposeHttpApi.GET_MACHINE_ALARM_CONFIG);
            request.queryParam("hostId", machineId);
            HttpWrapper<HostAlarmConfigWrapperDTO> wrapper = request.getJson(new TypeReference<HttpWrapper<HostAlarmConfigWrapperDTO>>() {
            });
            log.info("初始化报警配置-请求 {}", wrapper.toJsonString());
            if (!wrapper.isOk()) {
                return;
            }
            List<MachineAlarmConfig> data = wrapper.getContent().getAlarmConfigList();
            if (Lists.isEmpty(data)) {
                return;
            }
            data.forEach(s -> alarmChecker.getAlarmContext().put(MachineAlarmType.of(s.getType()), s));
        } catch (Exception e) {
            log.error("初始化报警配置-失败", e);
        }
    }

}
