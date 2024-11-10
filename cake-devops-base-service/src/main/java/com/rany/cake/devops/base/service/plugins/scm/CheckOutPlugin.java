package com.rany.cake.devops.base.service.plugins.scm;

import com.rany.cake.devops.base.domain.aggregate.Release;
import com.rany.cake.devops.base.domain.valueobject.AppExtend;
import com.rany.cake.devops.base.domain.valueobject.CodeRepository;
import com.rany.cake.devops.base.service.code.BaseCodeService;
import com.rany.cake.devops.base.service.code.CodeFactory;
import com.rany.cake.devops.base.service.code.RedisSerialNumberGenerator;
import com.rany.cake.devops.base.service.code.RepoUrlUtils;
import com.rany.cake.devops.base.service.context.DeployContext;
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
                codeRepository.getConnectionString(),
                codeRepository.getToken(),
                appExtend);
        String[] repos = RepoUrlUtils.extractRepoInfo(codeRepository.getRepo());
        if (repos == null) {
            log.error("Current code repo extract error");
            return false;
        }
        String serialNum = redisSerialNumberGenerator.generateSerialNumber(appName);
        String newReleaseBranchName = RepoUrlUtils.generateReleaseBranchName(serialNum);
        String[] pair = RepoUrlUtils.extractRepoInfo(codeRepository.getRepo());
        Boolean success = codeService.createBranch(pair[0], pair[1], ref);
        if (BooleanUtils.isNotTrue(success)) {
            log.error("Create new branch failed, {}", newReleaseBranchName);
            return false;
        }
        context.setCheckoutBranch(newReleaseBranchName);
        return success;
    }
}
