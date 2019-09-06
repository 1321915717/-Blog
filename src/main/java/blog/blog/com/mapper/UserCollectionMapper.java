package blog.blog.com.mapper;

import blog.blog.com.entity.UserCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCollectionMapper {


     List<Integer> selectByUserId(Integer article);
     int insert(UserCollection userCollection);
     UserCollection selectByArticleAndUserId(UserCollection userCollection);
}
