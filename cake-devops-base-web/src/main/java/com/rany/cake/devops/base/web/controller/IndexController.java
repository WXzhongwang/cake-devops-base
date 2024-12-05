package com.rany.cake.devops.base.web.controller;

import com.cake.cms.client.CmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 页面资源托管
 *
 * @author zhongshengwang
 */
@Slf4j
@RestController
public class IndexController {

    @Value("${cms.page.path}")
    private String cmsPagePath;

    @Resource
    private CmsClient cmsClient;

    @RequestMapping(value = {"/", "/apps", "/apps/**"}, produces = "text/html;charset=utf-8", method = RequestMethod.GET)
    public String index() {
        try {
            log.info("CMS load page from {}.", cmsPagePath);
            return cmsClient.load(cmsPagePath);
        } catch (Exception e) {
            log.error("[CMS] 拉取页面,出现异常,", e);
            return null;
        }
    }
}
