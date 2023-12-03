package com.rany.cake.devops.base.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试
 */
@Controller
@RequestMapping("/index")
public class IndexController {


    @RequestMapping(path = "/release")
    public String release(@RequestParam(value = "id") String id,
                          HttpServletRequest request, HttpServletResponse response
            , ModelMap model) {
        model.put("releaseId", id);
        return "release";
    }


}
