package com.rany.cake.devops.base.service.integration.code.gitlab;

import com.rany.cake.devops.base.service.integration.code.BaseCodeService;
import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.models.Branch;

import java.util.List;

@Slf4j
public class GitLabCodeService extends BaseCodeService {

    private final GitLabService gitLabService;

    public GitLabCodeService(String gitLabApiUrl, String gitLabApiToken) {
        this.gitLabService = new GitLabService(gitLabApiUrl, gitLabApiToken);
    }

    @Override
    public Boolean createBranch(String repoUrl, String branchName, String ref) {
        Branch branch = gitLabService.createBranch(repoUrl, branchName, ref);
        return branch != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<com.rany.cake.devops.base.api.dto.code.Branch> listBranch(String repo, String search, Integer pageNo, Integer pageSize) {
        return null;
    }
}
