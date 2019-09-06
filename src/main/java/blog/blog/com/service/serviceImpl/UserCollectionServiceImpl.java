package blog.blog.com.service.serviceImpl;

import blog.blog.com.entity.UserCollection;
import blog.blog.com.mapper.UserCollectionMapper;
import blog.blog.com.mapper.articleMapper;
import blog.blog.com.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserCollectionServiceImpl implements UserCollectionService {

    @Autowired
    private UserCollectionMapper userCollectionMapper;

    @Autowired
    private articleMapper articleMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addLike(UserCollection userCollection) {

        try {

            UserCollection userCollections=  userCollectionMapper.selectByArticleAndUserId(userCollection);

            if(null==userCollections){
                int code=   userCollectionMapper.insert(userCollection);

                if(code==0){

                    throw  new RuntimeException();
                }else{

                    if(articleMapper.addCommentLike(userCollection.getUcArticleId())==0){

                        throw  new RuntimeException();
                    }


                    return  1;
                }


            }else{

                return 2;   //2代表已经收藏过了
            }

        }catch (Exception e){

            throw  new RuntimeException(e);

        }




    }
}
