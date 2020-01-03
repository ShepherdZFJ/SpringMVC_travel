package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.dao.IRouteImgDao;
import com.shepherd.springmvc_travel.domain.RouteImg;
import com.shepherd.springmvc_travel.service.IRouteImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteImgServiceImpl implements IRouteImgService {

    @Autowired
    private IRouteImgDao routeImgDao;
    @Override
    public List<RouteImg> findRouteImg(Integer rid) {
        return routeImgDao.findByRid(rid);
    }
}
