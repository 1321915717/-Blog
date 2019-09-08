package blog.blog.com.controller.index;
import blog.blog.com.entity.WechatUser;
import blog.blog.com.entity.WechatuserInfo;
import blog.blog.com.service.WechatUserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import  blog.blog.com.util.PassUtil;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/index/login")
public class IndexLoginController {

    @Autowired
    private WechatUserService wechatUserService;

    public static String GETlogin(String url){

        String result = "";
        BufferedReader in = null;

        try {

            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求失败");
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();

                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return result;

    }

        @RequestMapping("/weixinLogin")
        public  Map<String,Object> login(String encryptedData, String iv, String code,String rawData){
        System.out.println("进来了"+encryptedData);


            Gson gson = new Gson();
            WechatuserInfo wechatuserInfo =gson.fromJson(rawData,WechatuserInfo.class);
            System.out.println(wechatuserInfo.getAvatarUrl());

            Map map = new HashMap<>();
            if (code == null || code.length() == 0) {

                map.put("status", 0);
                map.put("msg", "code是空的");

                return map;
            }
            System.out.println(code);
            String wxappid = "****";
            String wxSecret = "****";

          //  oyBqu4rrv9V9iwhjfjkrEUtxHQGk
            String grant_type = "authorization_code";
            String wexinapiurl = "https://api.weixin.qq.com/sns/jscode2session?" + "appid=" + wxappid + "&secret=" + wxSecret
                    + "&js_code=" + code + "&grant_type=" + grant_type;

            String  wexinapiurls= IndexLoginController.GETlogin(wexinapiurl);
            JSONObject json = JSONObject.fromObject(wexinapiurls);
            System.out.println("这里是openid和session_key" + json);
            String session_key = (String) json.get("session_key");
            String openid = (String) json.get("openid");
            try {
                System.out.println("进入解密成功程序");
                String result = PassUtil.decrypt(encryptedData, session_key, iv, "utf-8");
                //JSONObject jsonObject = JSONObject.fromObject(result);
                System.out.println(result.length()+"********************");
                if (null != result && result.length() > 0) {
                    map.put("status", 1);
                    map.put("msg", "解密成功");
                    HashMap userinfo = new HashMap<>();
                  //  userinfo.put("openid", json.get("openid"));
                    userinfo.put("session_key", json.get("session_key"));
                    userinfo.put("nickName",wechatuserInfo.getNickName());
                    userinfo.put("avatarUrl",wechatuserInfo.getAvatarUrl());
                    map.put("userInfo", userinfo);

                    WechatUser wechatUser = new WechatUser();
                    wechatUser.setOpenId(openid);
                   wechatUser.setUserImg(wechatuserInfo.getAvatarUrl());
                   wechatUser.setUserName(wechatuserInfo.getNickName());
                   wechatUser.setSessionKey(session_key);
                    int insertUsercode= wechatUserService.insertUser(wechatUser);

                    if(insertUsercode==1){
                        map.put("userAdd", "添加成功");
                        System.out.println("添加成功");
                    }else if (insertUsercode==-2){
                        map.put("userAdd", "用户存在");
                        System.out.println("用户存在");
                    }else {
                        map.put("userAdd", "添加失败");
                        System.out.println("添加失败");
                    }


                }

            } catch (Exception e) {
                System.out.println("解密失败");
            }


            return map;

        }


}
