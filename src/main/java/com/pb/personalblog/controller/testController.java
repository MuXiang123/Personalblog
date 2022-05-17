package com.pb.personalblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author zhk
 * @date 2022/5/16 16:58
 * 测试用
 */
@Controller
public class testController {

    @RequestMapping("/admin")
    public String test1() {
        return "admin/home";
    }

    @RequestMapping("/admin/blogs")
    public String test2(){
        return "admin/blogs";
    }

    @RequestMapping("/admin/test")
    public String test3(){
        return "admin/test";
    }
}
