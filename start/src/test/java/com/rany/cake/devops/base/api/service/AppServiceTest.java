package com.rany.cake.devops.base.api.service;

import com.google.common.collect.Lists;
import com.rany.cake.devops.base.BaseTests;
import com.rany.cake.devops.base.api.command.app.CreateAppCommand;
import com.rany.cake.devops.base.api.command.app.CreateAppEnvCommand;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import com.rany.cake.devops.base.api.dto.AppMemberDTO;
import com.rany.cake.devops.base.domain.base.AppConfig;
import com.rany.cake.devops.base.util.enums.AppRoleEnum;
import com.rany.cake.devops.base.util.enums.CodeLanguageEnum;
import com.rany.cake.devops.base.util.enums.DevelopMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * TODO
 *
 * @author zhongshengwang
 * @description TODO
 * @date 2023/2/3 21:54
 * @email 18668485565163.com
 */
public class AppServiceTest extends BaseTests {

    @Resource
    private AppService appService;

    @Resource
    private AppConfig appConfig;

    public static final String ACCOUNT_ID = "781488231601549312L";


    @Test
    public void createApp() {
        CreateAppCommand createAppCommand = new CreateAppCommand();
        createAppCommand.setAppName("cake-devops-base");
        createAppCommand.setDescription("测试应用001");
        createAppCommand.setRepo("https://github.com/WXzhongwang/cake-devops-base.git");
        createAppCommand.setDefaultBranch("origin/main");
        createAppCommand.setLanguage(CodeLanguageEnum.JAVA.name());
        createAppCommand.setDevelopMode(DevelopMode.FREEDOM.name());
        createAppCommand.setOwner("768821468053254144");
        createAppCommand.setDepartmentAbbreviation("Honda");
        createAppCommand.setDepartment("Honda汽车");
        ArrayList<AppMemberDTO> appMembers = new ArrayList<>();
        AppMemberDTO memberDTO = new AppMemberDTO();
        memberDTO.setAccountId(ACCOUNT_ID);
        memberDTO.setRoles(Lists.newArrayList(AppRoleEnum.DEVELOPER.name()));
        appMembers.add(memberDTO);
        createAppCommand.setAppMembers(appMembers);
        // createAppCommand.setAppEnvs(Lists.newArrayList());
        createAppCommand.setHealthCheck("/ok");
        String app = appService.createApp(createAppCommand);
        Assertions.assertFalse(app.isEmpty());
    }

    public void createAppEnv() {
        CreateAppEnvCommand createAppEnvCommand = new CreateAppEnvCommand();
        createAppEnvCommand.setAppId("781513981771788288");
        AppEnvDTO env = new AppEnvDTO();
        createAppEnvCommand.setEnv(env);
        appService.createAppEnv(createAppEnvCommand);
    }
}