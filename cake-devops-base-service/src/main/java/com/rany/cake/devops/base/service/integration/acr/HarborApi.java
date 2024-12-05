package com.rany.cake.devops.base.service.integration.acr;

import com.rany.cake.devops.base.domain.base.CrConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * harbor
 *
 * @author zhongshengwang
 * @description harbor
 * @date 2023/1/20 20:40
 * @email 18668485565163.com
 */
@Slf4j
@Component
public class HarborApi {
    @Resource
    private CrConfig crConfig;
    private static final HttpClient httpClient;

    static {
        httpClient = HttpClients.createDefault();
    }

    public void createRepo(String repoName) {
        // 替换以下URL和凭据为您的Harbor实例的实际值
        String harborApiUrl = crConfig.getHarbor().getHarborUrl() + "/api/repositories";
        String username = crConfig.getHarbor().getHarborUser();
        String password = crConfig.getHarbor().getHarborPassword();
        try {
            HttpPost httpPost = new HttpPost(harborApiUrl);
            httpPost.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
            httpPost.addHeader("Content-Type", "application/json");

            // 提供正确的JSON数据以创建仓库
            String jsonPayload = "{\"project_id\": " + crConfig.getHarbor().getDefaultProjectId() + ", \"name\": \"" + repoName + "\"}";
            StringEntity entity = new StringEntity(jsonPayload);
            httpPost.setEntity(entity);

            // 发送请求并处理响应
            HttpResponse response = httpClient.execute(httpPost);

            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            // 打印响应信息
            System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
            System.out.println("Response Body: " + responseBody);

        } catch (IOException e) {
            log.error("Create repo error", e);
        }
    }
}
