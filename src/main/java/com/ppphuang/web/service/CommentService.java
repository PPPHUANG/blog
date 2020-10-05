package com.ppphuang.web.service;

import com.ppphuang.web.beans.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);
}
