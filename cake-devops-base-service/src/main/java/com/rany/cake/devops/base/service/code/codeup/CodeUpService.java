//package com.rany.cake.devops.base.service.code.codeup;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHeaders;
//import org.apache.http.client.methods.*;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.time.Instant;
//import java.time.ZoneOffset;
//import java.time.format.DateTimeFormatter;
//import java.util.Base64;
//
//public class CodeUpService {
//
//    private final String codeUpApiUrl;
//    private final String accessKeyId;
//    private final String accessKeySecret;
//    private final CloseableHttpClient httpClient;
//
//    public CodeUpService(String codeUpApiUrl, String accessKeyId, String accessKeySecret) {
//        this.codeUpApiUrl = codeUpApiUrl;
//        this.accessKeyId = accessKeyId;
//        this.accessKeySecret = accessKeySecret;
//        this.httpClient = HttpClients.createDefault();
//    }
//
//    private String sendRequest(String method, String endpoint, String payload) throws IOException {
//        String fullUrl = codeUpApiUrl + endpoint;
//        switch (method) {
//            case "GET":
//                HttpGet httpGet = new HttpGet(fullUrl);
//                httpGet.setHeader(HttpHeaders.AUTHORIZATION, generateAuthorizationHeader(method, endpoint, httpGet));
//                return executeRequest(httpGet);
//            case "POST":
//                HttpPost httpPost = new HttpPost(fullUrl);
//                httpPost.setHeader(HttpHeaders.AUTHORIZATION, generateAuthorizationHeader(method, endpoint, httpPost));
//                httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//                httpPost.setEntity(new StringEntity(payload, StandardCharsets.UTF_8));
//                return executeRequest(httpPost);
//            case "PUT":
//                HttpPut httpPut = new HttpPut(fullUrl);
//                httpPut.setHeader(HttpHeaders.AUTHORIZATION, generateAuthorizationHeader(method, endpoint, httpPut));
//                httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//                httpPut.setEntity(new StringEntity(payload, StandardCharsets.UTF_8));
//                return executeRequest(httpPut);
//            case "DELETE":
//                HttpDelete httpDelete = new HttpDelete(fullUrl);
//                httpDelete.setHeader(HttpHeaders.AUTHORIZATION, generateAuthorizationHeader(method, endpoint, httpDelete));
//                return executeRequest(httpDelete);
//            default:
//                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
//        }
//    }
//
//    private String generateAuthorizationHeader(String method, String endpoint, HttpUriRequest request) {
//        String date = DateTimeFormatter.ISO_INSTANT.format(Instant.now().atZone(ZoneOffset.UTC));
//        request.setHeader(HttpHeaders.DATE, date);
//
//        String canonicalizedHeaders = HttpHeaders.DATE.toLowerCase() + ":" + date + "\n";
//        String canonicalizedResource = endpoint;
//        String stringToSign = method.toUpperCase() + "\n" +
//                canonicalizedHeaders + canonicalizedResource;
//
//        String signature = sign(stringToSign, accessKeySecret);
//        return "Signature " + accessKeyId + ":" + signature;
//    }
//
//    private String sign(String stringToSign, String secret) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-1");
//            byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
//            byte[] messageBytes = stringToSign.getBytes(StandardCharsets.UTF_8);
//            byte[] hash = digest.digest(keyBytes);
//            byte[] result = new byte[hash.length + messageBytes.length];
//            System.arraycopy(hash, 0, result, 0, hash.length);
//            System.arraycopy(messageBytes, 0, result, hash.length, messageBytes.length);
//            byte[] signature = digest.digest(result);
//            return Base64.getEncoder().encodeToString(signature);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException("Failed to generate SHA-1 signature", e);
//        }
//    }
//
//    private String executeRequest(HttpUriRequest request) throws IOException {
//        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                return EntityUtils.toString(entity);
//            }
//        }
//        return null;
//    }
//
//    // 创建仓库
//    public String createRepository(String name, String description) throws IOException {
//        String payload = "{\"name\": \"" + name + "\", \"description\": \"" + description + "\"}";
//        return sendRequest("POST", "/api/v1/repos", payload);
//    }
//
//    // 获取仓库信息
//    public String getRepository(String repoId) throws IOException {
//        return sendRequest("GET", "/api/v1/repos/" + repoId, "");
//    }
//
//    // 创建分支
//    public String createBranch(String repoId, String branch, String sha) throws IOException {
//        String payload = "{\"ref\": \"refs/heads/" + branch + "\", \"sha\": \"" + sha + "\"}";
//        return sendRequest("POST", "/api/v1/repos/" + repoId + "/git/refs", payload);
//    }
//
//    // 获取分支列表
//    public String listBranches(String repoId) throws IOException {
//        return sendRequest("GET", "/api/v1/repos/" + repoId + "/branches", "");
//    }
//
//    // 获取特定分支信息
//    public String getBranch(String repoId, String branch) throws IOException {
//        return sendRequest("GET", "/api/v1/repos/" + repoId + "/branches/" + branch, "");
//    }
//
//    // 创建标签
//    public String createTag(String repoId, String tag, String sha) throws IOException {
//        String payload = "{\"tag\": \"" + tag + "\", \"message\": \"Create tag\", \"object\": \"" + sha + "\", \"type\": \"commit\"}";
//        return sendRequest("POST", "/api/v1/repos/" + repoId + "/git/tags", payload);
//    }
//}
