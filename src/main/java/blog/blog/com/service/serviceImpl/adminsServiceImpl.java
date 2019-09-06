package blog.blog.com.service.serviceImpl;

import blog.blog.com.entity.Admins;
import blog.blog.com.mapper.adminsMapper;
import blog.blog.com.service.adminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class adminsServiceImpl  implements adminsService {

    @Autowired
    private adminsMapper adminsMapper;


    @Override
    public Admins selectByPrimaryKey(Integer adminId) {

        return  adminsMapper.selectByPrimaryKey(adminId);
    }
    @Override
      public Admins isLogin(Admins admin){

        Admins islogin=adminsMapper.isLogin(admin);

        return  islogin;


    }
}
