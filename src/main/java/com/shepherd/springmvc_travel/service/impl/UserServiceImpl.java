package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.domain.ResultInfo;
import com.shepherd.springmvc_travel.domain.User;
import com.shepherd.springmvc_travel.dao.IUserDao;
import com.shepherd.springmvc_travel.service.IUserService;
import com.shepherd.springmvc_travel.util.MailUtils;
import com.shepherd.springmvc_travel.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    ResultInfo info = new ResultInfo(true);

    public ResultInfo dataCheck(User user,String str){
        ResultInfo info = new ResultInfo(true);

        //检测验证码是否正确
        //验证码错误
        if(str == null || !str.equalsIgnoreCase(user.getCheck())){
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            return info;
        }

        //检测用户名是否存在
        //1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        //判断u是否为null
        if(u != null){
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("用户名已存在，请重新输入");
            return info;
        }

        //检测两次密码输入是否一致
        if( ! user.getPassword().equals(user.getPasswordAgain())){
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("两次输入密码不一致，请重新输入");
            return info;
        }
        return info;
    }

    public void dataHanding(User user){
        //2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        //2.2设置激活状态
        user.setStatus("N");

    }

    @Override
    public Object regist(User user,String checkCode) {
        ResultInfo resultInfo = this.dataCheck(user, checkCode);
        if(!resultInfo.isFlag())
        {
            return resultInfo;
        }
        else {


            this.dataHanding(user);
            userDao.save(user);
            //3.激活邮件发送，邮件正文？

            String content = "<a href='http://localhost:8080/user/active?code=" + user.getCode() + "'>点击激活【shepherd旅游网】</a>";

            MailUtils.sendMail(user.getEmail(), content, "激活邮件");

            return resultInfo;
        }
    }

    @Override
    public String  active(String code) {


        String msg;
        User u = userDao.findByCode(code);
        if(u!=null) {
            u.setStatus("Y");
            userDao.save(u);
            msg = "激活成功，请<a href='http://localhost:8080/user/getLoginList'>登录</a>";
        }
        else{
            msg="激活失败，请联系系统管理员";
        }
        return msg;

    }

    @Override
    public Object login(User user,String str) {

        ResultInfo info = new ResultInfo(true);
        //检测验证码是否正确
        if(str == null || !str.equalsIgnoreCase(user.getCheck())){
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            return info;
        }
        //检测用户名或密码是否正确
        User u = userDao.findByUsernameAndAndPassword(user.getUsername(), user.getPassword());
        //判断u是否为null
        if(u == null){
            //登录失败
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误，请重新输入");
            return info;
        }

        if(! "Y".equals(u.getStatus())){
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("该用户尚未激活，请先激活再登陆");
            return info;
        }
        info.setData(u.getId());
        return info;

    }


}
