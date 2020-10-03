package com.ppphuang.web.service;

import com.ppphuang.web.beans.Blog;
import com.ppphuang.web.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Blog saveBlog(Blog blog);

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
