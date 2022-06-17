package com.wusl.web.admin;


import com.wusl.pojo.Type;
import com.wusl.service.TypeService;
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

    /*跳转分页*/
    @GetMapping("/types")
    public String typesList(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

    /*跳转*/
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /*保存*/
    @PostMapping("/types")
    public String save(@Valid Type type, BindingResult result, RedirectAttributes reab) {
        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null) {
            result.rejectValue("name", "nameError", "添加失败:该分类已存在，不能重复添加！");
            return "admin/types-input";
        }

        Type type1 = typeService.saveType(type);
        if (type1 == null) {
            reab.addFlashAttribute("message", "新增失败");
        } else {
            reab.addFlashAttribute("message", "新增成功");
        }

        return "redirect:/admin/types";
    }

    /*修改前获取*/
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    /*修改*/
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes reab) {


        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null) {
            result.rejectValue("name", "nameError", "添加失败:该分类已存在，不能重复添加！");
            return "admin/types-input";
        }

        if (result.hasErrors()) {
            return "admin/types-input";
        }

        Type type1 = typeService.updateType(id, type);
        if (type1 == null) {
            reab.addFlashAttribute("message", "更新失败");
        } else {
            reab.addFlashAttribute("message", "更新成功");
        }

        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes rab) {
        typeService.deleteType(id);
        rab.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
