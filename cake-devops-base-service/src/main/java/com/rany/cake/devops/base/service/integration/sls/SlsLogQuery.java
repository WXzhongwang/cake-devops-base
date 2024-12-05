package com.rany.cake.devops.base.service.integration.sls;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.QueriedLog;
import com.aliyun.openservices.log.request.GetLogsRequest;
import com.aliyun.openservices.log.response.GetLogsResponse;

import java.util.ArrayList;
import java.util.List;


public class SlsLogQuery {

    private final Client client;
    private final String project;
    private final String logstore;

    private static final int PAGE_SIZE = 100;

    public SlsLogQuery(String endpoint, String accessKeyId, String accessKeySecret, String project, String logstore) {
        this.client = new Client(endpoint, accessKeyId, accessKeySecret);
        this.project = project;
        this.logstore = logstore;
    }

    public void close() {
        client.shutdown();
    }


    /**
     * 根据 pipeKey 查询 SLS 日志
     *
     * @param pipeKey 要查询的 pipeKey
     * @param from    查询的起始时间（毫秒）
     * @param to      查询的结束时间（毫秒）
     * @return 包含日志项的列表
     * @throws Exception 如果查询失败
     */
    /**
     * 根据 pipeKey 查询 SLS 日志
     *
     * @param pipeKey 要查询的 pipeKey
     * @param from    查询的起始时间（毫秒）
     * @param to      查询的结束日志（毫秒）
     * @return 包含日志项的列表
     * @throws Exception 如果查询失败
     */
    public List<QueriedLog> queryLogsByPipeKey(String pipeKey, long from, long to) throws Exception {
        List<QueriedLog> allLogs = new ArrayList<>();
        long offset = 0;

        do {
            // 构建查询请求
            GetLogsRequest request = new GetLogsRequest(project, logstore, (int) from, (int) to, null,
                    "__pipe_key__:" + pipeKey, offset, PAGE_SIZE, false);
            // 发送查询请求
            GetLogsResponse response = client.GetLogs(request);
            List<QueriedLog> logs = response.getLogs();
            allLogs.addAll(logs);

            // 更新 offset
            offset += logs.size();

            // 检查是否有更多日志
            if (logs.size() < PAGE_SIZE) {
                break; // 没有更多日志了
            }
        } while (true);

        return allLogs;
    }
}






