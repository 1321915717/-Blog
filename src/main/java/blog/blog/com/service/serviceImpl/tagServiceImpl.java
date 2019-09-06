package blog.blog.com.service.serviceImpl;

import blog.blog.com.entity.Tag;
import blog.blog.com.mapper.tagMapper;
import blog.blog.com.service.tagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tagServiceImpl implements tagService {
    @Autowired
    private tagMapper  tagMapper;

    @Override
    public List<Tag> selectAll() {
         List<Tag> tagList=  tagMapper.selectAll();



        return tagList;
    }

    @Override
    public int updateByPrimaryKey(Tag record) {

        int code= tagMapper.updateByPrimaryKey(record);
        if(code>0){
            return  1;

        }else {
            return  -1;
        }





    }

    @Override
    public int deleteByPrimaryKey(Integer[] tagId) {


        int code =tagMapper.deleteByPrimaryKey(tagId);

        if(code>0){

            return  1;

        }else{

            return -1;
        }
    }

    @Override
    public int insert(Tag record) {
        int code=tagMapper.insert(record);
        if(code>0){

            return 1;

        }else {

            return 0;
        }



    }

    @Override
    public List<Tag> selectTagsByTagId(Integer[] id) {
        return tagMapper.selectTagsByTagId(id);
    }
}
