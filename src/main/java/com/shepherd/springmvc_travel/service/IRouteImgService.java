package com.shepherd.springmvc_travel.service;

import com.shepherd.springmvc_travel.domain.RouteImg;

import java.util.List;

public interface IRouteImgService {
    public List<RouteImg> findRouteImg(Integer rid);
}
