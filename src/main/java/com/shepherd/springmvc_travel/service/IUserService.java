package com.shepherd.springmvc_travel.service;

import com.shepherd.springmvc_travel.domain.User;

public interface IUserService {


    Object regist(User user,String checkCode);

    String active(String code);

    Object login(User user,String checkCode);






}
