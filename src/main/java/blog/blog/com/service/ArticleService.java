package blog.blog.com.service;

import blog.blog.com.entity.Article;
import blog.blog.com.entity.ArticleAndCategory;

import java.util.List;

public interface ArticleService {

    List<Article>  selectAll(String keywords, Integer state);
    Article selectByPrimaryKey(Integer articleId);
    int updateByPrimaryKeySelective(Article record);
    List<Article> selectAllAndContent(String content);
    List<Article> selectAllBYArticleStatus(Article article);
    void deleteArticle(Integer [] id);
     void insertArticle(Article article,Integer[] checkList,Integer categoryid);

     List<Article> selectByCount(Article article,String findtype);
     List<Article> selectByTag(Integer tagId,Integer pageNum,Integer pageSize);

     List<Article> selectByCollection(Integer id,Integer pageNum,Integer pageSize);
    List<Article> selectByD(Integer id,Integer pageNum,Integer pageSize);
    List<Article> selectByoptins(Integer id,Integer pageNum,Integer pageSize);




}
