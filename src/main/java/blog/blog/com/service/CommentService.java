package blog.blog.com.service;

import blog.blog.com.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> selectAll();
    int deleteByCommentId(Integer [] id);
    List<Comment> selectCommentAndUserName(Integer id);

    int  addComment(Comment comment);
    int deleteComment(Comment comment);
}
