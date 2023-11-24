package com.rany.cake.devops.base.service.code.gitlab;

import com.rany.cake.devops.base.service.code.BaseCodeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GitLabCodeService extends BaseCodeService {

    private final GitLabApi gitLabApi;

    public GitLabCodeService(String gitLabApiUrl, String gitLabApiToken) {
        this.gitLabApi = new GitLabApi(gitLabApiUrl, gitLabApiToken);
    }
}
