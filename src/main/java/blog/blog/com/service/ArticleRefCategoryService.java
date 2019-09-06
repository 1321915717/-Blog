package blog.blog.com.service;

import blog.blog.com.entity.Article_ref_category;

import java.util.List;

public interface ArticleRefCategoryService {

    Integer selectCategoryByArticleId(Integer id);
    int insert(Article_ref_category record);
    int deleteById(Integer id);
    int deletesById(Integer[] id);
}
