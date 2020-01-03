package com.shepherd.springmvc_travel.dao;

import com.shepherd.springmvc_travel.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRouteDao extends JpaRepository<Route,Integer>, JpaSpecificationExecutor<Route> {

}
