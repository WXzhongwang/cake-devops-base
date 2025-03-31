package com.rany.cake.devops.base.web.controller;

import com.alibaba.fastjson.JSON;
import com.cake.framework.common.response.PojoResult;
import com.rany.cake.devops.base.api.dto.AppDTO;
import com.rany.cake.devops.base.api.dto.AppEnvDTO;
import com.rany.cake.devops.base.api.query.app.AppNameBasicQuery;
import com.rany.cake.devops.base.api.service.AppService;
import com.rany.framework.config.utils.XorUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 配置类, SDK底层获取应用configMap、SecretMap、EnvVars 信息使用
 *
 * @author zhongshengwang
 * @version 1.0
 * @date 2025/3/31 16:37
 * @slogon 找到银弹
 */
@RestController
public class OpenConfigController {

    @Resource
    private AppService appService;

    /**
     * /api/config/secret/items?env=TEST&appName=
     *
     * @param appName appName
     * @param env     环境名称
     * @return 返回配置
     */
    @GetMapping("/api/config/secret/items")
    public String getSecretConfig(@RequestParam("appName") String appName, @RequestParam("env") String env) {
        AppDTO app = appService.getAppByName(new AppNameBasicQuery(appName));
        PojoResult<Map<String, String>> result = PojoResult.succeed();
        if (app != null) {
            List<AppEnvDTO> appEnvList = app.getAppEnvList();
            for (AppEnvDTO appEnv : appEnvList) {
                if (appEnv.getEnv().equals(env)) {
                    result.setContent(appEnv.getSecretMap());
                    break;
                }
            }
        }
        return XorUtil.decrypt(JSON.toJSONString(result));
    }


    /**
     * /api/config/items?env=TEST&appName=
     *
     * @param appName appName
     * @param env     环境名称
     * @return 返回配置
     */
    @GetMapping("/api/config/items")
    public String getConfig(@RequestParam("appName") String appName, @RequestParam("env") String env) {
        AppDTO app = appService.getAppByName(new AppNameBasicQuery(appName));
        PojoResult<Map<String, String>> result = PojoResult.succeed();
        if (app != null) {
            List<AppEnvDTO> appEnvList = app.getAppEnvList();
            for (AppEnvDTO appEnv : appEnvList) {
                if (appEnv.getEnv().equals(env)) {
                    result.setContent(appEnv.getConfigMap());
                    break;
                }
            }
        }
        return XorUtil.decrypt(JSON.toJSONString(result));
    }

    /**
     * /api/config/vars/items?env=TEST&appName=
     *
     * @param appName appName
     * @param env     环境名称
     * @return 返回配置
     */
    @GetMapping("/api/config/vars/items")
    public String getVarsConfig(@RequestParam("appName") String appName, @RequestParam("env") String env) {
        AppDTO app = appService.getAppByName(new AppNameBasicQuery(appName));
        PojoResult<Map<String, String>> result = PojoResult.succeed();
        if (app != null) {
            List<AppEnvDTO> appEnvList = app.getAppEnvList();
            for (AppEnvDTO appEnv : appEnvList) {
                if (appEnv.getEnv().equals(env)) {
                    result.setContent(appEnv.getEnvVars());
                    break;
                }
            }
        }
        return XorUtil.decrypt(JSON.toJSONString(result));
    }
}
