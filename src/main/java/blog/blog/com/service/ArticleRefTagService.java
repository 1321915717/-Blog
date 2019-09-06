package blog.blog.com.service;

import blog.blog.com.entity.Article_ref_tag;

import java.util.List;

public interface ArticleRefTagService {

    List<Integer> selectTagsByArticle(Integer id);
    int  insertArray(List<Article_ref_tag> article_ref_tags);
    int deleteBYArticleID(Integer id);
    int deleteBYArticleId(Integer[] id);
}
