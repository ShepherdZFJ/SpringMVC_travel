package com.shepherd.springmvc_travel.service;

import com.shepherd.springmvc_travel.domain.Favorite;

public interface IFavoriteService {

    public Boolean isFavorite(Integer rid, Integer uid);

    public Boolean addFavorite(Integer rid,Integer uid);
}
