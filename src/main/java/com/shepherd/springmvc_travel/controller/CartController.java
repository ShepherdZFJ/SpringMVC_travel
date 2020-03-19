package com.shepherd.springmvc_travel.controller;

import com.shepherd.springmvc_travel.domain.Cart;
import com.shepherd.springmvc_travel.domain.User;
import com.shepherd.springmvc_travel.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("/getCartList")
    public ModelAndView getCartList(){
        ModelAndView mv = new ModelAndView("BuyCar");
        return mv;
    }

    @RequestMapping("/addCart")
    public void addCart(HttpServletRequest request, HttpServletResponse response, @RequestParam(name ="rid")String rid){

        //1.获得当前登录用户的id
        Integer userId;
        Integer routeId=1;
        Cart cart = new Cart();

        User user = (User) request.getSession().getAttribute("user");
        userId = user.getId();

        if(rid != null && rid.length() > 0 && !"null".equals(rid)){
            routeId = Integer.parseInt(rid);
        }

        cart.setUserId(userId);
        cart.setRouteId(routeId);

        Cart one = cartService.findOne(userId, routeId);
        if(one == null){
            cart.setQuantity(1);
            cartService.addCart(cart);
        }else{
            one.setQuantity(one.getQuantity()+1);
            cartService.addCart(one);
        }



    }

}