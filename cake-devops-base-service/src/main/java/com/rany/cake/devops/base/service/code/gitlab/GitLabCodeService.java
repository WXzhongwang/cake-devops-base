package com.rany.cake.devops.base.service.code.gitlab;

import com.rany.cake.devops.base.service.code.BaseCodeService;
import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.models.Branch;

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
}
