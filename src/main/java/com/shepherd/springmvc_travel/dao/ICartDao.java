package com.shepherd.springmvc_travel.dao;

import com.shepherd.springmvc_travel.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICartDao extends JpaRepository<Cart,Integer>, JpaSpecificationExecutor<Cart>  {

    public Cart findByUserIdAndRouteId(Integer userId ,Integer routeId);
}
