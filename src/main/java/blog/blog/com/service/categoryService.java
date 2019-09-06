package blog.blog.com.service;

import blog.blog.com.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface categoryService {
    List<Category> selectAll();
    int updateByPrimaryKeySelective(Category record);
    int insert(Category record);
    int deleteByPrimaryKey(Integer[] categoryId);
    Category selectByPrimaryKey(Integer categoryId);
}
