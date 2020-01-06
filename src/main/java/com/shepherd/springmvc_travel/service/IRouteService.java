package com.shepherd.springmvc_travel.service;

import com.shepherd.springmvc_travel.domain.PageBean;
import com.shepherd.springmvc_travel.domain.Route;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public interface IRouteService<T> {


    public PageBean<T> loadWithPage(Integer currentPage, Integer pageSize,String order);

    public PageBean<T> loadWithPage(Integer currentPage, Integer pageSize,List<Integer>where);

    public PageBean<T> loadWithPage(Integer currentPage, Integer pageSize, Map<String,Object> map, String order);

    public Route findRoute(Integer id);

    public Boolean updateRouteCount(Route route);

    public List<Route> findRoute(Collection<Integer>c);
}
