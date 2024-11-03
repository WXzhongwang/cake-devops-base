package com.rany.cake.devops.base.sls;

import com.aliyun.openservices.log.common.Index;
import com.aliyun.openservices.log.common.LogContent;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.common.LogStore;
import com.aliyun.openservices.log.common.QueriedLog;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.response.GetLogsResponse;
import com.aliyun.openservices.log.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SlsQuickStart {
    /**
     * 本示例从环境变量中获取AccessKey ID和AccessKey Secret。
     */
    static String accessId = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID");
    static String accessKey = System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET");
    /**
     * 日志服务的服务接入点。此处以杭州为例，其它地域请根据实际情况填写。
     */
    static String host = "cn-hangzhou.log.aliyuncs.com";
    /**
     * 创建日志服务Client。
     */
    static Client client = new Client(host, accessId, accessKey);
    /**
     * // Project名称。
     */
    static String projectName = "aliyun-test-gs-project";
    /**
     * Logstore名称。
     */
    static String logstoreName = "aliyun-test-logstore";
    /**
     * 查询语句。
     */
    static String query = "*| select * from " + logstoreName;

    /**
     * 创建Project。
     *
     * @throws LogException
     * @throws InterruptedException
     */
    static void createProject() throws LogException, InterruptedException {
        String projectDescription = "project description";
        System.out.println("ready to create project");
        client.CreateProject(projectName, projectDescription);
        System.out.println(String.format("create project %s success", projectName));
        TimeUnit.SECONDS.sleep(60 * 2);
    }

    /**
     * 创建Logstore。
     *
     * @throws LogException
     * @throws InterruptedException
     */
    static void createLogstore() throws LogException, InterruptedException {
        System.out.println("ready to create logstore");
        int ttlInDay = 3;     // 数据保存时间。如果配置为3650，表示永久保存。单位为天。
        int shardCount = 2;   // Shard数量。
        LogStore store = new LogStore(logstoreName, ttlInDay, shardCount);
        client.CreateLogStore(projectName, store);
        System.out.println(String.format("create logstore %s success", logstoreName));
        TimeUnit.SECONDS.sleep(60);
    }

    /**
     * 为Logstore创建索引。
     *
     * @throws LogException
     * @throws InterruptedException
     */
    static void createIndex() throws LogException, InterruptedException {
        System.out.println(String.format("ready to create index for %s", logstoreName));
        String logstoreIndex = "{\"line\": {\"token\": [\",\", \" \", \"'\", \"\\\"\", \";\", \"=\", \"(\", \")\", \"[\", \"]\", \"{\", \"}\", \"?\", \"@\", \"&\", \"<\", \">\", \"/\", \":\", \"\\n\", \"\\t\", \"\\r\"], \"caseSensitive\": false, \"chn\": false}, \"keys\": {\"dev\": {\"type\": \"text\", \"token\": [\",\", \" \", \"'\", \"\\\"\", \";\", \"=\", \"(\", \")\", \"[\", \"]\", \"{\", \"}\", \"?\", \"@\", \"&\", \"<\", \">\", \"/\", \":\", \"\\n\", \"\\t\", \"\\r\"], \"caseSensitive\": false, \"alias\": \"\", \"doc_value\": true, \"chn\": false}, \"id\": {\"type\": \"long\", \"alias\": \"\", \"doc_value\": true}}, \"log_reduce\": false, \"max_text_len\": 2048}";
        Index index = new Index();
        index.FromJsonString(logstoreIndex);
        client.CreateIndex(projectName, logstoreName, index);
        System.out.println(String.format("create index for %s success", logstoreName));
        TimeUnit.SECONDS.sleep(60);
    }

    /**
     * 向Logstore写入数据。为了提高您系统的IO效率，请尽量不要直接使用该方式往日志服务中写数据，此方式仅为功能举例。在大数据、高并发场景下建议使用Aliyun Log Java Producer方式写入日志数据。
     *
     * @throws LogException
     * @throws InterruptedException
     */
    static void pushLogs() throws LogException, InterruptedException {
        System.out.println(String.format("ready to push logs for %s", logstoreName));
        List<LogItem> logGroup = new ArrayList<LogItem>();
        for (int i = 0; i < 100; ++i) {
            LogItem logItem = new LogItem();
            logItem.PushBack("id", String.valueOf(i));
            logItem.PushBack("dev", "test_push");
            logGroup.add(logItem);
        }
        client.PutLogs(projectName, logstoreName, "", logGroup, "");
        System.out.println(String.format("push logs for %s success", logstoreName));
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * 通过SQL查询日志。
     *
     * @throws LogException
     */
    static void queryLogs() throws LogException {
        System.out.println(String.format("ready to query logs from %s", logstoreName));
        // fromTime和toTime表示查询日志的时间范围，Unix时间戳格式。
        int fromTime = (int) (System.currentTimeMillis() / 1000 - 3600);
        int toTime = fromTime + 3600;
        GetLogsResponse getLogsResponse = client.GetLogs(projectName, logstoreName, fromTime, toTime, "", query);
        for (QueriedLog log : getLogsResponse.getLogs()) {
            for (LogContent mContent : log.mLogItem.mContents) {
                System.out.println(mContent.mKey + " : " + mContent.mValue);
            }
            System.out.println("********************");
        }
    }

    public static void main(String[] args) throws LogException, InterruptedException {
        /**
         *  创建Project。
         */
        createProject();
        /**
         * 创建Logstore。
         */
        createLogstore();
        /**
         * 创建索引。
         */
        createIndex();
        /**
         * 写入日志数据。
         */
        pushLogs();
        /**
         * 查询日志。
         */
        queryLogs();
    }
}
