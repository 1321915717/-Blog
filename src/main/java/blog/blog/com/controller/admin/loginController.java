package blog.blog.com.controller.admin;


import blog.blog.com.entity.Admins;
import blog.blog.com.service.adminsService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class loginController {


   @Autowired
    private adminsService adminsService;

    @RequestMapping("/islogin")    //登录接口

    public  Map<String,Object> login(
            HttpSession session,String username,String password){


        Map<String,Object> msg= new HashMap<>();


        Admins  adminsobj=  new Admins();
        adminsobj.setAdminName(username);
        adminsobj.setAdminPass(password);

        Admins adminss=adminsService.isLogin(adminsobj);

        if(adminss==null){

                msg.put("code",0);
                msg.put("msg","账号或者密码不对");


        }else {

           msg.put("code",1);
           msg.put("msg","");
            msg.put("admin",adminss);
           session.setAttribute("admin",adminss);





        }


            return msg;




    }

    @RequestMapping("/login")  //登录页面
    public  String login(){

        return "login";
    }

    @RequestMapping("/index")  //登录主页
    public  String index(HttpServletRequest request, HttpSession session){

        Admins admin= (Admins) session.getAttribute("admin");

        request.setAttribute("admin",admin.getAdminName());


        return  "index";
    }

    @RequestMapping("/ceshi")
    public String ceshi(HttpServletRequest request){





        return  "welcome1 :: html";
    }




}
