package blog.blog.com.mapper;

import blog.blog.com.entity.Article_ref_category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Article_ref_categoryMapper {
    int insert(Article_ref_category record);

    int insertSelective(Article_ref_category record);
    Integer selectCategoryByArticleId(Integer id);
    int deleteById(Integer id);
    int deletesById(Integer[] id);
    List<Integer> selectArticleIdByOptions(Integer id);
}