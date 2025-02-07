package com.rany.cake.devops.base.api.service;

import com.rany.cake.devops.base.BaseTests;
import com.rany.cake.devops.base.api.command.release.DeployCommand;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/2/7 09:23
 * @slogon 找到银弹
 */
public class ReleaseServiceTests extends BaseTests {
    @Resource
    private ReleaseService releaseService;

    @Test
    public void deploy() {
        // cake-sso-server
        DeployCommand deployCommand = new DeployCommand();
        deployCommand.setReleaseId(982494913822339072L);
        deployCommand.setUser("768460662077796352");
        Boolean deploy = releaseService.deploy(deployCommand);
        Assert.assertTrue(deploy);
    }

    @Test
    public void deploy2() {
        // cake-ops
        DeployCommand deployCommand = new DeployCommand();
        deployCommand.setReleaseId(982154827033358336L);
        deployCommand.setUser("768460662077796352");
        Boolean deploy = releaseService.deploy(deployCommand);
        Assert.assertTrue(deploy);
    }
}
