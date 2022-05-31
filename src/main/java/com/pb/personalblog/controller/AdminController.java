package com.pb.personalblog.controller;

import com.pb.personalblog.service.CommonService;
import com.pb.personalblog.vo.Satistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author zhk
 * @date 2022/5/16 16:58
 * 后台管理功能controller
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CommonService commonService;

    /**
     * 管理页面主页
     *
     * @return
     */
    @GetMapping("/home")
    public String test1(Model model) {
        model.addAttribute("btn", new Satistics(commonService.getArticle(), commonService.getVisit(), commonService.getComment()));
        return "admin/home";
    }

    /**
     * 博客管理
     *
     * @return
     */
    @RequestMapping("/blogs")
    public String test2() {
        return "admin/blogs";
    }

    /**
     * 发布博客
     *
     * @return
     */
    @RequestMapping("/input")
    public String test3() {
        return "admin/blogsInput";
    }


}
