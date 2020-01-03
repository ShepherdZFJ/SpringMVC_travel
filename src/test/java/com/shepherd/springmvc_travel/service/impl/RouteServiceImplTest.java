package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.domain.PageBean;
import com.shepherd.springmvc_travel.service.IRouteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceImplTest {

    @Autowired
    private IRouteService routeService;
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
}