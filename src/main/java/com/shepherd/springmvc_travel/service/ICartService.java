package com.shepherd.springmvc_travel.service;

import com.shepherd.springmvc_travel.domain.Cart;

public interface ICartService {

    public void addCart(Cart cart);

    public Cart findOne(Integer userId , Integer routeId);
}
