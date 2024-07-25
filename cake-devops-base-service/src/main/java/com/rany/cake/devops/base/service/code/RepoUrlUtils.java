package com.rany.cake.devops.base.service.code;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RepoUrlUtils {

    // GitHub SSH 格式的 URL 正则表达式
    private static final Pattern GITHUB_SSH_URL_PATTERN = Pattern.compile("git@github.com:([^/]+)/([^/]+).git");

    // GitHub HTTP/HTTPS 格式的 URL 正则表达式
    private static final Pattern GITHUB_HTTP_URL_PATTERN = Pattern.compile("https?://github.com/([^/]+)/([^/]+)/?");

    // GitLab SSH 格式的 URL 正则表达式
    // 注意：这里假设 GitLab 域名是 gitlab.example.com，你可能需要根据实际情况修改它
    private static final Pattern GITLAB_SSH_URL_PATTERN = Pattern.compile("git@(?:\\[?([0-9]{1,3}\\.){3}[0-9]{1,3}\\]?:?)?(?:[0-9]+)?:([^/]+)/([^/]+)\\.git");

    // GitLab HTTP/HTTPS 格式的 URL 正则表达式
    // 同样，这里假设 GitLab 域名是 gitlab.example.com
    private static final Pattern GITLAB_HTTP_URL_PATTERN = Pattern.compile("https?://(?:\\[?([0-9]{1,3}\\.){3}[0-9]{1,3}\\]?|[^/:]+)(?::[0-9]+)?(?:/([^/]+))?/([^/]+)/([^/]+)\\.git\n");

    // 提取 GitHub SSH 格式的 URL 信息
    private static String[] extractFromGithubSsh(String sshRepoUrl) {
        Matcher matcher = GITHUB_SSH_URL_PATTERN.matcher(sshRepoUrl);
        if (matcher.find()) {
            String group = matcher.group(1);
            String project = matcher.group(2);
            System.out.println("Group (GitLab SSH): " + group);
            System.out.println("Project (GitLab SSH): " + project);
            return new String[]{group, project};
        }
        return new String[]{};
    }

    // 提取 GitHub HTTP/HTTPS 格式的 URL 信息
    private static String[] extractFromGithubHttp(String httpRepoUrl) {
        Matcher matcher = GITHUB_HTTP_URL_PATTERN.matcher(httpRepoUrl);
        if (matcher.find()) {
            String group = matcher.group(1);
            String project = matcher.group(2);
            System.out.println("Group (GitLab SSH): " + group);
            System.out.println("Project (GitLab SSH): " + project);
            return new String[]{group, project};
        }
        return new String[]{};
    }

    // 提取 GitLab SSH 格式的 URL 信息
    private static String[] extractFromGitlabSsh(String sshRepoUrl) {
        Matcher matcher = GITLAB_SSH_URL_PATTERN.matcher(sshRepoUrl);
        if (matcher.find()) {
            String group = matcher.group(1);
            String project = matcher.group(2);
            System.out.println("Group (GitLab SSH): " + group);
            System.out.println("Project (GitLab SSH): " + project);
            return new String[]{group, project};
        }
        return new String[]{};
    }

    // 提取 GitLab HTTP/HTTPS 格式的 URL 信息
    private static String[] extractFromGitlabHttp(String httpRepoUrl) {
        Matcher matcher = GITLAB_HTTP_URL_PATTERN.matcher(httpRepoUrl);
        if (matcher.find()) {
            String group = matcher.group(1);
            String project = matcher.group(2);
            System.out.println("Group (GitLab HTTP): " + group);
            System.out.println("Project (GitLab HTTP): " + project);
            return new String[]{group, project};
        }
        return new String[]{};
    }

    public static String[] extractRepoInfo(String repoUrl) {
        // 尝试匹配 GitHub SSH 格式的 URL
        if (extractFromGithubSsh(repoUrl).length > 0) {
            return extractFromGithubSsh(repoUrl);
        }

        // 尝试匹配 GitHub HTTP/HTTPS 格式的 URL
        if (extractFromGithubHttp(repoUrl).length > 0) {
            return extractFromGithubHttp(repoUrl);
        }

        // 尝试匹配 GitLab SSH 格式的 URL
        if (extractFromGitlabSsh(repoUrl).length > 0) {
            return extractFromGitlabSsh(repoUrl);
        }

        // 尝试匹配 GitLab HTTP/HTTPS 格式的 URL
        if (extractFromGitlabHttp(repoUrl).length > 0) {
            return extractFromGitlabHttp(repoUrl);
        }

        // 如果都不是，则记录日志并返回一个空数组
        log.info("Invalid GitHub or GitLab URL format");
        return new String[]{};
    }

    public static String generateReleaseBranchName(String serialNumber) {
        return String.format("release/%s", serialNumber);
    }
}
