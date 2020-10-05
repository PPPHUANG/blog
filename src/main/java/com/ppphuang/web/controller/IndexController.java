package com.ppphuang.web.controller;

import com.ppphuang.web.beans.Blog;
import com.ppphuang.web.service.BlogService;
import com.ppphuang.web.service.TagService;
import com.ppphuang.web.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    private TagService tagService;
    private TypeService typeService;
    private BlogService blogService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }
    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String index(@PageableDefault(size=8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("tags",tagService.listTag(6));
        model.addAttribute("types",typeService.listType(10));
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(10));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size=8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,@RequestParam String query, Model model){
        Page<Blog> blogs = blogService.listBlog("%" + query + "%", pageable);
        model.addAttribute("page",blogs);
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String Blog(@PathVariable Long id,Model model) {
        model.addAttribute("blog",blogService.getAndConvert(id));
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }

    @GetMapping("/test")
    public String test() {
        int a = 10/0;
        return "blog";
    }
}
