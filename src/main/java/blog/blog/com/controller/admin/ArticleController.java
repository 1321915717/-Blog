package blog.blog.com.controller.admin;


import blog.blog.com.entity.*;
import blog.blog.com.service.*;
import blog.blog.com.util.QiniuyunUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import sun.awt.SunHints;
//import  java.io.File;
import javax.servlet.http.HttpServletRequest;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
//import org.apache.commons.io.IOUtils;

@RestController
@RequestMapping("/admin/Article")
public class ArticleController {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRefCategoryService articleRefCategoryService;
    @Autowired
    private categoryService categoryService;
    @Autowired
    private ArticleRefTagService articleRefTagService;
    @Autowired
    private tagService tagService;

    @RequestMapping("/allArticle")
    public PageInfo<Article> allArticle(@RequestParam(defaultValue = "1") Integer pageNum,
                                                   @RequestParam(defaultValue = "5") Integer pageSize,
                                                   @RequestParam(required = false) String keywords,Integer state
    ) {

        PageHelper.startPage(pageNum, pageSize);



         //state一定要对应数据表Article的article_status 字段  1为显示  0为草稿  前端给的如果是-1 就是查全部 建议搞个枚举类存储方便维护
        List<Article>  list =articleService.selectAll(keywords, state);

        PageInfo<Article> pageInfo = new PageInfo<Article>(list);
        return pageInfo;

    }



    @RequestMapping("/intoArticle/{id}")
    public Map<String, Object> intoArticle(
            @PathVariable Integer id
    ) {

        Map<String, Object> map = new HashMap<>();
        Article article = articleService.selectByPrimaryKey(id);

        //如果 listtagid的长度 也就是拿不到值 这里会抛出一个异常

        List<Tag> listTag=null;
        Category category=null;
        List<Integer> listTagid = articleRefTagService.selectTagsByArticle(id);

        if (listTagid.size()!= 0&&listTagid!=null) {

            Integer[] tagids = listTagid.toArray(new Integer[listTagid.size()]);
             listTag = tagService.selectTagsByTagId(tagids);

        }
        //如果 categoryid 的长度为null也就是拿不到值 这里会抛出一个异常
       Integer categoryid=articleRefCategoryService.selectCategoryByArticleId(id);
       if(categoryid!=null){
           category = categoryService.selectByPrimaryKey(categoryid);


       }
        map.put("article", article);
        map.put("category", category);
        map.put("tag", listTag);
        map.put("status",200);
        return map;

    }

    @RequestMapping("/editArticle")

    public Map<String, Object> editArticle(@RequestParam(value = "id") Integer id, String title, String htmlContent,
                                           Integer state, @RequestParam(value = "checkList") Integer[] checkList,Integer categoryid,String articleImg) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Article article = new Article();
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();
        article.setArticleTitle(title);
        article.setArticleContent(htmlContent);
        article.setArticleUpdateTime(dateFormat.format(date));
        article.setArticleImg(articleImg);
        article.setArticleStatus(state);   //0为草稿 1为 发表
        article.setArticleGiveCount(0);

        if(id==-1){  //如果接收回来的id为-1 证明 是新增用户
            article.setArticleCreateTime(dateFormat.format(date));
            article.setArticleLikeCount(0);
           article.setArticleViewCount(0);
           article.setArticleCommentCount(0);
           article.setArticleCommentCount(0);



        }else {
            article.setArticleId(id);

       }


        articleService.insertArticle(article,checkList,categoryid);

        map.put("status", "1");
        map.put("msg", "增加成功");
       return map;
    }
    /*@RequestMapping(value = "/uploadimg", method = RequestMethod.POST) 这个方法是普通上传方法首先要在本地构建一个图片目录  下面是七牛云的base64上传
    public Map<String,Object> uploadImg(HttpServletRequest req, MultipartFile image) {
        Map<String,Object> map =new HashMap<>();
        StringBuffer url = new StringBuffer();
        String filePath = "/blogimg/" + sdf.format(new Date());
       String imgFolderPath = req.getServletContext().getRealPath(filePath);
        String  imgFolderPaths="C:\\Users\\1321915717\\Desktop\\blog\\blogimg";
        File imgFolder = new File(imgFolderPath);
        File imgFolders = new File(imgFolderPaths);

        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        if(!imgFolders.exists()){

            imgFolders.mkdirs();
        }

        url.append(req.getScheme())
                .append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append(filePath);
        String imageName = UUID.randomUUID().toString();
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolders, imgName)));
            url.append("/").append(imgName);
            String urls = QiniuyunUtil.uploadFile(imgName,imgFolderPaths+"\\"+imgName);
            byte[] bytes = image.getBytes();

            map.put("code","success");
            map.put("mag","上传成功");
            map.put("url",urls);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("error","上传失败");
        return map;
    }*/

    @RequestMapping("/uploadimg")
    public Map<String, Object> uploadImg(@RequestParam MultipartFile image, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        if (image.isEmpty()) {

            map.put("code", "fail");
            map.put("msg", "文件为空，请重新上传");


            return map;
        }
        try {
            byte[] bytes = image.getBytes();
            String imageName = UUID.randomUUID().toString();


            try {
                //使用base64方式上传到七牛云
                  String url = QiniuyunUtil.uploadFileBase64(bytes,imageName);


                map.put("code","success");
                map.put("mag","上传成功");
                map.put("url",url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
        } catch (IOException e) {
            map.put("code", 500);
            map.put("msg", "发生异常");
            return map;
        }


    }

   @RequestMapping("/deleteArticle")
    public  Map<String,Object> deleteArticle(Integer []ids){
       Map<String,Object> map =new HashMap<>();


       articleService.deleteArticle(ids);
       map.put("status","success");

       return map;


   }

}
