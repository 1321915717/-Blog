package blog.blog.com.service.serviceImpl;

import blog.blog.com.entity.*;
import blog.blog.com.mapper.*;
import blog.blog.com.service.ArticleService;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    private articleMapper articleMapper;
    @Autowired
    private Article_ref_tagMapper article_ref_tagMapper;
    @Autowired
    private Article_ref_categoryMapper article_ref_categoryMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserCollectionMapper userCollectionMapper;
    @Autowired
    private  UserDMapper userDMapper;

    @Autowired
    private  tagMapper tagMapper;

    @Override
    public List<Article>  selectAll(String keywords,Integer state) {

        List<Article> list =null;
        if(keywords.length()==0&&state==-1){
             list=  articleMapper.selectAll();


        }else  if(keywords.length()!=0&&state==-1){
            list= articleMapper.selectAllAndContent(keywords);
        }else if(state==1||state==0){

            Article article =new Article();
            article.setArticleStatus(state);
            article.setArticleContent(keywords);
            list=  articleMapper.selectAllBYArticleStatus(article);
        }
        List<ArticleAndCategory> articleAndCategories = new ArrayList<>();
        for (Article article : list) {


            Integer categoryId=article_ref_categoryMapper.selectCategoryByArticleId(article.getArticleId());

            if(categoryId!=null){  //就是怕拿不到categoryid 报空指针异常！！！
                Category category = categoryMapper.selectByPrimaryKey(categoryId);
               article.setCategory(category);
            }



        }


        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article selectByPrimaryKey(Integer articleId) {

        if(articleMapper.addCommentView(articleId)==0){

            throw  new RuntimeException();
        }

        List<Integer> tagid= article_ref_tagMapper.selectTagsByArticle(articleId);

        Article article= articleMapper.selectByPrimaryKey(articleId);
        if(tagid.size()>0){

            List<Tag> listTag= tagMapper.selectTagsByTagIds(tagid);
            article.setTagList(listTag);



        }






       Integer categoryid= article_ref_categoryMapper.selectCategoryByArticleId(articleId);



       article.setCategory( categoryMapper.selectByPrimaryKey(categoryid));



        return article ;


    }

    @Override
    public int updateByPrimaryKeySelective(Article record) {

        int code=articleMapper.updateByPrimaryKeySelective(record);

        if(code>0){
            return  1;

        }else {

            return -1;
        }


    }

    @Override
    public List<Article> selectAllAndContent(String content) {
        return articleMapper.selectAllAndContent(content);
    }

    @Override
    public List<Article> selectAllBYArticleStatus(Article article) {
        return  articleMapper.selectAllBYArticleStatus(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Integer[] id) {

        int codeTag=article_ref_tagMapper.deleteBYArticleId(id);
        int codeCategoory=article_ref_categoryMapper.deletesById(id);
        int codeArticle=articleMapper.deleteArticle(id);
 }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(Article article,Integer[] checkList,Integer categoryid){

        if (article.getArticleId()!=null) {

                articleMapper.updateByPrimaryKeySelective(article);
                article_ref_tagMapper.deleteBYArticleID(article.getArticleId());
                article_ref_categoryMapper.deleteById(article.getArticleId());

        }else {

            articleMapper.insert(article);




        }

        if (checkList.length>0) {
            List<Article_ref_tag> list = new ArrayList<>();
            for (int i = 0; i < checkList.length; i++) {
                Article_ref_tag article_ref_tag =new Article_ref_tag();
                article_ref_tag.setArticleId(article.getArticleId());
                article_ref_tag.setTagId(checkList[i]);
                list.add(article_ref_tag);
            }
            article_ref_tagMapper.insertArray(list);
         }

        Article_ref_category article_ref_category = new Article_ref_category();
        article_ref_category.setArticleId(article.getArticleId());
        article_ref_category.setCategoryId(categoryid);
        article_ref_categoryMapper.insert(article_ref_category);




    }

    @Override
    public List<Article> selectByCount(Article article, String findtype) {

        List<Article> list =null;



        if(findtype.equals("article_view_count")){
            System.out.println(findtype+"**************************");
            list=articleMapper.selectByViewCountAndTitle(article);
        }else if(findtype.equals("article_comment_count")){
            list=articleMapper.selectByCommentCountAndTitle(article);
        }else if(findtype.equals("article_give_count")){

                list=articleMapper.selectByGiveCountAndTitle(article);
        }else if(findtype.equals("article_like_count")){
            list=articleMapper.selectByLikeCountAndTitle(article);
        }


        return list;
    }

    @Override
    public List<Article> selectByTag(Integer tagId,Integer pageNum,Integer pageSize) {
        List<Integer> listArticleId=article_ref_tagMapper.selectArticlesByTag(tagId);

        PageHelper.startPage(pageNum,pageSize);
       List<Article>  list= articleMapper.selectArticlesByid(listArticleId);



        return list;
    }

    @Override
    public List<Article> selectByCollection(Integer id,Integer pageNum,Integer pageSize) {

        List<Integer> list =userCollectionMapper.selectByUserId(id);

        List<Article> listArticle= new ArrayList<>();
        if(list.size()>0) {
                PageHelper.startPage(pageNum,pageSize);
            listArticle = articleMapper.selectArticlesByid(list);
        }


        return listArticle;
    }

    @Override
    public List<Article> selectByD(Integer id,Integer pageNum,Integer pageSize) {


        List<Integer> list = userDMapper.selectByUserId(id);

        PageHelper.startPage(pageNum,pageSize);
        List<Article> articleList= new ArrayList<>();

        if(list.size()>0){


           articleList =articleMapper.selectArticlesByid(list);
       }



        return articleList;
    }

    @Override
    public List<Article> selectByoptins(Integer id, Integer pageNum, Integer pageSize) {

        System.out.println("进来了"+id);
        List<Integer> listid=article_ref_categoryMapper.selectArticleIdByOptions(id);
        List<Article> list = new ArrayList<>();
        if(listid.size()>0) {
            PageHelper.startPage(pageNum, pageSize);
             list = articleMapper.selectArticlesByid(listid);
        }
        return list;
    }


}
