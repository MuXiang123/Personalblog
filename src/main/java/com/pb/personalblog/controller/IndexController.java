package com.pb.personalblog.controller;


import com.pb.personalblog.service.BlogService;
import com.pb.personalblog.service.TagService;
import com.pb.personalblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @GetMapping("/")
    public String index(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(6));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        return "foreground/index";
    }


    @PostMapping("/search")
    public String search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {

        return "search";
    }

//    @GetMapping("/blog/{id}")
//    //    通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“)
//    public String blog(@PathVariable Long id, Model model) {
//        model.addAttribute("blog", blogService.getAndConvert(id));
//        return "blog";
//    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {

        return "_fragments :: newblogList";
    }

}
