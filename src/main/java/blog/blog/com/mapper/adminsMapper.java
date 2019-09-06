package blog.blog.com.mapper;

import blog.blog.com.entity.Admins;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface adminsMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admins record);

    int insertSelective(Admins record);

    Admins selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admins record);

    int updateByPrimaryKey(Admins record);

    Admins isLogin(Admins admin);

}