package blog.blog.com.service.serviceImpl;

import blog.blog.com.entity.UserD;
import blog.blog.com.mapper.UserDMapper;
import blog.blog.com.mapper.articleMapper;
import blog.blog.com.service.UserDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.ldap.PagedResultsControl;


@Service
public class UserDServiceImpl implements UserDService {

    @Autowired
    private UserDMapper userDMapper;

    @Autowired
    private articleMapper articleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addGive(UserD userD) {

        int code;
        UserD userDs=userDMapper.selectByArticleAndUserId(userD);

        if(null==userDs){


            code= userDMapper.insert(userD);

            int  code2=articleMapper.addCommentLike(userD.getUdArticleId());

           if(code>0){

               code=  1;

           }else {
               code=-1;
               throw  new RuntimeException();
           }


        }else {

            code=-2;
        }




        return code;
    }
}
