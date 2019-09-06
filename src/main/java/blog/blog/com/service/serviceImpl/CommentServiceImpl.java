package blog.blog.com.service.serviceImpl;

import blog.blog.com.entity.Comment;
import blog.blog.com.mapper.articleMapper;
import blog.blog.com.mapper.commentMapper;
import blog.blog.com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private commentMapper commentMapper;

    @Autowired
    private articleMapper articleMapper;
    @Override
    public List<Comment> selectAll() {
        return commentMapper.selectAll();
    }

    @Override
    public int deleteByCommentId(Integer[] id) {
        return commentMapper.deleteByCommentId(id);

    }

    @Override
    public List<Comment> selectCommentAndUserName(Integer id) {


        return  commentMapper.selectCommentAndUserName(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addComment(Comment comment) {

       try{
          int code=  commentMapper.insert(comment);
          if(code==0){
              throw  new RuntimeException();
          }

          articleMapper.addCommentCount(comment.getCommentArticleId());
            return  1;


       }catch ( Exception e){

           throw  new RuntimeException(e);

       }






    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteComment(Comment comment) {


        try{
            articleMapper.reduceCommentCount(comment.getCommentArticleId());
         int code=   commentMapper.deleteByCommentIds(comment.getCommentId());

         if(code==0){
             throw  new RuntimeException("没找到评论的id");

         }

            return  1;


        }catch ( Exception e){

            throw  new RuntimeException(e);


        }
    }


}
