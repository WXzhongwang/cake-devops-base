package com.rany.cake.devops.base.acr;

import com.rany.cake.devops.base.BaseTests;
import com.rany.cake.devops.base.service.integration.acr.AliyunAcrApi;
import org.junit.Test;

import javax.annotation.Resource;

public class AliyunAcrApiTest extends BaseTests {

    @Resource
    private AliyunAcrApi aliyunAcrApi;

    @Test
    public void createRepo() {
        aliyunAcrApi.createRepo("ding-alert");
        aliyunAcrApi.createRepo("search-management");
        aliyunAcrApi.createRepo("cake-ops");
    }
}

