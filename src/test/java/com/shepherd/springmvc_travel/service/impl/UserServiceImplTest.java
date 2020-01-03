package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.domain.User;
import com.shepherd.springmvc_travel.dao.IUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private IUserDao userDao;

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("hello1111111");
        user.setPassword("1235fdg");
        user.setBirthday(new Date());
        userDao.save(user);
    }

    @Test
    public void testFindByUsername(){
         User user = userDao.findByUsername("%hello%");

            System.out.println(user);


    }
}