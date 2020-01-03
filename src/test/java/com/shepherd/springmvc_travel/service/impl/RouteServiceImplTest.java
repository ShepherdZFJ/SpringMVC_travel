package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.domain.Favorite;
import com.shepherd.springmvc_travel.domain.PageBean;
import com.shepherd.springmvc_travel.service.IFavoriteService;
import com.shepherd.springmvc_travel.service.IRouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceImplTest {

    @Autowired
    private IRouteService routeService;
    @Autowired
    private IFavoriteService favoriteService;
    @Test
    public void loadWithPage() {

        PageBean pageBean = routeService.loadWithPage(5, 1, 5,"%西安","");
        System.out.println(pageBean);

    }

    @Test
    public void test1(){
        PageBean pageBean= routeService.loadWithPage(1, 8, "count");
        System.out.println(pageBean);

    }

    @Test
    public void findRoute() {
        List<Favorite> favorites = favoriteService.findAll(26);
        List<Integer> list = new ArrayList<>();
        for(Favorite favorite:favorites){
            list.add(favorite.getRid());
        }
        List route = routeService.findRoute(list);
        System.out.println(route);

    }

    @Test
    public void loadWithPage1() {
        List<Favorite> favorites = favoriteService.findAll(26);
        List<Integer> list = new ArrayList<>();
        for(Favorite favorite:favorites){
            list.add(favorite.getRid());
        }
        PageBean pageBean = routeService.loadWithPage(1, 8, list);
        System.out.println(pageBean);

    }
}