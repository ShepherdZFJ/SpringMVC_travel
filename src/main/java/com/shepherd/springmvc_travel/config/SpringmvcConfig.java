package com.shepherd.springmvc_travel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Configuration
//@EnableWebMvc千万不能加，谁加谁傻逼
public class SpringmvcConfig implements WebMvcConfigurer {
//
//    @Configuration
////@E
//    public class WebConfig implements WebMvcConfigurer {
//        private final List<String> EXCLUDE_PATH = Arrays.asList("/", "/css/**", "/js/**", "/img/**", "/media/**", "/vendors/**");
//
//        @Autowired
//       // HandlerInterceptor hjh;
//
//        @Override
//        public void addInterceptors(InterceptorRegistry registry) {
//            /*
//             *  对根目录和静态文件不需要进行拦截，如果对根目录（即登录页面）进行拦截，将会导致循环重定向
//             */
////            registry.addInterceptor(sessionInterceptor)
////                    .addPathPatterns("/**")
////                    .excludePathPatterns(EXCLUDE_PATH);
//      }
    }

