package com.shepherd.springmvc_travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public ModelAndView index(){

        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
