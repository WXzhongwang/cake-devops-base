package com.rany.cake.devops.base.service.code.codeup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CodeUpService {

    private final String domain;
    private final String organizationId;
    private final String personalAccessToken;
    private final OkHttpClient client;

    public CodeUpService(String domain, String organizationId, String personalAccessToken) {
        this.domain = domain;
        this.organizationId = organizationId;
        this.personalAccessToken = personalAccessToken;

        // 配置连接池化的OkHttpClient
        ConnectionPool connectionPool = new ConnectionPool(5, 5, TimeUnit.MINUTES);
        this.client = new OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public Boolean createBranch(String repo, String branch, String ref) {
        // 构建请求URL
        String requestUrl = String.format("https://%s/oapi/v1/codeup/organizations/%s/repositories/%s/branches?branch=%s&ref=%s",
                domain, organizationId, repo, branch, ref);

        // 构建请求体
//        JSONObject requestObject = new JSONObject();
//        requestObject.put("ref", ref);
//        requestObject.put("branch", branch);
//        requestObject.put("organizationId", organizationId);
//        requestObject.put("repositoryId", repo);
        RequestBody body = RequestBody.create(MediaType.get("application/json"), "");

        // 构建请求
        Request request = new Request.Builder()
                .url(requestUrl)
                .post(body)
                .header("Content-Type", "application/json")
                .header("x-yunxiao-token", personalAccessToken)
                .build();

        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                log.info("创建分支成功");
                return true;
            } else {
                log.error("创建分支失败: {} {}", response.code(), response.message());
            }
        } catch (IOException e) {
            log.error("创建分支时发生错误", e);
        }
        return false;
    }

    public List<Branch> listBranches(String repo, Integer page, Integer perPage, String sort, String search) {
        String requestUrl = String.format("https://%s/oapi/v1/codeup/organizations/%s/repositories/%s/branches",
                domain, organizationId, repo);
        // 构建请求体
        JSONObject requestObject = new JSONObject();
        requestObject.put("organizationId", organizationId);
        requestObject.put("repositoryId", repo);
        requestObject.put("page", page);
        requestObject.put("perPage", perPage);
        requestObject.put("sort", sort);
        requestObject.put("search", search);
        RequestBody body = RequestBody.create(MediaType.get("application/json"), requestObject.toString());

        // 构建请求
        Request request = new Request.Builder()
                .url(requestUrl)
                .post(body)
                .header("Content-Type", "application/json")
                .header("x-yunxiao-token", personalAccessToken)
                .build();

        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                List<Branch> branches = JSON.parseArray(responseBody, Branch.class);
                log.info("获取分支列表成功");
                return branches;
            }
        } catch (IOException e) {
            log.error("获取分支列表时发生错误", e);
        }
        return new ArrayList<>();
    }

    @Data
    public static class Branch {
        private Commit commit;
        private boolean defaultBranch;
        private String name;
        private boolean protectedBranch;
        private String webUrl;

        // Commit 类用于封装提交信息
        @Data
        public static class Commit {
            private String authorEmail;
            private String authorName;
            private String authoredDate;
            private String committedDate;
            private String committerEmail;
            private String committerName;
            private String id;
            private String message;
            private List<String> parentIds;
            private String shortId;
            private Stats stats;
            private String title;
            private String webUrl;

            @Data
            public static class Stats {
                private int additions;
                private int deletions;
                private int total;
            }
        }
    }
}
