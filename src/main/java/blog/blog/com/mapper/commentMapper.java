package blog.blog.com.mapper;

import blog.blog.com.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface commentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAll();
    int deleteByCommentId(Integer [] id);
    int deleteByCommentIds(Integer  id);
    List<Comment> selectCommentAndUserName(Integer id);
}