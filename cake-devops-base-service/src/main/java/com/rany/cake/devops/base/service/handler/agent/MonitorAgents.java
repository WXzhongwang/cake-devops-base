package com.rany.cake.devops.base.service.handler.agent;

import com.rany.cake.devops.base.api.dto.HostAlarmConfigDTO;
import com.rany.cake.devops.base.domain.entity.HostAlarmConfig;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.HostAlarmConfigRepository;
import com.rany.cake.devops.base.service.adapter.HostAlarmConfigDataAdapter;
import com.rany.cake.devops.base.service.handler.agent.request.MachineMonitorSyncRequest;
import com.rany.cake.devops.base.service.handler.http.MachineMonitorHttpApi;
import com.rany.cake.devops.base.service.handler.http.MachineMonitorHttpApiRequester;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MonitorAgents {

    @Resource
    private HostAlarmConfigRepository hostAlarmConfigRepository;
    @Resource
    private HostAlarmConfigDataAdapter hostAlarmConfigDataAdapter;

    public String getMonitorVersion(String url, String accessToken) {
        try {
            return MachineMonitorHttpApiRequester.builder()
                    .url(url)
                    .accessToken(accessToken)
                    .api(MachineMonitorHttpApi.ENDPOINT_VERSION)
                    .build()
                    .request(String.class)
                    .getContent();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 同步并且获取插件版本
     *
     * @param hostId
     * @param url
     * @param accessToken
     * @return
     */
    public String syncMonitorAgent(String hostId, String url, String accessToken) {
        try {
            // 设置同步请求
            MachineMonitorSyncRequest syncRequest = new MachineMonitorSyncRequest();
            syncRequest.setHostId(hostId);
            // 查询报警配置
            List<HostAlarmConfig> hostAlarmConfigs = hostAlarmConfigRepository.find(new HostId(hostId));
            List<HostAlarmConfigDTO> hostAlarmConfigDTOList = hostAlarmConfigDataAdapter.sourceToTarget(hostAlarmConfigs);
            syncRequest.setAlarmConfig(hostAlarmConfigDTOList);
            // 请求
            return MachineMonitorHttpApiRequester.builder()
                    .url(url)
                    .accessToken(accessToken)
                    .api(MachineMonitorHttpApi.ENDPOINT_SYNC)
                    .build()
                    .getRequest()
                    .jsonBody(syncRequest)
                    .getHttpWrapper(String.class)
                    .getContent();
        } catch (Exception e) {
            return null;
        }
    }
}
