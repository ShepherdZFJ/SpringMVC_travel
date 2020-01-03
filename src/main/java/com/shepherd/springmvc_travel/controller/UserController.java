package com.shepherd.springmvc_travel.controller;

import com.shepherd.springmvc_travel.domain.ResultInfo;
import com.shepherd.springmvc_travel.domain.User;
import com.shepherd.springmvc_travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/getRegisterList")
    public ModelAndView getRegisterList(){
        ModelAndView mv = new ModelAndView("register");
        return mv;
    }

    @RequestMapping("/getRegister_ok")
    public ModelAndView getRegister_ok(){
        ModelAndView mv = new ModelAndView("register_ok");
        return mv;
    }

    @RequestMapping("/active")
    @ResponseBody
    public String active(@RequestParam("code") String code){
        String active = userService.active(code);
        return active;
    }

    @RequestMapping("/getLoginList")
    public ModelAndView getLoginList() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
    @RequestMapping("/exit")
    public String exit(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        return "login";

    }

    @RequestMapping("login/submit")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, User user){
        //System.out.println(user);
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        Object info = userService.login(user, checkcode_server);
        if(((ResultInfo)info).isFlag()){
            user.setId((Integer) ((ResultInfo) info).getData());
            request.getSession().setAttribute("user",user);//登录成功标记
        }
        return info;

    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Object findOne(HttpServletRequest request, HttpServletResponse response){
        Object user = request.getSession().getAttribute("user");
        return user;
    }


    @RequestMapping("register/submit")
    @ResponseBody
    public Object register(HttpServletRequest request, HttpServletResponse response, User user){
        //System.out.println(user);
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        Object info = userService.regist(user, checkcode_server);
//        if(((ResultInfo)regist).isFlag())
//        {
//            return "register_ok";
//        }
        return info;

    }
    @RequestMapping("/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response){

        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0,0, width,height);

        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=4;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }

        //产生4个随机验证码，12Ey
        String checkCode = sb.toString();
        //将验证码放入HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        try {
            ImageIO.write(image,"PNG",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
