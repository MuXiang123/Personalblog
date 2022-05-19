package com.pb.personalblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @GetMapping("/")
    public String index() {
        return "foreground/index";
    }

    @GetMapping("/tags")
    public String tags() {
        return "foreground/tags";
    }

    @GetMapping("/archives")
    public String archives() {
        return "foreground/archives";
    }

    //    blog有问题
    @GetMapping("/blog")
    public String blog() {
        return "foreground/blog";
    }

    @GetMapping("/menu")
    public String menu() {
        return "foreground/menu";
    }
}
