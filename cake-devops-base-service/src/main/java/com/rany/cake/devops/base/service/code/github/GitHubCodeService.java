package com.rany.cake.devops.base.service.code.github;

import com.rany.cake.devops.base.service.code.BaseCodeService;
import com.rany.cake.devops.base.service.code.Branch;
import com.rany.cake.devops.base.service.code.RepoUrlUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class GitHubCodeService extends BaseCodeService {

    private final GitHubService gitHubService;

    public GitHubCodeService(String gitHubApiUrl, String token) {
        String[] pair = RepoUrlUtils.extractRepoInfo(gitHubApiUrl);
        String owner = pair[0];
        this.gitHubService = new GitHubService(gitHubApiUrl, token, owner);
    }

    @Override
    public Boolean createBranch(String repo, String branchName, String ref) {
        try {
            String branch = gitHubService.createBranch(repo, branchName, ref);
            log.info("branch:{}", branch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<Branch> listBranch(String repo, String search, Integer pageNo, Integer pageSize) {
        return null;
    }
}
