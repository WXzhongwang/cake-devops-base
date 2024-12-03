package com.rany.cake.devops.base.domain.valueobject;

import lombok.Data;

/**
 * git
 * 仓库配置参数
 */
@Data
public class GitLabConfig {

    /**
     * domain a:
     *
     * @link https://xxxx.gitlab.com
     */
    private String domain;
    /**
     * github_pat_xxxx
     */
    private String token;
}
