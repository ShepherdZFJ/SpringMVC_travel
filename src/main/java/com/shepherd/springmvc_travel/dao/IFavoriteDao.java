package com.shepherd.springmvc_travel.dao;

import com.shepherd.springmvc_travel.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IFavoriteDao extends JpaRepository<Favorite,Integer>, JpaSpecificationExecutor<Favorite> {

    public Favorite findByRidAndUid(Integer rid,Integer uid);
    public List<Favorite> findAllByUid(Integer uid);
}
