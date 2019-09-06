package blog.blog.com.service.serviceImpl;

import blog.blog.com.entity.WechatUser;
import blog.blog.com.mapper.WechatUserMapper;
import blog.blog.com.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatUserServiceImpl implements WechatUserService {
    @Autowired
    private WechatUserMapper wechatUserMapper;

    @Override
    public int insertUser(WechatUser wechatUsers) {

        WechatUser wechatUser=wechatUserMapper.selectUserByopenId(wechatUsers.getOpenId());

        if(null==wechatUser){

            int code=wechatUserMapper.insertUser(wechatUsers);


            if(code>0){
                return  1;
            }else {

                return -1;
            }

        }else {
               wechatUsers.setUserId(wechatUser.getUserId());
                wechatUserMapper.update(wechatUsers);
        }
            return -2;
    }

    @Override
    public WechatUser selectUserBySession(String session) {
        return wechatUserMapper.selectUserBySession(session);
    }
}
