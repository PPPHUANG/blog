package com.ppphuang.web.dao;

import com.ppphuang.web.beans.Blog;
import com.ppphuang.web.beans.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
}
