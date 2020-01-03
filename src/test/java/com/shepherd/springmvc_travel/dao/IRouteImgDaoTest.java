package com.shepherd.springmvc_travel.dao;

import com.shepherd.springmvc_travel.domain.RouteImg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IRouteImgDaoTest {
    @Autowired
    private IRouteImgDao routeImgDao ;

    @Test
    public void testFindImg(){
        List<RouteImg> routeImgs = routeImgDao.findByRid(1);
        System.out.println(routeImgs);
    }

}