package com.rany.cake.devops.base.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    
}
