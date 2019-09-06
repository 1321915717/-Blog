package blog.blog.com.mapper;

import blog.blog.com.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface articleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);


    int updateByPrimaryKey(Article record);
    List<Article> selectAll();
    List<Article> selectAllAndContent(String content);
    List<Article> selectAllBYArticleStatus(Article article);
    int deleteArticle(Integer [] id);
     int selectCount(Article article);
     int selectCountNostate(Article article);


     List<Article> selectByViewCountAndTitle(Article article);
    List<Article> selectByCommentCountAndTitle(Article article);
    List<Article> selectByLikeCountAndTitle(Article article);
    List<Article> selectByGiveCountAndTitle(Article article);
    List<Article> selectArticlesByid(List<Integer> id);

    int   addCommentCount(Integer id);
    int reduceCommentCount(Integer id);

    int   addCommentLike(Integer id);
    int reduceCommentLike(Integer id);
    int   addCommentGive(Integer id);
    int reduceCommentGive(Integer id);
    int   addCommentView(Integer id);




}