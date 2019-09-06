package blog.blog.com.controller.admin;


import blog.blog.com.entity.Category;
import blog.blog.com.service.categoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin/category")
@RestController
public class optionsController {


        @Autowired
        private categoryService categoryService;



    @RequestMapping("/allCategorys")

    public PageInfo<Category> allcategorys(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum ,
                                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){


            System.out.println(pageNum+"*********"+pageSize);

        PageHelper.startPage(pageNum,pageSize);
       List<Category> list =categoryService.selectAll();
        PageInfo<Category> pageInfo = new PageInfo<Category>(list);

        return  pageInfo;

    }
    @RequestMapping("/allCategory")

    public List<Category> allcategorys(){
        List<Category> list =categoryService.selectAll();

        return  list;

    }






        @RequestMapping("/editCategory")
        @Transactional
        public Map<String,Object> editCategory(Integer id, String categoryName,String categoryDescription){

                Map<String,Object>  map = new HashMap<>();

                if (id!=null) {
                        Category category =new Category();
                        category.setCategoryId(id);
                        category.setCategoryDescription(categoryDescription);

                        System.out.println(category.getCategoryId());
                        category.setCategoryName(categoryName);
                        int code=categoryService.updateByPrimaryKeySelective(category);
                        System.out.println(code);
                        if(code>0){

                                map.put("status","1");
                                map.put("msg","修改成功");
                        }else {
                                map.put("status","0");
                                map.put("msg","修改失败");

                        }
                }else{

                        map.put("status","0");
                        map.put("msg","修改的列无效！");
                }
                                return  map;

        }

        @RequestMapping("/addCategory")
        public Map<String,Object> addCategory(String addcateName,String addcatemiaosu){
                        Map<String,Object> map = new HashMap<>();


                      if(null!=addcateName&&null!=addcatemiaosu){
                                Category category = new Category();
                                category.setCategoryName(addcateName);
                                category.setCategoryDescription(addcateName);
                                category.setCategoryOrder(0);  //默认给0 如果需要分类排序自己在后台加一个input 框就行

                              int code= categoryService.insert(category);

                              if(code==1){
                                      map.put("status","1");
                                      map.put("msg","修改成功");
                              }else{
                                      map.put("status","0");
                                      map.put("msg","修改失败");

                              }

                      }else{
                              map.put("status","0");
                              map.put("msg","添加失败！");

                      }



                return  map;
        }


        @RequestMapping("/deleteCategory/{id}")
        public Map<String,Object> deleteCategory(@PathVariable Integer[] id){
                Map<String,Object> map = new HashMap<>();

               if(id!=null){
                        int code=categoryService.deleteByPrimaryKey(id);

                       if(code==1){
                               map.put("status","1");
                               map.put("msg","删除成功");
                       }else{
                               map.put("status","0");
                               map.put("msg","删除失败");

                       }


               } else{

                       map.put("status","0");
                       map.put("msg","删除失败！");
               }




                return  map;
        }
}
