package blog.blog.com.service;

import blog.blog.com.entity.Tag;

import java.util.List;

public interface tagService {

    List<Tag> selectAll();
    int updateByPrimaryKey(Tag record);
    int deleteByPrimaryKey(Integer[] tagId);
    int insert(Tag record);
    List<Tag>selectTagsByTagId(Integer [] id);

}
