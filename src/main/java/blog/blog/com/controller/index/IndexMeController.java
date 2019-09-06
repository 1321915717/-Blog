package blog.blog.com.controller.index;

import blog.blog.com.entity.Article;
import blog.blog.com.entity.WechatUser;
import blog.blog.com.service.ArticleService;
import blog.blog.com.service.WechatUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/index/me")
@RestController
public class IndexMeController {


    @Autowired
    private ArticleService articleService;
    @Autowired
     private WechatUserService wechatUserService;

    @RequestMapping("/loadingCollectionArticle")
    public PageInfo<Article> CollectionArticle(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize,
                                      String session_key){




         WechatUser wechatUser= wechatUserService.selectUserBySession(session_key);


        List<Article> list = articleService.selectByCollection(wechatUser.getUserId(),pageNum,pageSize);
        System.out.println(pageSize);
        System.out.println(list.size()+"-------------");

        PageInfo<Article> pageInfo =new PageInfo<>(list);


            System.out.println(pageInfo.getPageSize());


        return pageInfo;
    }

    @RequestMapping("/loadingDArticle")
    public PageInfo<Article>  loadingDArticle(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "5") Integer pageSize,
                                              String session_key){





        WechatUser wechatUser= wechatUserService.selectUserBySession(session_key);
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.selectByD(wechatUser.getUserId(),pageNum,pageSize);


        PageInfo<Article> pageInfo =new PageInfo<>(list);


        return pageInfo;

    }


}
