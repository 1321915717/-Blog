package blog.blog.com.service.serviceImpl;

import blog.blog.com.entity.Category;
import blog.blog.com.mapper.CategoryMapper;
import blog.blog.com.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryServiceImpl implements categoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAll() {
        List<Category> list = categoryMapper.selectAll();

        return  list;



    }

    @Override
    public int updateByPrimaryKeySelective(Category record) {
            int code=categoryMapper.updateByPrimaryKeySelective(record);

        return code>0?code:-1;
    }

    @Override
    public int insert(Category record) {
       int  code =categoryMapper.insert(record);

       if(code>0){

           return  1;
       }else{
           return -1;
       }


    }

    @Override
    public int deleteByPrimaryKey(Integer[] categoryId) {
        int  code =categoryMapper.deleteByPrimaryKey(categoryId);

        if(code>0){

            return  1;
        }else{
            return -1;
        }
    }

    @Override
    public Category selectByPrimaryKey(Integer categoryId) {
        return  categoryMapper.selectByPrimaryKey(categoryId);
    }
}
