package com.ppphuang.web.controller.admin;

import com.ppphuang.web.beans.Blog;
import com.ppphuang.web.beans.Type;
import com.ppphuang.web.service.BlogService;
import com.ppphuang.web.service.TypeService;
import com.ppphuang.web.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    private BlogService blogService;
    private TypeService typeService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogQuery, Model model){
//        Page<Blog> blogs = blogService.listBlog(pageable,blogQuery);
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
//        model.addAttribute("page",blogs);
        return "admin/blogs";
    }

    public String get(){
        return "admin/blog";
    }
}
