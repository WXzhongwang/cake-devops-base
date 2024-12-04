package com.rany.cake.devops.base.service.code;

import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.domain.valueobject.AppExtend;
import com.rany.cake.devops.base.domain.valueobject.CodeUpConfig;
import com.rany.cake.devops.base.domain.valueobject.GitHubConfig;
import com.rany.cake.devops.base.domain.valueobject.GitLabConfig;
import com.rany.cake.devops.base.service.code.codeup.CodeUpCodeService;
import com.rany.cake.devops.base.service.code.github.GitHubCodeService;
import com.rany.cake.devops.base.service.code.gitlab.GitLabCodeService;
import com.rany.cake.devops.base.util.enums.CodePlatformEnum;
import org.springframework.stereotype.Component;

/**
 * CodeFactory
 *
 * @author zhongshengwang
 * @description 工厂
 * @date 2023/2/13 22:28
 * @email 18668485565163.com
 */
@Component
public class CodeFactory {
    public BaseCodeService build(CodePlatformEnum codePlatform,
                                 AppExtend appExtend) {
        if (codePlatform == CodePlatformEnum.GITLAB) {
            GitLabConfig gitlab = appExtend.getGitlab();
            return new GitLabCodeService(gitlab.getDomain(), gitlab.getToken());
        }
        if (codePlatform == CodePlatformEnum.GITHUB) {
            GitHubConfig git = appExtend.getGit();
            return new GitHubCodeService(git.getDomain(), git.getToken());
        }
        if (codePlatform == CodePlatformEnum.CODE_UP) {
            CodeUpConfig codeUp = appExtend.getCodeUp();
            return new CodeUpCodeService(codeUp.getOrganizationId(), codeUp.getHeaderToken(),
                    codeUp.getAccessKeyId(), codeUp.getAccessKeySecret());
        }
        throw new DevOpsException(DevOpsErrorMessage.OPS_CONNECTED_ERROR);
    }
}
