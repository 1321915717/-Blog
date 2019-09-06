package blog.blog.com.service;

import blog.blog.com.entity.WechatUser;
import org.springframework.stereotype.Service;

@Service
public interface WechatUserService {
    int insertUser(WechatUser wechatUser);
    WechatUser selectUserBySession(String session);

}
