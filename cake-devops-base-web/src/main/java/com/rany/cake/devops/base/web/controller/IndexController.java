package com.rany.cake.devops.base.web.controller;

import com.rany.cake.dingtalk.sdk.beans.SsoUser;
import com.rany.cake.dingtalk.sdk.configuration.SsoConstants;
import com.rany.cake.dingtalk.sdk.utils.SsoTokenLoginHelper;
import com.rany.cake.dingtalk.sdk.utils.SsoUtil;
import com.rany.cake.dingtalk.starter.annotation.CurrentUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/**/{path:[^\\.]*}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return "index";
    }

//    @RequestMapping(path = "/release")
//    public String release(@RequestParam(value = "id") String id,
//                          HttpServletRequest request, HttpServletResponse response
//            , ModelMap model) {
//        model.put("releaseId", id);
//        return "release";
//    }


}
