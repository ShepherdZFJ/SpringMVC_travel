package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.domain.Favorite;
import com.shepherd.springmvc_travel.service.IFavoriteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;

import java.util.List;

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

    @Test
    public void isFavorite() {
    }

    @Test
    public void addFavorite() {
    }

    @Test
    public void findAll() {
        List<Favorite> favorites = favoriteService.findAll(26);
        System.out.println(favorites);
    }
}