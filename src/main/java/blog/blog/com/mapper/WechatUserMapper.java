package blog.blog.com.mapper;

import blog.blog.com.entity.WechatUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WechatUserMapper {
    WechatUser selectUserByopenId(String openId);
    int insertUser(WechatUser wechatUser);
    int update(WechatUser wechatUser);
    WechatUser selectUserBySession(String session);

}
