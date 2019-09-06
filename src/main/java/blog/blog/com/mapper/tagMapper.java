package blog.blog.com.mapper;

import blog.blog.com.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface tagMapper {
    int deleteByPrimaryKey(Integer[] tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
    List<Tag> selectAll();

    List<Tag>selectTagsByTagId(Integer [] id);

    List<Tag> selectTagsByTagIds(List<Integer> ids);

}