package com.shepherd.springmvc_travel.dao;

import com.shepherd.springmvc_travel.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IRouteDao extends JpaRepository<Route,Integer>, JpaSpecificationExecutor<Route> {



    public List<Route> findAllByIdIn(Collection<Integer> c);
}
