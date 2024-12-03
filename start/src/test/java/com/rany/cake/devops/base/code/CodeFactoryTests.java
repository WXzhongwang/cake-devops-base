package com.rany.cake.devops.base.code;

import com.rany.cake.devops.base.BaseTests;
import com.rany.cake.devops.base.domain.aggregate.App;
import com.rany.cake.devops.base.domain.pk.AppId;
import com.rany.cake.devops.base.domain.service.AppDomainService;
import com.rany.cake.devops.base.domain.valueobject.AppExtend;
import com.rany.cake.devops.base.service.code.BaseCodeService;
import com.rany.cake.devops.base.service.code.CodeFactory;
import com.rany.cake.devops.base.util.enums.CodePlatformEnum;
import org.apache.dubbo.common.URL;
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
        // git@codeup.aliyun.com:66460922ced1b9e566e16df5/WXzhongwang/cake-devops-base.git

        // URL-Encoder 编码的全路径
        String repo = app.getCodeRepository().getRepo();
        String encodeURL = URL.encode("66460922ced1b9e566e16df5/WXzhongwang/cake-devops-base");
        Boolean branch = codeService.createBranch(encodeURL, "code-support", "main");
        Assert.assertTrue(branch);
    }
}