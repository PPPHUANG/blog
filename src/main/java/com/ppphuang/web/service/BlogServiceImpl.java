package com.ppphuang.web.service;

import com.ppphuang.web.beans.Blog;
import com.ppphuang.web.dao.BlogRepository;
import com.ppphuang.web.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService{
    private BlogRepository blogRepository;

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog oBlog = getBlog(id);
        if (oBlog == null) {
            throw new NotFoundException("不存在该博文");
        }
        BeanUtils.copyProperties(blog, oBlog);
        return blogRepository.save(oBlog);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
