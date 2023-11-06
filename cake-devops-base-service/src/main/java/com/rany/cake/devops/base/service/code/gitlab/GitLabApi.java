package com.rany.cake.devops.base.service.code.gitlab;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GitLabApi {

    private final String gitLabApiUrl;
    private final String gitLabApiToken;
    private HttpClient httpClient;

    public GitLabApi(String gitLabApiUrl, String gitLabApiToken) {
        this.gitLabApiUrl = gitLabApiUrl;
        this.gitLabApiToken = gitLabApiToken;
        this.httpClient = HttpClients.createDefault();
    }

    public String get(String endpoint) throws IOException {
        String url = gitLabApiUrl + endpoint;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HttpHeaders.ACCEPT, "application/json");
        httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer" + gitLabApiToken);

        HttpResponse response = httpClient.execute(httpGet);
        return EntityUtils.toString(response.getEntity());
    }

    public String post(String endpoint, String payload) throws IOException {
        String url = gitLabApiUrl + endpoint;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer" + gitLabApiToken);
        httpPost.setEntity(new StringEntity(payload));

        HttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }

    public String createRepository(String projectName, String visibility) throws IOException {
        String createProjectEndpoint = "/projects";
        String payload = "{\"name\": " + projectName + ", \"visibility\": " + visibility + "}";
        return post(createProjectEndpoint, payload);
    }

    public String getRepository(String projectId) throws IOException {
        String getProjectEndpoint = "/projects/" + projectId;
        return get(getProjectEndpoint);
    }

    public String createBranch(String projectId, String branchName, String ref) throws IOException {
        String createBranchEndpoint = "/projects/" + projectId + "/repository/branches";
        String payload = "{\"branch\": " + branchName + ", \"ref\": " + ref + "}";
        return post(createBranchEndpoint, payload);
    }

    public String getBranch(String projectId, String branchName) throws IOException {
        String getBranchEndpoint = "/projects/" + projectId + "/repository/branches/" + branchName;
        return get(getBranchEndpoint);
    }

    public String listBranches(String projectId) throws IOException {
        String listBranchEndpoint = "/projects/" + projectId + "/repository/branches";
        return get(listBranchEndpoint);
    }

    public String createTag(String projectId, String tagName, String ref) throws IOException {
        String createTagEndpoint = "/projects/" + projectId + "/repository/tags";
        String payload = "{\"tag_name\": " + tagName + ", \"ref\": " + ref + "}";
        return post(createTagEndpoint, payload);
    }

    public String getTag(String projectId, String tagName) throws IOException {
        String getTagEndpoint = "/projects/" + projectId + "/repository/tags/" + tagName;
        return get(getTagEndpoint);
    }

    public String listTags(String projectId, String tagName) throws IOException {
        String getTagEndpoint = "/projects/" + projectId + "/repository/tags";
        return get(getTagEndpoint);
    }
}
