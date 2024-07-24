package com.rany.cake.devops.base.service.code.github;

import com.rany.cake.devops.base.service.code.BaseCodeService;

public class GitHubCodeService extends BaseCodeService {

    private final GitHubService gitHubService;

    public GitHubCodeService(String gitHubApiUrl, String token) {
        this.gitHubService = new GitHubService(gitHubApiUrl, token);
    }

    @Override
    public Boolean createBranch(String repoUrl, String branchName, String ref) {
        return null;
    }
}
