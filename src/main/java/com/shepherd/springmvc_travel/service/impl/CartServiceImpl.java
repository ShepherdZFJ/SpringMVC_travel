package com.shepherd.springmvc_travel.service.impl;

import com.shepherd.springmvc_travel.dao.ICartDao;
import com.shepherd.springmvc_travel.dao.ICategoryDao;
import com.shepherd.springmvc_travel.domain.Cart;
import com.shepherd.springmvc_travel.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private ICartDao cartDao;

    @Override
    public void addCart(Cart cart) {
        cartDao.save(cart);
    }

    @Override
    public Cart findOne(Integer userId , Integer routeId) {
        return cartDao.findByUserIdAndRouteId(userId , routeId);
    }

}