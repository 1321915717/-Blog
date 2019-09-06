package blog.blog.com.controller.index;


import blog.blog.com.entity.Article;
import blog.blog.com.entity.Category;
import blog.blog.com.service.ArticleService;
import blog.blog.com.service.categoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index/option")
public class IndexOptionController {

    @Autowired
    private categoryService categoryService;
    @Autowired
    private ArticleService  articleService;


    @RequestMapping("/loadOptions")
    public List<Category> loadOptions(){




        return  categoryService.selectAll();
    }
    @RequestMapping("/intoOptions")
    public PageInfo<Article> intoOptions(  @RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "5") Integer pageSize,
                                           Integer optionId
                                           ){
        List<Article> list=  articleService.selectByoptins(optionId,pageNum,pageSize);
            PageInfo<Article> pageInfo =new PageInfo<>(list);
        return  pageInfo;
    }




}
