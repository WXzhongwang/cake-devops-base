package com.rany.cake.devops.base.service.plugins.scm;

import com.rany.cake.devops.base.domain.enums.CodePlatformEnum;
import com.rany.cake.devops.base.domain.valueobject.CodeRepository;
import com.rany.cake.devops.base.service.code.BaseCodeService;
import com.rany.cake.devops.base.service.code.CodeFactory;
import com.rany.cake.devops.base.service.context.DeployContext;
import com.rany.cake.devops.base.service.plugins.BasePlugin;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 代码检出
 *
 * @author zhongshengwang
 * @description 打包机器选择
 * @date 2023/1/20 20:30
 * @email 18668485565163.com
 */
@Component
public class CheckOutPlugin extends BasePlugin {
    @Override
    public boolean init(DeployContext context) {
        return true;
    }

    @Resource
    private CodeFactory codeFactory;

    @Override
    public boolean execute(DeployContext context) {
        CodeRepository app = context.getApp().getCodeRepository();
        BaseCodeService codeService = codeFactory.build(CodePlatformEnum.GITLAB, "127.0.0.0:12345", "tokexxxx");
        return true;
    }
}
