package blog.blog.com.controller.index;


import blog.blog.com.entity.Comment;
import blog.blog.com.entity.UserCollection;
import blog.blog.com.entity.UserD;
import blog.blog.com.entity.WechatUser;
import blog.blog.com.service.CommentService;
import blog.blog.com.service.UserCollectionService;
import blog.blog.com.service.UserDService;
import blog.blog.com.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index/article")
public class IndexArticleController {


    @Autowired
    private CommentService commentService ;
    @Autowired
    private WechatUserService wechatUserService;

    @Autowired
    private UserCollectionService userCollectionService;

    @Autowired
    private UserDService userDService;

    @RequestMapping("/loadComment")
    public List<Comment>  loadComment(Integer id){


        return  commentService.selectCommentAndUserName(id);
    }

    @RequestMapping("/addComment")
    public Map<String,Object> addComent(Integer articleId,String session_key ,String commentContent){

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Date date = new Date();



         WechatUser wechatUser= wechatUserService.selectUserBySession(session_key);

         Map<String,Object> map =new HashMap<>();
         if(null!=wechatUser&&!wechatUser.equals("")){

             Comment comment =new Comment();
             comment.setCommentArticleId(articleId);
             comment.setCommentContent(commentContent);
             comment.setCommentUid(wechatUser.getUserId());
             comment.setCommentUid(wechatUser.getUserId());
             comment.setCommentTime(simpleDateFormat.format(date));

            int code= commentService.addComment(comment);
            if(code>0){

                map.put("msg","评论成功");
            }else{
                map.put("msg","评论失败");
            }


         }else {
             map.put("msg","评论失败");

         }





        return  map;
    }

    @RequestMapping("/deleteComment")
    public  Map<String,Object> deleteComment (Integer articleId,String session_key,Integer commentid){
        WechatUser wechatUser= wechatUserService.selectUserBySession(session_key);

        Map<String,Object> map =new HashMap<>();

      if(null!=wechatUser&&null!=commentid){

          Comment comment =new Comment();
          comment.setCommentArticleId(articleId);
         comment.setCommentId(commentid);
          comment.setCommentUid(wechatUser.getUserId());

          int code=commentService.deleteComment(comment);

          if(code>0){
              map.put("msg","删除成功");

          }else {
              map.put("msg","删除失败");

          }

      }else {
          map.put("msg","删除失败");
      }



        return  map;
    }

    @RequestMapping("/addLike")
    public  Map<String,Object> addLike(Integer articleId,String session_key){
        Map<String,Object> map =new HashMap<>();
        WechatUser wechatUser= wechatUserService.selectUserBySession(session_key);

        if(null!=wechatUser){
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

            Date date =new Date();
            UserCollection userCollection =new UserCollection();

            userCollection.setUcArticleId(articleId);
            userCollection.setUcCreateTime(simpleDateFormat.format(date));
            userCollection.setUcUserId(wechatUser.getUserId());
          int code=  userCollectionService.addLike(userCollection);

          if(code==2){
              map.put("msg","你已经收藏过了");
          }else if (code==1){
              map.put("msg","收藏成功");
          }else{

              map.put("msg","收藏失败");
          }

        }else {

            map.put("msg","收藏失败");
        }


        return  map;
    }
    @RequestMapping("/addGive")
    public  Map<String,Object> addGive(Integer articleId,String session_key){
        Map<String,Object> map =new HashMap<>();
        WechatUser wechatUser= wechatUserService.selectUserBySession(session_key);

        if(null!=wechatUser){
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

            Date date =new Date();

            UserD userD =new UserD();

            userD.setUdCreateTime(simpleDateFormat.format(date));

           userD.setUdArticleId(articleId);
            userD.setUdUserId(wechatUser.getUserId());

                int code=userDService.addGive(userD);

          if(code==1){

              map.put("msg","点赞成功");

          }else if(code==-1){
              map.put("msg","点赞失败");
          }else if(code==-2){

              map.put("msg","你已经点赞过了");
          }


        }else{
            map.put("msg","点赞失败");

        }


        return  map;
    }


}
