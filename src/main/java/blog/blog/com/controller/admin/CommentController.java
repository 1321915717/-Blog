package blog.blog.com.controller.admin;

import blog.blog.com.entity.Comment;
import blog.blog.com.service.CommentService;
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

@RestController
@RequestMapping("/admin/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/allComment")
    public PageInfo<Comment> allComment(@RequestParam(defaultValue = "1") Integer pageNum,
                                                   @RequestParam(defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        List<Comment> list =commentService.selectAll();
        PageInfo<Comment> pageInfo=new PageInfo<>(list);

        return pageInfo;

    }
    @RequestMapping("/deleteComment/{ids}")
    public Map<String,Object> deleteComment( @PathVariable Integer [] ids){
        Map<String,Object> map =new HashMap<>();
        int code= commentService.deleteByCommentId(ids);

        if(code>0){

            map.put("status",200);
            map.put("msg","删除成功");
        }else{

            map.put("msg","删除失败");
        }

        return  map;
    }
}