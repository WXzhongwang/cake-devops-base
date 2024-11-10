package com.rany.cake.devops.base.service.code.github;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class GitHubService {

    private final String githubApiUrl;
    private final String githubApiToken;
    private final CloseableHttpClient httpClient;
    private final String owner;

    public GitHubService(String githubApiUrl, String githubApiToken, String owner) {
        this.githubApiUrl = githubApiUrl;
        this.githubApiToken = githubApiToken;
        this.httpClient = HttpClients.createDefault();
        this.owner = owner;
    }

    private String sendRequest(String method, String endpoint, String payload) throws IOException {
        String fullUrl = githubApiUrl + endpoint;
        switch (method) {
            case "GET":
                HttpGet httpGet = new HttpGet(fullUrl);
                httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubApiToken);
                return executeRequest(httpGet);
            case "POST":
                HttpPost httpPost = new HttpPost(fullUrl);
                httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubApiToken);
                httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                httpPost.setEntity(new StringEntity(payload));
                return executeRequest(httpPost);
            case "PUT":
                HttpPut httpPut = new HttpPut(fullUrl);
                httpPut.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubApiToken);
                httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                httpPut.setEntity(new StringEntity(payload));
                return executeRequest(httpPut);
            case "DELETE":
                HttpDelete httpDelete = new HttpDelete(fullUrl);
                httpDelete.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubApiToken);
                return executeRequest(httpDelete);
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

    private String executeRequest(org.apache.http.client.methods.HttpUriRequest request) throws IOException {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        }
        return null;
    }


    public String getRepository(String repo) throws IOException {
        return sendRequest("GET", "/repos/" + owner + "/" + repo, "");
    }

    public String createBranch(String repo, String branch, String sha) throws IOException {
        String payload = "{\"ref\": \"refs/heads/" + branch + "\", \"sha\": \"" + sha + "\"}";
        return sendRequest("POST", "/repos/" + owner + "/" + repo + "/git/refs", payload);
    }

    public String getBranch(String repo, String branch) throws IOException {
        return sendRequest("GET", "/repos/" + owner + "/" + repo + "/branches/" + branch, "");
    }

    public String listBranches(String repo) throws IOException {
        return sendRequest("GET", "/repos/" + owner + "/" + repo + "/branches", "");
    }

    public String createTag(String repo, String tag, String sha) throws IOException {
        String payload = "{\"tag\": \"" + tag + "\", \"message\": \"Create tag\", \"object\": \"" + sha + "\", \"type\": \"commit\"}";
        return sendRequest("POST", "/repos/" + owner + "/" + repo + "/git/tags", payload);
    }
}

