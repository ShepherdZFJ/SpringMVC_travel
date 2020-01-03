package com.shepherd.springmvc_travel.service;

import com.shepherd.springmvc_travel.domain.Favorite;

import java.util.List;

public interface IFavoriteService {

    public Boolean isFavorite(Integer rid, Integer uid);

    public Boolean addFavorite(Integer rid,Integer uid);

    public List<Favorite> findAll(Integer uid);
}
