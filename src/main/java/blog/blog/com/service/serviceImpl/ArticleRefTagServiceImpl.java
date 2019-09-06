package blog.blog.com.service.serviceImpl;

import blog.blog.com.entity.Article_ref_tag;
import blog.blog.com.mapper.Article_ref_tagMapper;
import blog.blog.com.service.ArticleRefTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleRefTagServiceImpl implements ArticleRefTagService {

    @Autowired
    private Article_ref_tagMapper article_ref_tagMapper;

    @Override
    public List<Integer> selectTagsByArticle(Integer id) {
        return article_ref_tagMapper.selectTagsByArticle(id);
    }

    @Override
    public int insertArray(List<Article_ref_tag> article_ref_tags) {
         int code= article_ref_tagMapper.insertArray(article_ref_tags);
            if(code>0){

                return 1;
            }else {
                return -1;
            }

    }

    @Override
    public int deleteBYArticleID(Integer id) {
        int code=article_ref_tagMapper.deleteBYArticleID(id);
        if(code>0){
            return 0;
        }else {
            return -1;
        }
    }
    @Override
    public  int deleteBYArticleId(Integer []id){
        int code=article_ref_tagMapper.deleteBYArticleId(id);

        if(code>0){
            return 1;
        }else {
            return  -1;
        }

    }
}
