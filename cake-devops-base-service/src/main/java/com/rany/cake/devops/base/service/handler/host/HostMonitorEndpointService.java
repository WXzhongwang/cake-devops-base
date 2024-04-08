package com.rany.cake.devops.base.service.handler.host;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rany.cake.devops.base.api.dto.HostMonitorDTO;
import com.rany.cake.devops.base.api.service.HostMonitorService;
import com.rany.cake.devops.base.service.base.ParamConst;
import com.rany.cake.devops.base.service.handler.http.HttpApiRequest;
import com.rany.cake.devops.base.service.handler.http.HttpApiRequester;
import com.rany.cake.devops.base.service.handler.http.MachineMonitorHttpApi;
import com.rany.cake.devops.base.service.handler.http.MachineMonitorHttpApiRequester;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.Valid;
import com.rany.cake.toolkit.lang.wrapper.HttpWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("hostMonitorEndpointService")
public class HostMonitorEndpointService {

    @Resource
    private HostMonitorService machineMonitorService;

    public Integer ping(String machineId) {
        HttpWrapper<Integer> wrapper = this.getRequester(machineId, MachineMonitorHttpApi.ENDPOINT_PING)
                .request(Integer.class);
        return Valid.api(wrapper);
    }

    public JSONObject getBaseMetrics(String machineId) {
        HttpApiRequest request = this.getRequester(machineId, MachineMonitorHttpApi.METRICS_BASE)
                .getRequest();
        request.queryParam(ParamConst.LIMIT, Const.N_10);
        HttpWrapper<JSONObject> wrapper = request.getHttpWrapper(JSONObject.class);
        return Valid.api(wrapper);
    }

    public JSONObject getSystemLoad(String machineId) {
        HttpWrapper<JSONObject> wrapper = this.getRequester(machineId, MachineMonitorHttpApi.METRICS_SYSTEM_LOAD)
                .request(JSONObject.class);
        return Valid.api(wrapper);
    }

    public JSONArray getTopProcesses(String machineId, String name) {
        HttpApiRequest request = this.getRequester(machineId, MachineMonitorHttpApi.METRICS_TOP_PROCESSES)
                .getRequest();
        request.queryParam(ParamConst.LIMIT, Const.N_10)
                .queryParam(ParamConst.NAME, name);
        HttpWrapper<JSONArray> wrapper = request.getHttpWrapper(JSONArray.class);
        return Valid.api(wrapper);
    }

    public JSONArray getDiskName(String machineId) {
        HttpWrapper<JSONArray> res = this.getRequester(machineId, MachineMonitorHttpApi.METRICS_DISK_NAME)
                .getRequest()
                .getHttpWrapper(JSONArray.class);
        return Valid.api(res);
    }

    public JSONObject getCpuChart(MachineMonitorEndpointRequest request) {
        return getStatisticsChart(request, MachineMonitorHttpApi.MONITOR_CPU);
    }

    public JSONObject getMemoryChart(MachineMonitorEndpointRequest request) {
        return getStatisticsChart(request, MachineMonitorHttpApi.MONITOR_MEMORY);
    }

    public JSONObject getNetChart(MachineMonitorEndpointRequest request) {
        return getStatisticsChart(request, MachineMonitorHttpApi.MONITOR_NET);
    }

    public JSONObject getDiskChart(MachineMonitorEndpointRequest request) {
        return getStatisticsChart(request, MachineMonitorHttpApi.MONITOR_DISK);
    }

    private JSONObject getStatisticsChart(MachineMonitorEndpointRequest request, MachineMonitorHttpApi api) {
        HttpWrapper<JSONObject> res = this.getRequester(request.getHostId(), api)
                .getRequest()
                .jsonBody(request)
                .getHttpWrapper(JSONObject.class);
        return Valid.api(res);
    }

    /**
     * 获取 agent api 请求
     *
     * @param machineId machineId
     * @param api       api
     * @return request
     */
    private HttpApiRequester<?> getRequester(String machineId, MachineMonitorHttpApi api) {
        HostMonitorDTO monitor = machineMonitorService.findByHostId(machineId);
        return MachineMonitorHttpApiRequester.builder()
                .url(monitor.getMonitorUrl())
                .accessToken(monitor.getAccessToken())
                .api(api)
                .build();
    }

}