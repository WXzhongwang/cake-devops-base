package com.rany.cake.devops.base.service.plugins.scm;

import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.valueobject.AppExtend;
import com.rany.cake.devops.base.domain.valueobject.CodeRepository;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.integration.code.BaseCodeService;
import com.rany.cake.devops.base.service.integration.code.CodeFactory;
import com.rany.cake.devops.base.service.integration.code.RedisSerialNumberGenerator;
import com.rany.cake.devops.base.service.integration.code.RepoUrlUtils;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import com.rany.cake.devops.base.service.plugins.annotation.PluginName;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 代码分支
 *
 * @author zhongshengwang
 * @description 代码分支
 * @date 2023/1/20 20:30
 * @email 18668485565163.com
 */
@Component
@PluginName("代码分支检出")
public class CheckOutPlugin extends BasePlugin {
    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Resource
    private CodeFactory codeFactory;
    @Resource
    private RedisSerialNumberGenerator redisSerialNumberGenerator;

    @Override
    public boolean execute(DeployContext context) {
        CodeRepository codeRepository = context.getApp().getCodeRepository();
        String appName = context.getApp().getAppName().getName();
        AppExtend appExtend = context.getApp().getAppExtend();
        Release release = context.getRelease();
        String ref = StringUtils.isNotEmpty(release.getReleaseBranch()) ? release.getReleaseBranch() : release.getReleaseCommitId();
        log.info("Current code repo:{}, default branch:{}", codeRepository.getRepo(), codeRepository.getDefaultBranch());
        BaseCodeService codeService = codeFactory.build(codeRepository.of(),
                appExtend);
        String serialNum = redisSerialNumberGenerator.generateSerialNumber(appName);
        String newReleaseBranchName = RepoUrlUtils.generateReleaseBranchName(serialNum);
        Boolean success = codeService.createBranch(codeRepository.getRepo(), newReleaseBranchName, ref);
        if (BooleanUtils.isNotTrue(success)) {
            log.error("Create new branch failed, {}", newReleaseBranchName);
            return false;
        }
        context.setCheckoutBranch(newReleaseBranchName);
        return success;
    }
}
