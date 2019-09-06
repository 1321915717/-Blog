package blog.blog.com.mapper;

import blog.blog.com.entity.UserD;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDMapper {

    List<Integer> selectByUserId(Integer id);
   UserD selectByArticleAndUserId(UserD userD);

   int insert(UserD userD);
}
