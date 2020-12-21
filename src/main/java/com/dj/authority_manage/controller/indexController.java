package com.dj.authority_manage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dj
 * @date 2020-12-21 16:49
 */

@RestController
public class indexController {

    @RequestMapping("/index")
    public String sayHello(){
        return "index";
    }

}
