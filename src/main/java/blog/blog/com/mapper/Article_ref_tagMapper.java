package blog.blog.com.mapper;

import blog.blog.com.entity.Article_ref_tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Article_ref_tagMapper {
    int insert(Article_ref_tag record);

    int insertSelective(Article_ref_tag record);
  List<Integer> selectTagsByArticle(Integer id);
    int  insertArray(List<Article_ref_tag> article_ref_tags);
    int deleteBYArticleID(Integer id);
     int deleteBYArticleId(Integer[] id);
    List<Integer>  selectArticlesByTag(Integer id);
}