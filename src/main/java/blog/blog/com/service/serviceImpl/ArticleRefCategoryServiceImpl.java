package blog.blog.com.service.serviceImpl;


import blog.blog.com.entity.Article_ref_category;
import blog.blog.com.mapper.Article_ref_categoryMapper;
import blog.blog.com.service.ArticleRefCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleRefCategoryServiceImpl implements ArticleRefCategoryService {

    @Autowired
    private Article_ref_categoryMapper article_ref_categoryMapper;

    @Override
    public Integer selectCategoryByArticleId(Integer id) {



        return article_ref_categoryMapper.selectCategoryByArticleId(id);
    }

    @Override
    public  int insert(Article_ref_category record){
       int code= article_ref_categoryMapper.insert(record);

        return  code;
    }
    @Override
    public   int deleteById(Integer id){

       return   article_ref_categoryMapper.deleteById(id);

    }
    @Override
    public  int deletesById(Integer[] id){

        int code=article_ref_categoryMapper.deletesById(id);

        if(code>0){
            return 1;
        }else {
            return -1;
        }

    }
}
