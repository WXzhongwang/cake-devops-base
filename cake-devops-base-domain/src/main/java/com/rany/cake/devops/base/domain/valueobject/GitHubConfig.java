package com.rany.cake.devops.base.domain.valueobject;

import lombok.Data;

/**
 * git
 * 仓库配置参数
 */
@Data
public class GitHubConfig {

    /**
     * domain a:
     *
     * @link https://github.com
     */
    private String domain;
    /**
     * github_pat_xxxx
     */
    private String token;
}
