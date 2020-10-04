package com.ppphuang.web.service;

import com.ppphuang.web.beans.Blog;
import com.ppphuang.web.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog saveBlog(Blog blog);

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query, Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
