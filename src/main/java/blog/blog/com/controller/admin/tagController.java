package blog.blog.com.controller.admin;

import blog.blog.com.service.tagService;
import  blog.blog.com.entity.Tag;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin/tag")
@RestController
public class tagController {


    @Autowired
    private tagService tagService;


    @RequestMapping("/allTag")
    public PageInfo<Tag>  selectAll(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam( defaultValue = "5") Integer pageSize
    ){
        PageHelper.startPage(pageNum,pageSize);
        List<Tag> list= tagService.selectAll();

        PageInfo<Tag>  pageInfo =new PageInfo<Tag>(list);


        return pageInfo;

    }

    @RequestMapping("/allTags")
    public List<Tag>  selectAll(){

        List<Tag> list= tagService.selectAll();
        return list;

    }

    @RequestMapping("/editTag")
    public Map<String,Object> editTag(Integer id,String tagName,String tagDescription ){

        Map<String,Object>  map = new HashMap<>();

        if(null!=id){
            Tag tag = new Tag();
            tag.setTagId(id);
            tag.setTagName(tagName);
            tag.setTagDescription(tagDescription);

             int code= tagService.updateByPrimaryKey(tag);

             if(code>0){
                 map.put("status","1");
                 map.put("msg","修改成功");
             }else{
                 map.put("status","0");
                 map.put("msg","修改的列无效！");
             }

        }else{

            map.put("status","0");
            map.put("msg","修改的列无效！");
        }
        return  map;
    }
    @RequestMapping("/deleteTag/{id}")
    public  Map<String,Object> deleteTag(@PathVariable Integer[] id){
        Map<String,Object>  map = new HashMap<>();

        int code=tagService.deleteByPrimaryKey(id);
        if(code==1){
            map.put("status","1");
            map.put("msg","删除成功");

        }else {
            map.put("status","0");
            map.put("msg","删除失败！");
        }


        return  map;

    }


    @RequestMapping("/addTag")
    public  Map<String,Object> addTag( String addtagName,String addtagmiaosu){
        Map<String,Object>  map = new HashMap<>();

            if(null!=addtagName&&null!=addtagmiaosu){

                Tag tag =new Tag();
                tag.setTagName(addtagName);
                tag.setTagDescription(addtagmiaosu);
                int code=tagService.insert(tag);

                if(code==1){

                    map.put("status","1");
                    map.put("msg","添加成功");
                }else {

                    map.put("status","0");
                    map.put("msg","添加失败！");
                }
            }else{
                map.put("status","0");
                map.put("msg","添加失败！");

            }


            return  map;
    }


}
