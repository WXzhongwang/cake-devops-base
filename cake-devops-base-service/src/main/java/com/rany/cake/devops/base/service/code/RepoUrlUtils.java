package com.rany.cake.devops.base.service.code;

public class RepoUrlUtils {

    public static String[] extractNamespaceAndProject(String repoUrl) {
        // Git 仓库 URL 的正则表达式，同时支持 HTTP 和 HTTPS
        String regex = "https?://[^/]+/([^/]+)/([^/]+)\\.git";

        // 尝试匹配正则表达式
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(repoUrl);

        if (matcher.matches() && matcher.groupCount() == 2) {
            String namespace = matcher.group(1);
            String project = matcher.group(2);
            return new String[]{namespace, project};
        }
        return null;
    }

    public static String generateReleaseBranchName(String serialNumber) {
        return String.format("release/%s", serialNumber);
    }
}
