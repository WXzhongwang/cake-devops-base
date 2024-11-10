package com.rany.cake.devops.base.service.code;

import com.rany.cake.devops.base.api.exception.DevOpsErrorMessage;
import com.rany.cake.devops.base.api.exception.DevOpsException;
import com.rany.cake.devops.base.domain.valueobject.AppExtend;
import com.rany.cake.devops.base.domain.valueobject.CodeUpConfig;
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
    public BaseCodeService build(CodePlatformEnum codePlatform, String connectString, String token,
                                 AppExtend appExtend) {
        if (codePlatform == CodePlatformEnum.GITLAB) {
            return new GitLabCodeService(connectString, token);
        }
        if (codePlatform == CodePlatformEnum.GITHUB) {
            return new GitHubCodeService(connectString, token);
        }
        if (codePlatform == CodePlatformEnum.CODE_UP) {
            CodeUpConfig codeUp = appExtend.getCodeUp();
            return new CodeUpCodeService(codeUp.getDomain(), codeUp.getOrganizationId(), codeUp.getHeaderToken());
        }
        throw new DevOpsException(DevOpsErrorMessage.OPS_CONNECTED_ERROR);
    }
}
