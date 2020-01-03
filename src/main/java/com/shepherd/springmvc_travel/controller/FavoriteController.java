package com.shepherd.springmvc_travel.controller;

import com.shepherd.springmvc_travel.domain.PageBean;
import com.shepherd.springmvc_travel.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private IRouteService routeService;

    @RequestMapping("/getFavoriteRankList")
    public ModelAndView getFavoriteRankList(){
        ModelAndView mv = new ModelAndView("favoriterank");
        return mv;
    }

    @RequestMapping("/getFavoriteRankInfo")
    @ResponseBody
    public PageBean getFavoriteRankInfo(@RequestParam(name = "currentPage")String currentPageData){
        Integer currentPage = 1;//当前页码，如果不传递，则默认为第一页
        if(currentPageData != null && currentPageData.length() > 0 && !"null".equals(currentPageData)){
            currentPage = Integer.parseInt(currentPageData);
        }else{
            currentPage = 1;
        }
        Integer pageSize = 8;
        PageBean pageBean = routeService.loadWithPage( currentPage, pageSize,"count");
        System.out.println(pageBean);
        return pageBean;
    }
}
