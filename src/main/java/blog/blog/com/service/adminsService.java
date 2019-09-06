package blog.blog.com.service;


import blog.blog.com.entity.Admins;
import org.springframework.stereotype.Service;

@Service
public interface adminsService {

    Admins selectByPrimaryKey(Integer adminId);


    Admins isLogin(Admins admin);

}
