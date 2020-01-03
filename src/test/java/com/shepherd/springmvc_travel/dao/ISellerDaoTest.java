package com.shepherd.springmvc_travel.dao;

import com.shepherd.springmvc_travel.domain.Seller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ISellerDaoTest {
    @Autowired
    private ISellerDao sellerDao;

    @Test
    public void testFindUser(){
        Seller dao = sellerDao.findBySid(1);
        System.out.println(dao);
    }


}