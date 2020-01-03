package com.shepherd.springmvc_travel.dao;

import com.shepherd.springmvc_travel.domain.RouteImg;
import org.hibernate.secure.internal.JaccSecurityListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IRouteImgDao extends JpaRepository<RouteImg,Integer> , JpaSpecificationExecutor<RouteImg> {
    public List<RouteImg> findByRid(Integer id);
}
