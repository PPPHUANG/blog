package com.ppphuang.web.controller.admin;

import com.ppphuang.web.beans.Blog;
import com.ppphuang.web.beans.Type;
import com.ppphuang.web.beans.User;
import com.ppphuang.web.service.BlogService;
import com.ppphuang.web.service.TagService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    private BlogService blogService;
    private TypeService typeService;
    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

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
        Page<Blog> blogs = blogService.listBlog(pageable,blogQuery);
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        model.addAttribute("page",blogs);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogQuery, Model model) {
        Page<Blog> blogs = blogService.listBlog(pageable,blogQuery);
        model.addAttribute("page",blogs);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeTag(model);
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    void setTypeTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model){
        Blog blog = blogService.getBlog(id);
        setTypeTag(model);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes redirectAttributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        blog.setType(typeService.getType(blog.getType().getId()));
        Blog b;
        if (blog.getId() == null) {
            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == null) {
            redirectAttributes.addFlashAttribute("message", "新增失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
}
