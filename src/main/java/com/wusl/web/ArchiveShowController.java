package com.wusl.web;


import com.wusl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;


    @GetMapping("/archives")
    public String archiveShow(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("countBlog", blogService.countBlog());
        return "archives";


    }

}
