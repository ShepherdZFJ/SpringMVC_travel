package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.dao.IFavoriteDao;
import com.shepherd.springmvc_travel.domain.Favorite;
import com.shepherd.springmvc_travel.domain.Route;
import com.shepherd.springmvc_travel.service.IFavoriteService;
import com.shepherd.springmvc_travel.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

    @Autowired
    private IFavoriteDao favoriteDao;
    @Autowired
    private IRouteService routeService;
    @Override
    public Boolean isFavorite(Integer rid, Integer uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(rid, uid);
        return favorite != null;
    }

    @Override
    @Transactional
    public Boolean addFavorite(Integer rid, Integer uid) {
        Favorite favorite = new Favorite();
        favorite.setRid(rid);
        favorite.setUid(uid);
        favorite.setDate(new Date());
        favoriteDao.save(favorite);
        Route route = routeService.findRoute(rid);
        route.setCount(route.getCount()+1);
        routeService.updateRouteCount(route);
        return true;
    }
}
