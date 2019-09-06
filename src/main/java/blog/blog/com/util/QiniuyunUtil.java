package blog.blog.com.util;




import blog.blog.com.entity.FileUrl;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;

import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FetchRet;
import com.qiniu.util.Auth;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;




public class QiniuyunUtil {
    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "SLxkQSIjBhEFOkNX5aXIuXBWbRp_jUNckoNaQOJV";
    private static final String SECRET_KEY = "d3YRr9CJi_l8dgibvD9bikSon_MerHHjsOdMJi0c";

    // 要上传的空间
    private static final String bucketname = "blog_img";

    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    private static final String DOMAIN = "pwh5ycj85.bkt.clouddn.com";

    private static final String style = "blogzyc";


    public  static  String getupToken(){
        //密钥配置上传的凭证

        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"fileUrl\": \""+DOMAIN+"/$(key)\"}"); //上传成功后返回这个json数据
        long expireSeconds=3600; //过期时间
        String upToken=auth.uploadToken(bucketname,null,expireSeconds,putPolicy);
        return  upToken;
    }

    public static  String uploadFile(String key,String localFilePath){

        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);

        //默认不指定key情况下，以文件内容的hash值作为文件名字

        String imgUrl=null;
        Response response=null;
        try {
            //上传文件
           response = uploadManager.put(localFilePath, key, getupToken());
           // System.out.println(response.bodyString());

            //解析上传文件的结果

            Gson gson = new Gson();
           // DefaultPutRet putRet = gson.fromJson(response.bodyString(), DefaultPutRet.class); 这里转就是个坑 自己定义一个对象！！！
            FileUrl  url= gson.fromJson(response.bodyString(),FileUrl.class);


           System.out.println(response.bodyString());
            System.out.println(url.fileUrl);
           imgUrl="http://"+url.fileUrl+"?"+style;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
                return imgUrl;
    }

    //base64上传方式

    public static String uploadFileBase64(byte[] base64, String key) throws Exception{
        String file64 = Base64.encodeToString(base64, 0);
        Integer l = base64.length;
        String uploadUrl = "http://up-z2.qiniup.com/putb64/" + l + "/key/"+ UrlSafeBase64.encodeToString(key);
        //非华南空间需要修改上传域名
        //上传文件
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(uploadUrl)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getupToken())
                .post(rb).build();
        //System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        return "http://"+DOMAIN +"/"+ key+"-"+style;
    }







}
