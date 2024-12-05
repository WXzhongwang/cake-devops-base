package com.rany.cake.devops.base.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 *
 * @author zhongshengwang
 */
@Slf4j
@RestController
public class OkController {
    @RequestMapping(value = {"/ok"})
    public String index() {
        return "success";
    }
}
