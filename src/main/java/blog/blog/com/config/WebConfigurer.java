package blog.blog.com.config;

import blog.blog.com.intercepors.LoginIntercepor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginIntercepor loginIntercepor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问


        registry.addInterceptor(loginIntercepor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login","/admin/index","/admin/islogin");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
