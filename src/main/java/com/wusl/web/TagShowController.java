package com.wusl.web;


import com.wusl.pojo.BlogQuery;
import com.wusl.pojo.Tag;
import com.wusl.service.BlogService;
import com.wusl.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {


    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;


    /*获取tag页面显示内容*/
    @GetMapping("/tags/{id}")
    public String gettags(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                  Pageable pageable, Model model, @PathVariable Long id) {

        List<Tag> tags = tagService.lisTagTop(10000);
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }


}
