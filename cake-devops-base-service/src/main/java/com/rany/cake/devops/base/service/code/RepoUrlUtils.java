package com.rany.cake.devops.base.service.code;

public class RepoUrlUtils {

    public static String[] extractNamespaceAndProject(String repoUrl) {
        // Git 仓库 URL 的正则表达式，同时支持 HTTP(S) 和 SSH
        String regex = "(?:https?://|ssh://[^@]+@)?(?:[^/]+\\.)?(?:(?:(?:[^/]+)@)?([^/]+)/(?:[^/]+)|([^/]+)/(.*))(?:\\.git)?";

        // 尝试匹配正则表达式
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(repoUrl);

        if (matcher.find()) {
            // 根据匹配组的数量选择合适的组
            if (matcher.groupCount() >= 3) {
                String namespace = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
                String project = matcher.group(3);
                return new String[]{namespace, project};
            }
        }
        return null;
    }


    public static String generateReleaseBranchName(String serialNumber) {
        return String.format("release/%s", serialNumber);
    }
}
