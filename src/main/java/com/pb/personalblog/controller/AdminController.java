package com.pb.personalblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author zhk
 * @date 2022/5/16 16:58
 * 后台管理功能controller
 */
@Controller
public class AdminController {
    /**
     * 管理页面主页
     *
     * @return
     */
    @RequestMapping("/admin/home")
    public String test1() {
        return "admin/home";
    }

    /**
     * 博客管理
     *
     * @return
     */
    @RequestMapping("/admin/blogs")
    public String test2() {
        return "admin/blogs";
    }

    /**
     * 发布博客
     *
     * @return
     */
    @RequestMapping("/admin/input")
    public String test3() {
        return "admin/blogsInput";
    }
}
