package com.rany.cake.devops.base.service.code.github;

import com.rany.cake.devops.base.service.code.BaseCodeService;
import com.rany.cake.devops.base.service.code.RepoUrlUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class GitHubCodeService extends BaseCodeService {

    private final GitHubService gitHubService;

    public GitHubCodeService(String gitHubApiUrl, String token) {
        this.gitHubService = new GitHubService(gitHubApiUrl, token);
    }

    @Override
    public Boolean createBranch(String repoUrl, String branchName, String ref) {
        String[] strings = RepoUrlUtils.extractRepoInfo(repoUrl);
        try {
            String branch = gitHubService.createBranch(strings[0], strings[1], branchName, ref);
            log.info("branch:{}", branch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
