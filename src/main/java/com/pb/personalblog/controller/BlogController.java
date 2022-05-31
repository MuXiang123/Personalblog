package com.pb.personalblog.controller;

import com.pb.personalblog.pojo.Blog;
import com.pb.personalblog.pojo.User;
import com.pb.personalblog.service.CommonService;
import com.pb.personalblog.vo.Satistics;
import com.pb.personalblog.service.BlogService;
import com.pb.personalblog.service.TagService;
import com.pb.personalblog.service.TypeService;
import com.pb.personalblog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author xys
 * @date 2022/5/27 17:20
 * 后台管理页面controller
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private CommonService commonService;

    private static final String INPUT = "admin/blogsInput";
    private static final String LIST = "admin/blogs";
    /**
     * 重定向页面
     */
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * @param pageable
     * @param blog
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model) {
        //传递所有分类到前端
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        model.addAttribute("btn", new Satistics(commonService.getArticle(), commonService.getVisit(), commonService.getComment()));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        //返回一个片段，只改变该页面的表格内容，不改变其他
        return "admin/blogs :: blogList";

    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        model.addAttribute("btn", new Satistics(commonService.getArticle(), commonService.getVisit(), commonService.getComment()));
        return INPUT;
    }

    private void setTypeAndTag(Model model) {
        //传递所有分类到前端
        model.addAttribute("types", typeService.listType());
        //传递所有标签到前端
        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        //获取前台传的id
        model.addAttribute("blog", blog);
        model.addAttribute("btn", new Satistics(commonService.getArticle(), commonService.getVisit(), commonService.getComment()));
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        //拿到前台选择的分类id进行保存
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        //简单保存
        Blog b;
        if (blog.getId() == null) {
            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }
}
