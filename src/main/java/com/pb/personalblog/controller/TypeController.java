package com.pb.personalblog.controller;

import com.pb.personalblog.pojo.Type;
import com.pb.personalblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public Object types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {//分页处理，设定根据id倒序排列
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
//查询到的page会有数据列表，是否末页，总页数，总条数，一页几条，当前第几页，是否第一页，排序方式，当前页的数据占总页几条

    }

    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {// 后端校验消息要传到前台要加@Valid表示要校验type对象
        Type type1 = typeService.getTypeByName(type.getName());//寻找同名分类
        if (type1 != null) {
            result.rejectValue("name", "nameError", "该分类已存在");
        }

        //BindingResult result接收校验结果
        if (result.hasErrors()) {
            //接受结果有错误返回页面
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "新增失败");
            //如果保存不成功提示
        } else {
            attributes.addFlashAttribute("message", "新增成功");
            //成功提示
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {// 后端校验消息要传到前台要加@Valid表示要校验type对象
        Type type1 = typeService.getTypeByName(type.getName());//寻找同名分类
        if (type1 != null) {
            result.rejectValue("name", "nameError", "该分类已存在");
        }

        //BindingResult result接收校验结果
        if (result.hasErrors()) {
            //接受结果有错误返回页面
            return "admin/types-input";
        }
        Type t = typeService.updateType(id, type);
        if (t == null) {
            attributes.addFlashAttribute("message", "更新失败");
            //如果保存不成功提示
        } else {
            attributes.addFlashAttribute("message", "更新成功");
            //成功提示
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";

    }
}
