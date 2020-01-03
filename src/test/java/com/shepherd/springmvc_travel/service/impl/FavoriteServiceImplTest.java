package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.service.IFavoriteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoriteServiceImplTest {
    @Autowired
    private IFavoriteService favoriteService;

    @Test
    public void testFindFavorite(){
        Boolean flag = false;
        flag= favoriteService.isFavorite(1,26);
        System.out.println(flag);
    }

}