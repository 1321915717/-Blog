package blog.blog.com.controller.index;

import blog.blog.com.entity.Article;

import blog.blog.com.entity.Tag;
import blog.blog.com.service.ArticleService;
import blog.blog.com.service.tagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private tagService  tagService;


    @RequestMapping("/allArticle")
    public PageInfo<Article> index(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "5") Integer pageSize,
                                    String words
    ){

        PageHelper.startPage(pageNum, pageSize);

        Article article =new Article();
        article.setArticleStatus(1);
        if(null!=words &&!words.equals("")){

            article.setArticleTitle(words);


        }
        List<Article> list= articleService.selectAllBYArticleStatus(article);

        PageInfo<Article> pageInfo =new PageInfo<>(list);



        return pageInfo;
    }
    @RequestMapping("/allArticleByCount")
    public  PageInfo<Article> allArticleByCount(@RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                String words,
                                                String findtype
    ){

        PageHelper.startPage(pageNum, pageSize);
        Article article =new Article();
        article.setArticleStatus(1);
        if(null!=words&&!words.equals("")){
            article.setArticleTitle(words);
        }

        if(null!=words &&!words.equals("")){

            article.setArticleTitle(words);


        }

        List<Article> lists =articleService.selectByCount(article,findtype);

        PageInfo<Article>  list= new PageInfo<>(lists);




        return  list;
    }
        @RequestMapping("/loadingTag")
    public  List<Tag> loadingTag(){
        tagService.selectAll();

        return  tagService.selectAll();
    }

    @RequestMapping("/allArticleByTag")
    public  PageInfo<Article> allArticleByTag(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize,
            String words,
           Integer tagid
    ){


      List<Article> list=  articleService.selectByTag(tagid,pageNum,pageSize);

      PageInfo<Article>  pageInfo=new PageInfo<>(list);


        return  pageInfo;
    }

    @RequestMapping("/intoArticle")
    public  Article intoArticle(Integer id){





        return  articleService.selectByPrimaryKey(id);

    }


}
