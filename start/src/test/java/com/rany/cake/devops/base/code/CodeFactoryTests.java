package com.rany.cake.devops.base.code;

import com.rany.cake.devops.base.BaseTests;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.service.AppDomainService;
import com.rany.cake.devops.base.domain.valueobject.AppExtend;
import com.rany.cake.devops.base.service.code.BaseCodeService;
import com.rany.cake.devops.base.service.code.CodeFactory;
import com.rany.cake.devops.base.util.enums.CodePlatformEnum;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class CodeFactoryTests extends BaseTests {

    @Resource
    private CodeFactory codeFactory;
    @Resource
    private AppDomainService appDomainService;

    @Test
    public void test() {
        App app = appDomainService.getApp(new AppId("979992554168791040"));
        AppExtend appExtend = app.getAppExtend();
        BaseCodeService codeService = codeFactory.build(CodePlatformEnum.CODE_UP, "", "", appExtend);
        Boolean branch = codeService.createBranch("cake-devops-base", "feature/code-support", "main");
        Assert.assertTrue(branch);
    }
}
